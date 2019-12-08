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
public class Service {

    private String name;

    private String type;

    private List<Agent> agentList = new ArrayList();

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAgentList(List<Agent> agentList) {
        this.agentList = agentList;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<Agent> getAgentList() {
        return agentList;
    }
}
