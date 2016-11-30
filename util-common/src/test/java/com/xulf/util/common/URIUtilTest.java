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

    @Test
    public void appendParamTest(){
        String url = "http://host/path?";
        String key = "name";
        String val = "jim";

        String appendedParam = URIUtil.appendParam(url, key, val);
        Assert.assertEquals(appendedParam, "http://host/path?name=jim");

        url = "http://host/path?age=9&";
        appendedParam = URIUtil.appendParam(url, key, val);
        Assert.assertEquals(appendedParam, "http://host/path?age=9&name=jim");

        url = "http://host/path?age=9";
        appendedParam = URIUtil.appendParam(url, key, val);
        Assert.assertEquals(appendedParam, "http://host/path?age=9&name=jim");
    }
    
    @Test
    public void tmpTest() {
        URIUtil.resolveParameters("content=platform=android&uid=2279251&goods_id=757218&enterTime=2016-10-18 14:26:51&exitTime=2016-10-18 14:27:20&shp_fit_flag=null&statistic_type=0&duration=1393#$%platform=android&uid=2279251&goods_id=757209&enterTime=2016-10-18 14:28:21&exitTime=2016-10-18 14:28:40&shp_fit_flag=null&statistic_type=0&duration=2045#$%platform=android&uid=2279251&goods_id=757698&enterTime=2016-10-18 14:29:14&exitTime=2016-10-18 14:29:47&shp_fit_flag=null&statistic_type=0&duration=1146#$%platform=android&uid=2279251&goods_id=757297&enterTime=2016-10-18 14:30:25&exitTime=2016-10-18 14:30:47&shp_fit_flag=null&statistic_type=0&duration=1760#$%platform=android&uid=2279251&goods_id=756091&enterTime=2016-10-18 14:30:53&exitTime=2016-10-18 14:31:18&shp_fit_flag=null&statistic_type=0&duration=2058#$%platform=android&uid=2279251&goods_id=754414&enterTime=2016-10-18 14:32:02&exitTime=2016-10-18 14:32:20&shp_fit_flag=null&statistic_type=0&duration=2665#$%platform=android&uid=2279251&goods_id=743543&enterTime=2016-10-18 14:33:42&exitTime=2016-10-18 14:33:44&shp_fit_flag=null&statistic_type=0&duration=1580#$%platform=android&uid=2279251&goods_id=530033&enterTime=2016-10-18 14:33:57&exitTime=2016-10-18 14:34:02&shp_fit_flag=null&statistic_type=0&duration=2860#$%platform=android&uid=2279251&goods_id=704770&enterTime=2016-10-18 14:35:14&exitTime=2016-10-18 14:35:16&shp_fit_flag=null&statistic_type=0&duration=390#$%platform=android&uid=2279251&goods_id=741869&enterTime=2016-10-18 14:36:01&exitTime=2016-10-18 14:36:05&shp_fit_flag=null&statistic_type=0&duration=1963#$%platform=android&uid=2279251&goods_id=749716&enterTime=2016-10-18 14:36:19&exitTime=2016-10-18 14:36:32&shp_fit_flag=null&statistic_type=0&duration=694#$%platform=android&uid=2279251&goods_id=749172&enterTime=2016-10-18 14:36:57&exitTime=2016-10-18 14:37:14&shp_fit_flag=null&statistic_type=0&duration=1327#$%platform=android&uid=2279251&goods_id=710264&enterTime=2016-10-18 14:37:52&exitTime=2016-10-18 14:38:11&shp_fit_flag=null&statistic_type=0&duration=3588#$%platform=android&uid=2279251&goods_id=727825&enterTime=2016-10-18 14:38:32&exitTime=2016-10-18 14:38:48&shp_fit_flag=null&statistic_type=0&duration=3269#$%platform=android&uid=2279251&goods_id=713185&enterTime=2016-10-18 14:39:18&exitTime=2016-10-18 14:39:21&shp_fit_flag=null&statistic_type=0&duration=1056#$%platform=android&uid=2279251&goods_id=754341&enterTime=2016-10-18 14:42:13&exitTime=2016-10-18 14:42:41&shp_fit_flag=null&statistic_type=0&duration=9676#$%platform=android&uid=2279251&goods_id=731042&enterTime=2016-10-18 14:42:53&exitTime=2016-10-18 14:42:58&shp_fit_flag=null&statistic_type=0&duration=4278#$%platform=android&uid=2279251&goods_id=741447&enterTime=2016-10-18 14:42:57&exitTime=2016-10-18 14:43:18&shp_fit_flag=null&statistic_type=0&duration=1033#$%platform=android&uid=2279251&goods_id=754333&enterTime=2016-10-18 14:43:26&exitTime=2016-10-18 14:43:43&shp_fit_flag=null&statistic_type=0&duration=4190&IP=221.223.204.152&&userip=221.223.204.152");
    }
}
