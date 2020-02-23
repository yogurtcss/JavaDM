package pers.yo.service.impl;

import org.apache.commons.lang.StringUtils;
import pers.yo.service.RedisRepositoryService;
import pers.yo.utils.JedisPoolUtils;

//Redis url仓库实现类

/* 2020-02-23 12:38:20
 * 把电视剧列表url、剧集详情url全塞进同一个 redis url仓库中，
 * 并用 高优先级、低优先级区分 列表url和详情url
 * */
public class RedisRepositoryServiceImpl implements RedisRepositoryService {
    @Override //从redis中拉出一条 高优先级/低优先级 的url
    public String poll() {
        String url = JedisPoolUtils.poll( JedisPoolUtils.highKey ); //在redis中，先获取高优先级的url
        if( StringUtils.isNotBlank(url) ){ //高优先级的url被解析完后
            url = JedisPoolUtils.poll( JedisPoolUtils.lowKey ); //就解析低优先级的url
        }
        return url;
    }

    @Override //高优先级
    public void add_HighLevelUrl_IntoRedis(String url) {
        JedisPoolUtils.add( JedisPoolUtils.highKey,url );
    }

    @Override //低优先级
    public void add_LowLevelUrl_IntoRedis(String url) {
        JedisPoolUtils.add( JedisPoolUtils.lowKey,url );
    }


}
