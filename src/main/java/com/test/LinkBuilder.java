package com.test;

import com.google.gson.JsonObject;

/**
 * LinkBuilder
 * @ author knight7024@naver.com
 * @ version 1.1.0
 */
public class LinkBuilder {
    private String pc;
    private String mobile;
    private String web;

    public LinkBuilder pc(String pc) {
        this.pc = pc;
        return this;
    }

    public LinkBuilder mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public LinkBuilder web(String web) {
        this.web = web;
        return this;
    }

    public JsonObject build() {
        JsonObject link = new JsonObject();
        if (pc != null)
            link.addProperty("pc", pc);
        if (mobile != null)
            link.addProperty("mobile", mobile);
        if (web != null)
            link.addProperty("web", web);
        return link;
    }
}
