package com.atomic.cm.api;

import com.cloudera.api.model.ApiHealthSummary;

/**
 * @Project:
 * @Description:
 * @Version 1.0.0
 * @Throws SystemException:
 * @Author: <li>2019/12/3/003 Administrator Create 1.0
 * @Copyright ©2018-2019 al.github
 * @Modified By:
 */
public class Agent {

    private String name;

    private ApiHealthSummary status;

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(ApiHealthSummary status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public ApiHealthSummary getStatus() {
        return status;
    }
}
