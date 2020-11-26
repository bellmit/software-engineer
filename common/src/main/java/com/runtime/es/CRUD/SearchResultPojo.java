package com.runtime.es.CRUD;

import lombok.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/3 11:24
 * @Description
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchResultPojo {
    private int id; //ID
    private int weight_value; //权重值
    private String keyword; //关键字
    private String disease_name; //疾病名称
    private int status;//状态码
    private int body_parts_id; //父Id
    // private int weight_value;

}