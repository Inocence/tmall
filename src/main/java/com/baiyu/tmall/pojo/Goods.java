package com.baiyu.tmall.pojo;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "tmall", type = "goods")
@Data
@ToString
public class Goods {
    @Id
    private int goodsId;
    private int storeId;
    private int cateId;
    private String name;
    private String content;
    private Integer price;
    private String cover;
    private String img;
    private List<String> imgList;
    private String createTime;
    private String updateTime;
    private int updateAt;

    private Cate cate;
}
