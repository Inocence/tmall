package com.baiyu.tmall.pojo.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CateVo {
    private List<Integer> cateIds;
    private int cateId;
    private int total;
}
