package com.test;

import com.runtime.date.DateUtils;

import java.util.Date;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/9/12 11:20
 * @Description
 */
public class dateTest {
    public static void main(String[] args) {

        String str = "{\"properties\":{\"disease_id\":{\"type\":\"keyword\"},\"weight_value\":{\"type\":\"integer\"},\"keyword\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\"},\"disease_name\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\"},\"status\":{\"type\":\"integer\"},\"body_parts_id\":{\"type\":\"integer\"},\"body_parts_name\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\"}}}\n";
        String str1 = "{\n" +
                "    \"settings\" : {\n" +
                "        \"number_of_shards\" : 3,\n" +
                "        \"number_of_replicas\" : 2\n" +
                "    },\n" +
                "    \"mappings\" : {\n" +
                "        \"tweet\" : {\n" +
                "            \"properties\" : {\n" +
                "                \"message\" : { \"type\" : \"text\" }\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"aliases\" : {\n" +
                "        \"twitter_alias\" : {}\n" +
                "    }\n" +
                "}";

        System.out.println(str1.length() % 2);
    }
}
