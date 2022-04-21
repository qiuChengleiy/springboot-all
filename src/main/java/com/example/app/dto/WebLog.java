package com.example.app.dto;

import lombok.Data;

/**
 * @author qcl
 * @version 1.0
 * @date 2022/4/21 10:38 AM
 * @description
 */
@Data
public class WebLog {
    /**
     * 请求参数
     */
    private Object params;

    /**
     * URI
     */
    private String uri;

    /**
     * URL
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;


    /**
     * 请求返回的结果
     */
    private Object response;

    /**
     * IP地址
     */
    private String ip;

}
