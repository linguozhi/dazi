package com.dazi.core.common.api;

import com.alibaba.fastjson.JSON;
import com.dazi.core.common.utils.MD5Util;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @desc: 通用接口客户端
 * @author:guice_lin@126.com
 * @date: 2016/3/1
 */
public class ApiClient {
    /**
     * id
     */
    private int id;

    /**
     * 服务器地址
     */
    private String url;

    /**
     * caller
     */
    private String caller;

    /**
     * secretKey
     */
    private String secretKey;

    /**
     * isConnected
     */
    private boolean isConnected;

    /**
     * 接口调用超时时间(单位秒)
     */
    private int timeoutSeds;

    /**
     * Creates a new instance of UCAPIClient.
     */
    public ApiClient(String url, String caller, String secretKey) {
        this(url, caller, secretKey, 10);
    }

    /**
     * Creates a new instance of UCAPIClient.
     */
    public ApiClient(String url, String caller, String secretKey, int timeoutSeds) {
        this.url = url;
        this.caller = caller;
        this.secretKey = secretKey;
        this.timeoutSeds = timeoutSeds;
    }

    public ResponseMsg post(Map<? , ?> params) {
        HttpEntity<String> httpEntity = new HttpEntity<String>(getRequestJson(params), buildHeaders());
        ResponseEntity<String> res = new RestTemplate().exchange(this.url, HttpMethod.POST, httpEntity, String.class);
        return res == null ? null : JSON.parseObject(res.getBody(), ResponseMsg.class);
    }

    /**
     * 获取请求json字符串
     *
     * @param params
     * @return
     */
    @SuppressWarnings("unused")
	private String getRequestJson(Map<? , ?> params) {

        if (null == params) {
            return "";
        }

        String id = (String) params.get("id");
        id = StringUtils.isEmpty(id) == false ? id : String.valueOf(System.currentTimeMillis());
        params.remove("id");
        String sign = getSign(params, false);

        Map<String, String> client = new HashMap<String, String>();
        client.put("caller", this.caller);
        client.put("ex", "");

        Map<String , Object> bodyMap = new HashMap<String , Object>();
        bodyMap.put("id", id);
        bodyMap.put("data", params);
        bodyMap.put("client", client);
        bodyMap.put("encrypt", "md5");
        bodyMap.put("sign", sign);

        return JSON.toJSONString(bodyMap);
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        return headers;
    }

    /**
     * MD5摘要运算
     *
     * @param params
     * @return
     */
    private String getSign(Map<? , ?> params, boolean withChar) {
        if (params == null) {
            return null;
        }

        Map treeMap = new TreeMap(params);
        String sign = MD5Util.md5(this.caller + JSON.toJSONString(treeMap) + this.secretKey);
        return sign;
    }

    /**
     * get id
     *
     * @return id id
     */
    public int getId() {
        return id;
    }

    /**
     * set id
     *
     * @param id
     *            id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get isConnected
     *
     * @return isConnected isConnected
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * set isConnected
     *
     * @param isConnected
     *            isConnected
     */
    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    /**
     * get 服务器地址
     *
     * @return url 服务器地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * set 服务器地址
     *
     * @param url
     *            服务器地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * get caller
     *
     * @return caller caller
     */
    public String getCaller() {
        return caller;
    }

    /**
     * set caller
     *
     * @param caller
     *            caller
     */
    public void setCaller(String caller) {
        this.caller = caller;
    }

    /**
     * get secretKey
     *
     * @return secretKey secretKey
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * set secretKey
     *
     * @param secretKey
     *            secretKey
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * get 接口调用超时时间(单位秒)
     *
     * @return timeoutSeds 接口调用超时时间(单位秒)
     */
    public int getTimeoutSeds() {
        return timeoutSeds;
    }

    /**
     * set 接口调用超时时间(单位秒)
     *
     * @param timeoutSeds
     *            接口调用超时时间(单位秒)
     */
    public void setTimeoutSeds(int timeoutSeds) {
        this.timeoutSeds = timeoutSeds;
    }
}
