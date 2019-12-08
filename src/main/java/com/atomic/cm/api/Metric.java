package com.atomic.cm.api;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project:
 * @Description:
 * @Version 1.0.0
 * @Throws SystemException:
 * @Author: <li>2019/12/3/003 Administrator Create 1.0
 * @Copyright ©2018-2019 al.github
 * @Modified By:
 */
public class Metric {

    private String metricName;

    private String entityName;

    private String startTime;

    private String endTime;

    List<Data> data = new ArrayList();

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getMetricName() {
        return metricName;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public List<Data> getData() {
        return data;
    }
}
