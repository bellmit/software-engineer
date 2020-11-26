package com.runtime.es.before;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.HashMap;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/6 16:35
 * @Description
 */
public class Tcs {
    private static TransportClient client = null;

    private static String STR = "{\n" +
            "    \"tweet\": {\n" +
            "      \"properties\": {\n" +
            "        \"message\": {\n" +
            "          \"type\": \"string\"\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }";

    public static void main(String[] args) throws Exception {

        //设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "es-cluster").build();
        //创建client
        client = new PreBuiltTransportClient(settings)
                .addTransportAddresses(new TransportAddress(InetAddress.getByName("node01"), 9300))
                .addTransportAddresses(new TransportAddress(InetAddress.getByName("node02"), 9300))
                .addTransportAddresses(new TransportAddress(InetAddress.getByName("node03"), 9300));

        HashMap<String, Object> settings_map = new HashMap<String, Object>(2);
        settings_map.put("number_of_shards", 3);
        settings_map.put("number_of_replicas", 1);

        client.admin().indices().prepareCreate("twitter123")
                //创建一个type，并指定type中属性的名字和类型
                .addMapping("tweet1",
                        STR)
                .get();


        /*

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
        builder.close();*/


        if (client != null){client.close();}
    }

}
