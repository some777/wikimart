package com.mikhail.exc;

import com.mikhail.async.AsyncCheckIsPictureUrlValid;
import com.mikhail.async.AsyncGetCommonElements;
import com.mikhail.async.AsyncGetNewRemovedElements;
import com.mikhail.async.AsyncUnmarshalling;
import com.mikhail.model.Offer;
import com.mikhail.model.YmlCatalog;
import com.mikhail.utils.TimeMeasureSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainProcessing {
    private TimeMeasureSupport timeMeasureSupport;

    private List<Offer> offersNew;
    private List<Offer> offersNew1;
    private List<Offer> offersNewReserv;

    private List<Offer> offersOld;
    private List<Offer> offersOld1;
    private List<Offer> offersOldReserv;

    private final String fileNew; //= "E:\\Wikimart\\resources\\yandex1.xml";
    private final String fileOld; //= "E:\\Wikimart\\resources\\yandex.xml";

    private final Integer threadPoolSize = 600;

    private long timeBegin;

    public MainProcessing(String oldFile, String newFile){
        timeBegin = System.currentTimeMillis();

            this.fileNew = newFile;
            this.fileOld = oldFile;
            timeMeasureSupport = new TimeMeasureSupport();
        init();
    }

    public void runProcessing(){

        //split new and old file on: removed element list,
        //new element list, and common and update list
        doListCompareJob();

        //mark new element as new
        markElementAs(offersNew, "n");

        //Common (and updated) elements + new elements, we get  new file again
        offersNewReserv.addAll(offersNew);

        //check is picture url valid
        asyncCheckIsPictureUrlValid(offersNewReserv);

        printResult(offersNewReserv);
        printStatistics();
    }

    private void init(){
        Long localTimeBegin = System.currentTimeMillis();;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<YmlCatalog> newCatalog = executorService.submit(new AsyncUnmarshalling(fileNew));
        Future<YmlCatalog> oldCatalog = executorService.submit(new AsyncUnmarshalling(fileOld));

        YmlCatalog ymlCatalogNew = null;
        YmlCatalog ymlCatalogOld = null;
        try {
            ymlCatalogNew = newCatalog.get();
            ymlCatalogOld = oldCatalog.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        //we clone lists here
        offersNew = ymlCatalogNew.getShop().getOffers();
        offersNew1 = new ArrayList<Offer>(offersNew);
        offersNewReserv = new ArrayList<Offer>(offersNew);

        offersOld = ymlCatalogOld.getShop().getOffers();
        offersOld1 = new ArrayList<Offer>(offersOld);
        offersOldReserv = new ArrayList<Offer>(offersOld);

        Long localEndTime = System.currentTimeMillis();
        getTimeMeasureSupport().setUnmarshallingTime(localEndTime - localTimeBegin);
    }

    private void doListCompareJob(){
        Long localBeginTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<List<Offer>> newElementList = executorService.submit(new AsyncGetNewRemovedElements(offersNew, offersOld));
        Future<List<Offer>> removedElementList = executorService.submit(new AsyncGetNewRemovedElements(offersOld1, offersNew1));
        Future<List<Offer>> commonElementList = executorService.submit(new AsyncGetCommonElements(offersNewReserv, offersOldReserv));

        try {
            newElementList.get();
            removedElementList.get();
            commonElementList.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        Long localEndTime = System.currentTimeMillis();
        getTimeMeasureSupport().setProcessingNewUpdateRemoveElementList(localEndTime - localBeginTime);
    }

    private  void asyncCheckIsPictureUrlValid(List<Offer> offerList){
        Long localBeginTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        CountDownLatch latch = new CountDownLatch(offerList.size());

        for(Offer current : offerList){
            executorService.submit(new AsyncCheckIsPictureUrlValid(current, latch));
        }

        executorService.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long localEndTime = System.currentTimeMillis();
        getTimeMeasureSupport().setProcessingMalformedUrlTime(localEndTime - localBeginTime);
    }

    private void printElementListMarkedAs(List<Offer> listOffer, String status){
        listOffer.
                stream().
                forEach(val -> System.out.println(val.getId() + " " + status));
    }

    private void markElementAs(List<Offer> listOffer, String markSymbol){
        listOffer.
                stream().
                forEach(val -> val.setStatus(val.getStatus() + markSymbol));
    }

    private void printResult(List<Offer> listOffer){
        listOffer.
                stream().
                filter(val -> {return val.getPictureUrlValid().contains("p") ||
                    val.getStatus().contains("u") ||
                    val.getStatus().contains("n") ;}).
                forEach(val -> System.out.println(val.getId()  + " " +  " " + val.getStatus() + val.getPictureUrlValid()));

        printElementListMarkedAs(offersOld1, "r");
    }

    private void printStatistics(){
        System.out.println("---------------------------------------");
        System.out.println("Unmarshalling time : " + getTimeMeasureSupport().getUnmarshallingTime() + " millisec");
        System.out.println("New, removed, common, updated element");
        System.out.println("getting time : " + getTimeMeasureSupport().getProcessingNewUpdateRemoveList()  +" millisec");
        System.out.println("Processing malformed url time : " + getTimeMeasureSupport().getProcessingMalformedUrlTime()  +" millisec");

        long timeEnd = System.currentTimeMillis();
        System.out.println("Total program time: " + (timeEnd - timeBegin));
    }

    public TimeMeasureSupport getTimeMeasureSupport() {
        return timeMeasureSupport;
    }

    public void setTimeMeasureSupport(TimeMeasureSupport timeMeasureSupport) {
        this.timeMeasureSupport = timeMeasureSupport;
    }
}
