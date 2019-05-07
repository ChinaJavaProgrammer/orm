package util;

/**
 * Created with IntelliJ IDEA.
 * User: wuxiaogang
 * Date: 2018/08/16
 * Description:
 * Version: V1.0
 */
public class PageUtil {
    private int numberPage; //总页数
    private int totalPage; //总条数
    private int pageSize; //每页大小
    private int newPage; //当前页码

    /**
     * paging 构造方法
     * @param totalPage   对应表总数据条数
     * @param pageSize  每页大小
     * @param newPage    当前要查询页码
     */
    public PageUtil(int totalPage, int newPage,int pageSize) {
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.newPage = newPage;
        this.numberPage = (totalPage-1)/pageSize + 1;
    }

    /**
     *
     * @return 总页数
     */
    public int getNumberPage() {
        return numberPage;
    }

    /**
     *
     * @return  数据总条数
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     *
     * @return 每页大小
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     *
     * @return  当前要查询页码页码
     */
    public int getNewPage() {
        return newPage;
    }
}

