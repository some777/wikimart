package com.mikhail.async;

import com.mikhail.model.Offer;

import java.util.List;
import java.util.concurrent.Callable;


public class AsyncGetCommonElements implements  Callable{
    private List<Offer> primaryList;
    private List<Offer> secondaryList;

    public AsyncGetCommonElements(List<Offer> primaryList, List<Offer> secondaryList) {
        this.primaryList = primaryList;
        this.secondaryList = secondaryList;
    }

    @Override
    public List<Offer> call() throws Exception {
        primaryList.retainAll(secondaryList);

        return primaryList;
    }

    public List<Offer> getPrimaryList() {
        return primaryList;
    }

    public void setPrimaryList(List<Offer> primaryList) {
        this.primaryList = primaryList;
    }

    public List<Offer> getSecondaryList() {
        return secondaryList;
    }

    public void setSecondaryList(List<Offer> secondaryList) {
        this.secondaryList = secondaryList;
    }
}
