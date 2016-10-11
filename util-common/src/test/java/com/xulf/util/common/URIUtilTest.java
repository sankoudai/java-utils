package com.xulf.util.common;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by sankoudai on 2016/10/11.
 */
public class URIUtilTest {
    @Test
    public void resolveParameter() {
        //正常情形
        String queryString = "a=1&c=2";
        Map<String, String> params = URIUtil.resolveParameters(queryString);
        Assert.assertEquals(params.size(), 2);
        Assert.assertEquals(params.get("a"), "1");
        Assert.assertEquals(params.get("c"), "2");

        //无等号情形
        queryString = "a=1&b&c=2";
        params = URIUtil.resolveParameters(queryString);
        Assert.assertEquals(params.size(), 2);
        Assert.assertEquals(params.get("a"), "1");
        Assert.assertEquals(params.get("c"), "2");

        //多个等号的情形
        queryString = "a=1&b==1&c=2";
        params = URIUtil.resolveParameters(queryString);
        Assert.assertEquals(params.size(), 3);
        Assert.assertEquals(params.get("a"), "1");
        Assert.assertEquals(params.get("b"), "=1");
        Assert.assertEquals(params.get("c"), "2");

        //同key多次出现情形
        queryString = "a=1&a=2&c=2";
        params = URIUtil.resolveParameters(queryString);
        Assert.assertEquals(params.size(), 2);
        Assert.assertEquals(params.get("a"), "1");
        Assert.assertEquals(params.get("c"), "2");

        //encodeURIComponent 编码情形
        queryString = "a=%3DC%E8%AF%AD%E8%A8%80";
        params = URIUtil.resolveParameters(queryString);
        Assert.assertEquals(params.get("a"), "=C语言");

        //encodeURI 编码情形
        queryString = "a==C%E8%AF%AD%E8%A8%80";
        params = URIUtil.resolveParameters(queryString);
        Assert.assertEquals(params.get("a"), "=C语言");
    }
}
