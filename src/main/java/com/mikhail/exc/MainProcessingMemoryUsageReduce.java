package com.mikhail.exc;

import com.mikhail.async.*;
import com.mikhail.model.Offer;
import com.mikhail.model.YmlCatalog;
import com.mikhail.utils.TimeMeasureSupport;

import java.util.List;
import java.util.concurrent.*;

public class MainProcessingMemoryUsageReduce {
    private TimeMeasureSupport timeMeasureSupport;

    private List<Offer> offersNew;
    private List<Offer> offersOld;

    private String fileNew;// = "E:\\Wikimart\\resources\\yandex1.xml";
    private String fileOld;// = "E:\\Wikimart\\resources\\yandex.xml";

    private long timeBegin;

    public MainProcessingMemoryUsageReduce(String oldFile, String newFile){
        timeBegin = System.currentTimeMillis();

        this.fileNew = newFile;
        this.fileOld = oldFile;
        timeMeasureSupport = new TimeMeasureSupport();
        init();
    }

    public void runProcessing(){
        CountDownLatch countDownLatch = new CountDownLatch(2);

        //split new and old file on: removed element list,
        //new element list, and common and update list
        //doListCompareJob();
        new Thread(new AsyncExecuteListCompareJob(offersNew, offersOld, timeMeasureSupport, countDownLatch)).start();

        //check is picture url valid
        //asyncCheckIsPictureUrlValid(offersNew);
        new Thread(new AsyncExecutePictureUrlValid(countDownLatch, offersNew, timeMeasureSupport)).start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        printResult(offersNew, offersOld);
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

        offersNew = ymlCatalogNew.getShop().getOffers();
        offersOld = ymlCatalogOld.getShop().getOffers();

        Long localEndTime = System.currentTimeMillis();
        getTimeMeasureSupport().setUnmarshallingTime(localEndTime - localTimeBegin);
    }

    private void doListCompareJob(){
        Long localBeginTime = System.currentTimeMillis();

        CountDownLatch latch = new CountDownLatch(2);

        //check for removed elements
        new Thread(new AsyncCheckForNewRemovedElements(latch, "r", offersOld, offersNew)).start();

        //check for new elements
        new Thread(new AsyncCheckForNewRemovedElements(latch, "n", offersNew, offersOld)).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long localEndTime = System.currentTimeMillis();
        getTimeMeasureSupport().setProcessingNewUpdateRemoveElementList(localEndTime - localBeginTime);
    }

    private  void asyncCheckIsPictureUrlValid(List<Offer> offerList){
        Long localBeginTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(600);
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

    private void printResult(List<Offer> listOfferNew, List<Offer> listOfferOld){
        listOfferNew.
                stream().
                filter(val -> {return val.getPictureUrlValid().contains("p") ||
                        val.getStatus().contains("u") ||
                        val.getStatus().contains("n") ;}).
                forEach(val -> System.out.println(val.getId()  + " " +  " " + val.getStatus() + val.getPictureUrlValid()));

        listOfferOld.
                stream().
                filter(val -> {return val.getStatus().contains("r") ;}).
                forEach(val -> System.out.println(val.getId()  + " " +  " " + val.getStatus()));

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
