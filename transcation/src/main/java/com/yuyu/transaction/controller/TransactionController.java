package com.yuyu.transaction.controller;

import com.yuyu.common.domain.Result;
import com.yuyu.transaction.domain.po.Transaction;
import com.yuyu.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * 保存事务
     * @param content 保存事务的内容
     * @return 操作结果
     */
    @PostMapping()
    public Result saveTransaction(@RequestParam("content") String content){
        return transactionService.saveTransaction(content);
    }

    /**
     * 将一条/所有的代办转换成完成，或者将完成转化成代办
     * @param tid 事务的id
     * @param type 1-表示将代办转化成完成，2-表示将完成转化成代办
     * @return 返回操作的结果
     */
    @PutMapping()
    public Result updateTransaction(@RequestParam(value = "transaction_id",required = false) String tid,@RequestParam("type") Integer type){
        return transactionService.updateTransaction(tid,type);
    }

    /**
     * 查询事务
     * @param type 1-代表查询代办的 2-代表查询完成的 3-代表查询全部的
     * @param keyWord 查询所需的关键词
     * @param pageNum 开始页数
     * @param pageSize 页面大小
     * @return 操作结果
     */
    @GetMapping()
    public Result<List<Transaction>> listTransactionById(@RequestParam("type") Integer type, @RequestParam(value = "keyword",required = false) String keyWord,
                                            @RequestParam(value = "page_num",required = false) Integer pageNum, @RequestParam(value = "page_size",required = false) Integer pageSize){
        return transactionService.listTransactionById(type,keyWord,pageNum,pageSize);
    }

    /**
     * 删除事务
     * @param tid 删除的事务，如果不传id，按照type进行多目标删除
     * @param type 删除类型 1-未读 2-已读 3-全部
     * @return 操作结果
     */
    @DeleteMapping()
    public Result removeTransaction(@RequestParam(value = "transaction_id",required = false) String tid,@RequestParam(value = "type",required = false) Integer type){
        return transactionService.removeTransaction(tid,type);
    }





}
