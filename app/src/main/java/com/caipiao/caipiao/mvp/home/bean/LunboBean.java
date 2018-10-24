package com.caipiao.caipiao.mvp.home.bean;

import java.util.List;

public class LunboBean {

    /**
     * success : true
     * accessToken : debde67c-8a19-4aa3-bb86-552f618a7fdc
     * content : []
     */

    private boolean success;
    private String accessToken;
    private List<?> content;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}
