package demo02_MyDesign.mybatis.cfg;


//封装 【执行的SQL语句】 和 【结果类型的全限定类型】
public class Mapper {
    private String queryString; //SQL语句
    private String resultType; //返回的实体类的全限定类名

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
