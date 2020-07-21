package com.Logger;

public class Process implements IProcess {

    private String processID;
    private Long startTime;
    private Long endTime;

    public Process(String processID, Long startTime){
        this.processID = processID;
        this.startTime = startTime;
        endTime = -1L;
    }

    @Override
    public Long getStartTime() {
        return startTime;
    }

    @Override
    public Long getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }


    @Override
    public String getProcessID() {
        return processID;
    }
}
