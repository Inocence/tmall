package com.baiyu.tmall.pojo.item;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SearchCatesItem {
    private List<Integer> cateIds;
    private int cateId;
    private int total;
}
