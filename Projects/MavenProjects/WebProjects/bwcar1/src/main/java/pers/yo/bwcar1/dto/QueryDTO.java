package pers.yo.bwcar1.dto;

public class QueryDTO {
    /* 请求的地址是：
    * http://localhost:8080/sys/menu/list ?【order=asc&limit=10&offset=0】
    *  */
    //根据请求的参数，写出QueryDTO对象的属性
    private int offset;
    private int limit;
    private String order; //排序顺序，如asc升序、或desc降序

    private String sort; //排序的字段
    private String search; //模糊搜索的关键字

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
