package com.bhvote.database.utils;

import java.util.List;

public class PageUtils {

    /**
     * 对集合进行分页
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示的记录数
     * @param list     待分页的集合
     * @param <T>      集合中元素的类型
     * @return 分页后的集合
     */
    public static <T> PageResult<T> page(int pageNum, int pageSize, List<T> list) {
        // 计算总记录数和总页数
        long total = list.size();
        int totalPages = (int) (total % pageSize == 0 ? total / pageSize : total / pageSize + 1);

        // 判断页码是否越界
        if (pageNum < 1) {
            pageNum = 1;
        } else if (pageNum > totalPages) {
            pageNum = totalPages;
        }

        // 计算起始和结束位置
        int startIndex = (pageNum - 1) * pageSize;
        int endIndex = pageNum * pageSize > list.size() ? list.size() : pageNum * pageSize;

        // 分页查询
        List<T> pageList = list.subList(startIndex, endIndex);

        // 封装分页结果
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setList(pageList);
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setTotal(total);

        return pageResult;
    }
}