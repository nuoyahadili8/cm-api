package com.atomic.cm.api;

import com.cloudera.api.ApiRootResource;
import com.cloudera.api.ClouderaManagerClientBuilder;
import com.cloudera.api.DataView;
import com.cloudera.api.model.ApiCluster;
import com.cloudera.api.model.ApiClusterList;
import com.cloudera.api.model.ApiTimeSeriesRequest;
import com.cloudera.api.v11.RootResourceV11;
import com.cloudera.api.v11.TimeSeriesResourceV11;
import com.cloudera.api.v18.RootResourceV18;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * @Project:
 * @Description:
 * @Version 1.0.0
 * @Throws SystemException:
 * @Author: <li>2019/12/1/001 Administrator Create 1.0
 * @Copyright ©2018-2019 al.github
 * @Modified By:
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        String query="select total_bytes_receive_rate_across_network_interfaces, total_bytes_transmit_rate_across_network_interfaces where category = CLUSTER";
        String from="2019-12-02 23:40:00";
        String end="2019-12-02 23:50:00";
        String json=getTimeSeriesResponse(query,from,end);
        System.out.println("==========="+json);

//        getAllCluster();
    }

    public static void getAllCluster(){
        RootResourceV18 apiRoot = new ClouderaManagerClientBuilder().withHost("192.168.226.136").
        withPort(Integer.valueOf(7180))
                .withUsernamePassword("admin", "admin").build().getRootV18();
        LOGGER.info("开始测试的时间为{},**************开始测试获取ClouderaManager集群信息**************"+"aaaaaaa");
        ApiClusterList apiClusterList = apiRoot.getClustersResource().readClusters(DataView.FULL);
        LOGGER.info("ClouderaManager 共管理了{}个集群"+apiClusterList.getClusters().size());
        for(ApiCluster apiCluster : apiClusterList){
            ApiCluster apiCluster1 = apiRoot.getClustersResource().readCluster(apiCluster.getName());
            LOGGER.info("集群名称 {}"+apiCluster1.getName());
            LOGGER.info("集群显示名称 {}"+apiCluster1.getDisplayName());
            LOGGER.info("CDH 版本：{}-{}"+apiCluster1.getVersion()+apiCluster.getFullVersion());
            LOGGER.info("ClusterUrl {}"+apiCluster1.getClusterUrl());
            LOGGER.info("HostUrl {}"+apiCluster1.getHostsUrl());
            LOGGER.info("Cluster Uuid {}"+apiCluster1.getUuid());
            LOGGER.info("集群运行状态 {}"+apiCluster1.getEntityStatus());
        }
        LOGGER.info("结束测试的时间为{},**************结束测试获取ClouderaManager集群信息**************"+"aaaaaaa");
    }

    public static String getTimeSeriesResponse(String query, String from, String to) throws ParseException {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fromDate = dfs.parse(from);
        Date toDate = dfs.parse(to);
        SimpleDateFormat rfs = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+0800'");
        String fromformat = rfs.format(fromDate);
        String toformat = rfs.format(toDate);

        long between = (toDate.getTime() - fromDate.getTime());
        int minutes = (int) (between / (1000 * 60));
        String desire = "RAW";
        System.out.println("minutes" + minutes);
        if (minutes <= 30) {
            desire = "RAW";
        } else if (minutes > 30 && minutes < 300) {
            desire = "TEN_MINUTELY";
        } else if (minutes >= 300 && minutes < 1800) {
            desire = "HOURLY";
        } else if (minutes >= 1800 && minutes < 10800) {
            desire = "SIX_HOURLY";
        } else {
            desire = "DAILY";
        }

        System.out.println("desire" + desire);
        ApiTimeSeriesRequest atsr = new ApiTimeSeriesRequest();
        atsr.setQuery(query);
        atsr.setFrom(fromformat);
        atsr.setTo(toformat);
        atsr.setDesiredRollup(desire);
        atsr.setMustUseDesiredRollup(true);
        ApiRootResource root = new ClouderaManagerClientBuilder()
                .withHost("192.168.226.136").withPort(7180)
                .withUsernamePassword("admin", "admin")
                .build();

        RootResourceV11 v11 = root.getRootV11();

        TimeSeriesResourceV11 t11 = v11.getTimeSeriesResource();
        Response res = t11.queryTimeSeries(atsr);
        String jsonResponse = res.readEntity(String.class);
//		JSONArray jsonArray = JSONArray.fromObject(obj.get("items"));
//		List<ApiTimeSeriesResponse> lst=(List) JSONArray.toCollection(jsonArray,  ApiTimeSeriesResponse.class);
        return jsonResponse;
    }
}
