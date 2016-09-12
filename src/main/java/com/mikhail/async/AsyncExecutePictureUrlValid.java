package com.mikhail.async;

import com.mikhail.model.Offer;
import com.mikhail.utils.TimeMeasureSupport;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncExecutePictureUrlValid implements Runnable {
    private CountDownLatch latch;
    private List<Offer> offerList;
    private TimeMeasureSupport timeMeasureSupport;
    private final Integer threadPoolSize = 600;

    public AsyncExecutePictureUrlValid(CountDownLatch latch, List<Offer> offerList, TimeMeasureSupport timeMeasureSupport) {
        this.latch = latch;
        this.offerList = offerList;
        this.timeMeasureSupport = timeMeasureSupport;
    }

    @Override
    public void run() {
        Long localBeginTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        CountDownLatch countDownLatch = new CountDownLatch(offerList.size());

        for(Offer current : offerList){
            executorService.submit(new AsyncCheckIsPictureUrlValid(current, countDownLatch));
        }

        executorService.shutdown();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Long localEndTime = System.currentTimeMillis();
        getTimeMeasureSupport().setProcessingMalformedUrlTime(localEndTime - localBeginTime);

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public List<Offer> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<Offer> offerList) {
        this.offerList = offerList;
    }

    public TimeMeasureSupport getTimeMeasureSupport() {
        return timeMeasureSupport;
    }

    public void setTimeMeasureSupport(TimeMeasureSupport timeMeasureSupport) {
        this.timeMeasureSupport = timeMeasureSupport;
    }
}
