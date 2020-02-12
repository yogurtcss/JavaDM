package pers.yo.bwcar1.dto;

import java.util.List;

/* 2020-02-06 20:03:42
针对 bootstrap要求填入后端数据的格式，特地整出了这个DataGridResult
* */


public class DataGridResult {

    private long total;  //返回的总行数
    private List<?> rows; //具体返回的每一行的内容

    DataGridResult(){}

    public DataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
