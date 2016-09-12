package com.mikhail.async;

import com.mikhail.model.Offer;

import java.util.List;
import java.util.concurrent.CountDownLatch;


public class AsyncCheckForNewRemovedElements implements Runnable{
    private CountDownLatch countDownLatch;
    private String status = "";
    private List<Offer> listOne;
    private List<Offer> listTwo;

    public AsyncCheckForNewRemovedElements(CountDownLatch countDownLatch, String status, List<Offer> listOne, List<Offer> listTwo) {
        this.countDownLatch = countDownLatch;
        this.status = status;
        this.listOne = listOne;
        this.listTwo = listTwo;
    }

    @Override
    public void run() {
        for(Offer currentFromOneList : listOne){
            currentFromOneList.setStatus(status);

            for(Offer currentElementFromTwoList : listTwo){
                if(currentFromOneList.equals(currentElementFromTwoList))
                    break;
            }
        }

        countDownLatch.countDown();
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Offer> getListOne() {
        return listOne;
    }

    public void setListOne(List<Offer> listOne) {
        this.listOne = listOne;
    }

    public List<Offer> getListTwo() {
        return listTwo;
    }

    public void setListTwo(List<Offer> listTwo) {
        this.listTwo = listTwo;
    }


}
