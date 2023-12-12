package cn.weedien.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author weedien
 * @date 2023/12/10
 */
@Data
public class PageBean<T> implements Serializable {
    /**
     * 当前页页码
     */
    private int pageNumber;
    /**
     * 一页显示的记录数
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private int totalRecord;
    /**
     * 总共有多少页
     */
    private int totalPage;
    /**
     * 开始页
     */
    private int startIndex;
    /**
     * 数据集合
     */
    private List<T> data;

    /**
     * 含参构造方法
     */
    public PageBean(int pageNumber, int pageSize, int totalRecord) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        // 计算总页数
        if (totalRecord % pageSize == 0) {
            totalPage = totalRecord / pageSize;
        } else {
            totalPage = totalRecord / pageSize + 1;
        }
        // 起始项(通过计算得到)
        this.startIndex = (this.pageNumber - 1) * pageSize;
    }


}
