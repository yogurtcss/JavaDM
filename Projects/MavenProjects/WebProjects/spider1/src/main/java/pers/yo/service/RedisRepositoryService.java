package pers.yo.service;

public interface RedisRepositoryService {
    /* 2020-02-23 12:38:20
     * 把电视剧列表url、剧集详情url全塞进同一个 redis url仓库中，
     * 并用 高优先级、低优先级区分 列表url和详情url
     * */
    public abstract String poll();
    public abstract void add_HighLevelUrl_IntoRedis( String url );
    public abstract void add_LowLevelUrl_IntoRedis( String url );
}
