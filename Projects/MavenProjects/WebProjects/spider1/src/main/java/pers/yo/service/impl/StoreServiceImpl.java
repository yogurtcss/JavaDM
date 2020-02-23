package pers.yo.service.impl;

import pers.yo.entity.Page;
import pers.yo.service.StoreService;
import pers.yo.utils.HBaseUtil;
import pers.yo.utils.JedisPoolUtils;

import java.io.IOException;

public class StoreServiceImpl implements StoreService {

    public HBaseUtil HBaseUtil = new HBaseUtil(); //我佛了，工具类居然还要new出来！

    @Override
    public void store(Page page) {
        //2020-02-23 20:45:22
        String tvId = page.getTvId();
        JedisPoolUtils.add( "solr_tv_index", tvId );
        /*
        public void put(
                        String tableName,
                        String row,
                        String columnFamily,
			            String column,
			            String data  )
        *  */
        try{
            HBaseUtil.put( HBaseUtil.TABLE_NAME, tvId, HBaseUtil.COLUMNFAMILY_1, HBaseUtil.COLUMNFAMILY_1_URL, page.getUrl() );
            HBaseUtil.put( HBaseUtil.TABLE_NAME, tvId, HBaseUtil.COLUMNFAMILY_1, HBaseUtil.COLUMNFAMILY_1_TVNAME, page.getTvName() );
            HBaseUtil.put( HBaseUtil.TABLE_NAME, tvId, HBaseUtil.COLUMNFAMILY_1, HBaseUtil.COLUMNFAMILY_1_HEATTEXT, page.getHeatText() );
            HBaseUtil.put( HBaseUtil.TABLE_NAME, tvId, HBaseUtil.COLUMNFAMILY_1, HBaseUtil.COLUMNFAMILY_1_SCORE, page.getScore() );
            HBaseUtil.put( HBaseUtil.TABLE_NAME, tvId, HBaseUtil.COLUMNFAMILY_1, HBaseUtil.COLUMNFAMILY_1_TAG, page.getTag() );
        }catch( IOException e){
            e.printStackTrace();
        }

    }
}
