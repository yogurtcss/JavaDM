package pers.yo.utils;

import org.apache.commons.lang.StringUtils;
import pers.yo.entity.Page;

//建立索引的工具类
public class SolrJob {

    private static final String SOLR_TV_INDEX = "solr_tv_index";
    //引入redis 工具类

    public static void buildIndex(){
        HBaseUtil HBaseUtil = new HBaseUtil();
        String tvId = "";
        try{
            System.out.println( "开始遍历索引..." );
            tvId = JedisPoolUtils.poll( SOLR_TV_INDEX ); //先从redis中取出rowKey

            while( !Thread.currentThread().isInterrupted() ){
                if( StringUtils.isNotBlank(tvId) ){
                    //根据rowKey，从HBase中查出页面信息
                    Page page = HBaseUtil.get( HBaseUtil.TABLE_NAME, tvId );
                    if( page!=null ){ //如果能查到，立马建立索引
                        SolrUtil.addIndex( page );
                    }
                    tvId = JedisPoolUtils.poll( SOLR_TV_INDEX ); //获取下一个tvId
                }else{
                    System.out.println( "目前没有需要索引的数据，休息一会再处理！" );
                    ThreadUtil.sleep( 5000 );
                }
            }
        } catch (Exception e) {
            JedisPoolUtils.add( SOLR_TV_INDEX, tvId );
            e.printStackTrace();
        }

    }

    /* 2020-02-24 09:36:32
    * 原来还有这个主函数！
    * */
    public static void main(String[] args) {
        buildIndex();
    }

}
