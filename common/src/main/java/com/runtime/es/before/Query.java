package com.runtime.es.before;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/6 11:15
 * @Description
 */
public class Query {
    private TransportClient client = null;

    private static String STR = "{\n" +
            "    \"tweet\": {\n" +
            "      \"properties\": {\n" +
            "        \"message\": {\n" +
            "          \"type\": \"string\"\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }";


    @Before
    public void init() throws Exception {
        //设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "es-cluster").build();
        //创建client
        client = new PreBuiltTransportClient(settings)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName("node01"), 9300))
                .addTransportAddresses(new TransportAddress(InetAddress.getByName("node02"), 9300))
                .addTransportAddresses(new TransportAddress(InetAddress.getByName("node03"), 9300));

    }


    @Test
    public void createIndexWithSettings() {

        //获取Admin的API
        AdminClient admin = client.admin();
        //使用Admin API对索引进行操作
        IndicesAdminClient indices = admin.indices();
        //准备创建索引
        indices.prepareCreate("indexcreatetest11")
                //配置索引参数
                .setSettings(
                        //参数配置器
                        Settings.builder()//指定索引分区的数量
                                .put("index.number_of_shards", 3)
                                //指定索引副本的数量（注意：不包括本身，如果设置数据存储副本为2，实际上数据存储了3份）
                                .put("index.number_of_replicas", 1)
                )
                //真正执行
                .get();

    }

    //跟索引添加mapping信息（给表添加schema信息）
    @Test
    public void putMapping() throws IOException {

        HashMap<String, Object> settings_map = new HashMap<String, Object>(2);
        settings_map.put("number_of_shards", 3);
        settings_map.put("number_of_replicas", 1);


        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                    .field("dynamic", "true")
                        .startObject("properties")
                            .startObject("id")
                            .field("type", "integer")
                            .field("index", "not_analyzed")
                            .field("store", "yes")
                        .endObject()
                .endObject();


        CreateIndexRequestBuilder create = client.admin().indices().prepareCreate("user_info");
        create.setSettings(settings_map).addMapping("user", builder).get();

    }

    @After
    public void close() {
        if (this.client != null) this.client.close();
    }


}
