package com.mikhail.utils;


public class TimeMeasureSupport {
    private Long unmarshallingTime;
    private Long processingNewUpdateRemoveList;
    private Long processingMalformedUrlTime;

    public Long getUnmarshallingTime() {
        return unmarshallingTime;
    }

    public void setUnmarshallingTime(Long unmarshallingTime) {
        this.unmarshallingTime = unmarshallingTime;
    }

    public Long getProcessingNewUpdateRemoveList() {
        return processingNewUpdateRemoveList;
    }

    public void setProcessingNewUpdateRemoveElementList(Long processingNewUpdateRemoveList) {
        this.processingNewUpdateRemoveList = processingNewUpdateRemoveList;
    }

    public Long getProcessingMalformedUrlTime() {
        return processingMalformedUrlTime;
    }

    public void setProcessingMalformedUrlTime(Long processingMalformedUrlTime) {
        this.processingMalformedUrlTime = processingMalformedUrlTime;
    }
}
