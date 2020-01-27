package demo1.utils;



import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 2020-01-27 18:17:03
* Converter<S-接受的泛型,T-转换后目标的泛型>
*  */

public class StringToDateConverter implements Converter<String,Date> {

    @Override
    public Date convert(String source) {
        Date rst = null;
        if( source==null ){
            throw new RuntimeException( "请您传入数据" );
        }

        DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
        try{
            rst = df.parse( source ); //把字符串转为date类型
//            return df.parse(source);
        }catch( Exception e ){
            throw new RuntimeException( "数据类型转换出现错误" );
        }
        return rst;
    }
}
