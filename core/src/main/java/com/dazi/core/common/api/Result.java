package com.dazi.core.common.api;

import java.util.Map;

/**
 * @desc: 请求返回内容
 * @author:guice_lin@126.com
 * @date: 2016/3/2
 */
public class Result {

    private Head head;
    private Map<String ,Object> body;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    class Head {
        private String result;
        private String message;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    /**
     * 判断是否成功返回
     * @return
     */
    public boolean isSuccess() {
        if("1".equals(this.head.getResult()) && this.body != null) {
            return true;
        }

        return false;
    }
}
