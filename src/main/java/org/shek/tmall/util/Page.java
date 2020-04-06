package org.shek.tmall.util;

public class Page {
    private int start;
    private int count;
    private int total;
    //分类的分页查询中没有使用这个属性
    private String param;

    private static final int defaultCount = 5;

    public Page() {
        count = defaultCount;
    }

    public Page(int start, int count) {
        this.start = start;
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    //是否有上一页
    public boolean isHasPrevious() {
        if (start == 0) {
            return false;
        }
        return true;
    }

    //是否有下一页
    public boolean isHasNext() {
        if (start == getLast()) {
            return false;
        }
        return true;
    }

    //计算总页数
    public int getTotalPage() {
        int totalPage;
        if (0 == total % count) {
            totalPage = total / count;
        } else {
            totalPage = total / count + 1;
        }
        return totalPage;
    }

    //计算最后一页的开始
    public int getLast() {
        int last;
        if (0 == total % 5) {
            last = total - count;
        } else {
            last =total - total % count;
        }
        last = Math.max(last, 0);
        return last;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                '}';
    }
}
