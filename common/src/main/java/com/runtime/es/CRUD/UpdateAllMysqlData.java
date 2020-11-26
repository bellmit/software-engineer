package com.runtime.es.CRUD;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/3 11:09
 * @Description
 */
public class UpdateAllMysqlData {
    private static TransportClient clientOld;
    private static String[] hostName = new String[]{"node01", "node02", "node03"};
    private static HashMap<Integer, String> MAP;

    public static void main(String[] args) {
        try {
            clientOld = getClientOld("es-cluster", hostName, 9300);
            args = new String[]{"searchprescriptiov1", "tst"};

            get(clientOld, args);
            //multiSearchFilter(clientOld, args);
            //create(clientOld,args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clientOld.close();
        }
    }

    private static TransportClient getClientOld(String cluster_name, String[] hostName, int port) throws UnknownHostException {

        Settings settings = Settings
                .builder()
                .put("cluster.name", cluster_name)
                .build();

        return new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(hostName[0]), port))
                .addTransportAddress(new TransportAddress(InetAddress.getByName(hostName[1]), port))
                .addTransportAddress(new TransportAddress(InetAddress.getByName(hostName[2]), port));

    }

    private static void create(TransportClient client, String[] args) throws IOException, SQLException {
        List<SearchResultPojo> list = getResult();

        System.out.println("000" + MAP);

        for (SearchResultPojo pojo : list) {
            IndexResponse response = client.prepareIndex(args[0], args[1], String.valueOf(pojo.getId()))
                    .setSource(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("disease_id", pojo.getId())
                            .field("weight_value", pojo.getWeight_value())
                            .field("keyword", pojo.getKeyword())
                            .field("disease_name", pojo.getDisease_name())
                            .field("status", pojo.getStatus())
                            .field("body_parts_id", pojo.getBody_parts_id())
                            .field("body_parts_name", MAP.get(pojo.getBody_parts_id()))
                            .endObject()
                    ).get();
            System.out.println(response.getResult());
        }


    }

    private static void get(TransportClient client, String[] args) {

        if (checkExists(client, args[0]) == false) throw new RuntimeException("Index: " + args[0] + "\t" + "不存在");

        GetResponse response = client.prepareGet(args[0], args[1], "1").get();
        System.out.println(response.getSourceAsString());

    }

    private static boolean checkExists(TransportClient client, String indexName) {

        if ("".equals(indexName) || indexName == null) return false;

        return client
                .admin().indices()
                .exists(new IndicesExistsRequest().indices(indexName))
                .actionGet().isExists();
    }


    private void getAll(TransportClient client, String[] args) {
        SearchResponse response = client.prepareSearch(args[0]).get();

        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        //System.out.println(response);
    }

    private void update(TransportClient client, String[] args) throws IOException {
        UpdateResponse updateResponse = client.prepareUpdate(args[0], args[1], args[2])
                .setDoc(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("status", "3")
                        .endObject()
                ).get();
        System.out.println(updateResponse.getResult());
    }

    private void delete(TransportClient client, String[] args) throws ExecutionException, InterruptedException {
        DeleteResponse response = client.prepareDelete(args[0], args[1], args[2]).execute().get();
        System.out.println(response.getResult());
    }

    void multiSearch(TransportClient client, String[] args) {

        QueryBuilders.boolQuery().must(
                QueryBuilders.matchQuery("keyword", "头疼")
        ).filter(QueryBuilders.matchQuery("status", 1));

        SearchResponse response = client.prepareSearch(args[0])
                .setQuery(QueryBuilders.matchQuery("keyword", "头疼"))
                .addSort("weight_value", SortOrder.ASC)
                .get();
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
        }

    }

    public static List<SearchResultPojo> getResult() throws SQLException {
        Connection conn = null;

        ArrayList<SearchResultPojo> list = null;
        try {
            conn = new MysqlConnect().getConnection();
            list = new ArrayList<>();
            ArrayList<Object> eQList = new ArrayList<>();

            ResultSet executeQuery = conn.prepareStatement("SELECT * FROM symptoms_issue_content").executeQuery();
            ResultSet partEQ = conn.prepareStatement("SELECT * FROM body_parts").executeQuery();
            while (executeQuery.next()) {
                list.add(
                        new SearchResultPojo(
                                executeQuery.getInt("id"),
                                executeQuery.getInt("weighted_value"),
                                executeQuery.getString("issue_keyword"),
                                executeQuery.getString("illness_name"),
                                executeQuery.getInt("status"),
                                executeQuery.getInt("body_parts_id")
                        )
                );
            }

            MAP = new HashMap<>();
            while (partEQ.next()) {
                MAP.put(partEQ.getInt("id"), partEQ.getString("body_parts"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) conn.close();
        }

        return list;
    }


    static void multiSearchFilter(TransportClient client, String[] args) {

        BoolQueryBuilder filter = QueryBuilders
                .boolQuery()
                .must(
                        QueryBuilders.matchQuery("keyword", "1111"))
                .filter(
                        QueryBuilders.matchQuery("status", 1));

        SearchResponse response = client.prepareSearch(args[0])
                .setQuery(QueryBuilders.matchQuery("keyword", "头疼"))
                .setQuery(filter)
                .get();
        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
        }

    }


}
