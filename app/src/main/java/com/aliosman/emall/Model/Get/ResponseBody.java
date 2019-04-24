package com.aliosman.emall.Model.Get;

public class ResponseBody {
    private int code;
    private String body;

    public ResponseBody(){

    }
    public ResponseBody(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public ResponseBody setCode(int code) {
        this.code = code;
        return this;
    }

    public String getBody() {
        return body;
    }

    public ResponseBody setBody(String body) {
        this.body = body;
        return this;
    }
}
