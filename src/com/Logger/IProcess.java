package com.Logger;

public interface IProcess {

    Long getStartTime();

    Long getEndTime();

    void setEndTime(Long endTime);

    String getProcessID();
}
