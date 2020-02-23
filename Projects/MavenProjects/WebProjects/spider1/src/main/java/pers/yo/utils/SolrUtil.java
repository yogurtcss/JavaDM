package pers.yo.utils;

import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import pers.yo.entity.Page;

import java.util.ArrayList;
import java.util.List;

public class SolrUtil {
    private static final String SOLR_URL = "http://localhost:8983/solr"; //solr服务器地址
    private static HttpSolrServer server = null;

    static{
        try{
            server = new HttpSolrServer( SOLR_URL );
            server.setAllowCompression( true );
            server.setConnectionTimeout( 10000 );
            server.setDefaultMaxConnectionsPerHost( 100 );
            server.setMaxTotalConnections( 100 );
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    //建立索引
    public static void addIndex( Page page ) throws Exception{
        server.addBean( page );
        //对索引进行优化
        server.optimize();
        server.commit();
        System.out.println( "添加电视剧索引："+page.getTvId() );
    }
    //删除索引
    public static void delIndex(){
        try{
            server.deleteByQuery( "*:*" );
            server.commit(); //要记得提交数据
        }catch ( Exception e ){
            e.printStackTrace();
        }
    }
    //列表查询
    public static List<Page> search( String sKey, int start, int range, String sort, String field ) throws Exception{
        SolrQuery params = new SolrQuery();
        if( StringUtils.isNotBlank(sKey) ){
            params.set( "q","tvName:"+sKey );
        }else{
            params.set( "q", "*:*" );
        }
        params.set( "start", ""+start );
        params.set( "rows", ""+range );

        if( StringUtils.isNotBlank(sort) ){
            if( sort.equals("asc") ){
                params.setSort( field, SolrQuery.ORDER.asc );
            }else{
                params.setSort( field, SolrQuery.ORDER.desc );
            }
        }
        QueryResponse response = server.query( params );

        List<Page> results = response.getBeans( Page.class );
        return results;
    }

    //列表查询
    public  static Page searchPage( String sKey ) throws Exception{
        SolrQuery params = new SolrQuery();
        params.set( "tvId", sKey );
        QueryResponse response = server.query( params );
        List<Page> pages = response.getBeans( Page.class );
        return pages.get(0);
    }

    public static int getCount( String sKey ){
        int count = 0;
        SolrQuery params = new SolrQuery();
        if( StringUtils.isNotBlank(sKey) ){
            params.set( "q","tvName"+sKey );
        }else{
            params.set( "q","*:*" );
        }
        try{
            QueryResponse response = server.query(params);
            count = (int)response.getResults().getNumFound();
        }catch( SolrServerException e){
            e.getRootCause();
        }
        return count;
    }

    public static void main(String[] args) { //测试方法
        List<Page> pageList = new ArrayList<>();
        int count = 0;
        try{
            pageList = SolrUtil.search( "爱情公寓",0,10,"","" );
            for( Page page : pageList ){
                System.out.println( "tvName："+page.getTvName() );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println( JSONArray.fromObject(pageList).toString() );
    }


}
