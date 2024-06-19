package com.yuyu.transaction.service;

import com.yuyu.common.domain.Result;
import com.yuyu.transaction.domain.po.Transaction;

import java.util.List;

public interface TransactionService {
    /**
     * 保存事务
     * @param content 内容
     * @return 操作结果
     */
    Result saveTransaction(String content);

    /**
     * 修改事务状态
     * @param tid 事务id
     * @param type 修改的类型
     * @return 操作结果
     */
    Result updateTransaction(String tid, Integer type);

    /**
     * 获取事务的列表
     * @param type 获取的种类
     * @param keyWord 关键词
     * @param pageNum 页面的页数
     * @param pageSize 页面的大小
     * @return 操作结果
     */
    Result<List<Transaction>> listTransactionById(Integer type, String keyWord, Integer pageNum, Integer pageSize);

    /**
     * 删除事务
     * @param tid 事务的tid
     * @param type 获取的种类
     * @return 操作结果
     */
    Result removeTransaction(String tid, Integer type);
}
