package com.mikhail.async;

import com.mikhail.model.Offer;
import com.mikhail.utils.TimeMeasureSupport;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AsyncExecuteListCompareJob implements Runnable {
    private List<Offer> offersNew;
    private List<Offer> offersOld;
    private TimeMeasureSupport timeMeasureSupport;
    private CountDownLatch countDownLatch;

    public AsyncExecuteListCompareJob(List<Offer> offersNew, List<Offer> offersOld, TimeMeasureSupport timeMeasureSupport, CountDownLatch countDownLatch) {
        this.offersNew = offersNew;
        this.offersOld = offersOld;
        this.timeMeasureSupport = timeMeasureSupport;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
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

        countDownLatch.countDown();
    }

    public List<Offer> getOffersNew() {
        return offersNew;
    }

    public void setOffersNew(List<Offer> offersNew) {
        this.offersNew = offersNew;
    }

    public List<Offer> getOffersOld() {
        return offersOld;
    }

    public void setOffersOld(List<Offer> offersOld) {
        this.offersOld = offersOld;
    }

    public TimeMeasureSupport getTimeMeasureSupport() {
        return timeMeasureSupport;
    }

    public void setTimeMeasureSupport(TimeMeasureSupport timeMeasureSupport) {
        this.timeMeasureSupport = timeMeasureSupport;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
