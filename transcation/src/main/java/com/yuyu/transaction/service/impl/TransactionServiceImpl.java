package com.yuyu.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyu.common.Utils.UserContext;
import com.yuyu.common.domain.Result;
import com.yuyu.common.exception.BussinessException;
import com.yuyu.transaction.domain.po.Transaction;
import com.yuyu.transaction.mapper.TransactionMapper;
import com.yuyu.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction>implements TransactionService {
    @Override
    public Result saveTransaction(String content) {
        // 1.检查内容是否为空
        if(!StringUtils.hasText(content)){
            throw new BussinessException(Result.PROJECT_BUSSINESS_ERROR,"请输入内容");
        }

        // 2.获取用户id
        Transaction transaction = new Transaction(UserContext.getUser(),content, LocalDateTime.now());
        baseMapper.insert(transaction);
        return new Result(Result.PROJECT_SUCCESS,"success");
    }

    @Override
    public Result updateTransaction(String tid, Integer type) {
        // 1.判断类型数字是否正确
        if(type!=1&&type!=2){
            throw new BussinessException(Result.PROJECT_BUSSINESS_ERROR,"type只可为1，2");
        }
        // 2.判断是否是单条转化还是多条转化
        if(StringUtils.hasText(tid)){
            // 说明是单条转化
            if(type==1){
                // 将代办转化成完成
                baseMapper.updateTypeByTid(Long.valueOf(tid),0);
                log.info("已将代办转化成完成");
            }else {
                // 将完成转化成代办
                baseMapper.updateTypeByTid(Long.valueOf(tid),1);
                log.info("已将完成转化成代办");
            }
        }else {
            // 说明是多条转化
            if(type==1){
                // 将代办转化成完成
                baseMapper.updateTypeByUid(UserContext.getUser(),0);
                log.info("已将代办转化成完成");
            }else {
                // 将完成转化成代办
                baseMapper.updateTypeByUid(UserContext.getUser(),1);
                log.info("已将完成转化成代办");
            }
        }
        return new Result(Result.PROJECT_SUCCESS,"success");
    }

    @Override
    public Result<List<Transaction>> listTransactionById(Integer type, String keyWord, Integer pageNum, Integer pageSize) {
        List<Transaction> list = new ArrayList<>();
        // 1.通过num和size获取所需begin
        Integer begin = (pageNum-1)*pageSize;
        // 2.查看是否有关键字
        if (StringUtils.hasText(keyWord)){
            // 说明含有关键字
            if(type==1){
                // 说明找代办的
                List<Transaction> transactions = baseMapper.listTransactionByIdWithKey(UserContext.getUser(), 1, keyWord,begin, pageSize);
                System.out.println(transactions);
                return new Result<List<Transaction>>(Result.PROJECT_SUCCESS,"success",transactions);
            }else if(type==2){
                // 说明找完成的
                List<Transaction> transactions = baseMapper.listTransactionByIdWithKey(UserContext.getUser(), 0,keyWord,begin, pageSize);
                return new Result<List<Transaction>>(Result.PROJECT_SUCCESS,"success",transactions);
            }else{
                // 说明找全部的
                List<Transaction> transactions = baseMapper.listAllByIdWithKey(UserContext.getUser(),keyWord, begin, pageSize);
                return new Result<List<Transaction>>(Result.PROJECT_SUCCESS,"success",transactions);
            }
        }else {
            // 说明不含关键字
            if(type==1){
                // 说明找代办的
                List<Transaction> transactions = baseMapper.listTransactionById(UserContext.getUser(), 1, begin, pageSize);
                return new Result<List<Transaction>>(Result.PROJECT_SUCCESS,"success",transactions);
            }else if(type==2){
                // 说明找完成的
                List<Transaction> transactions = baseMapper.listTransactionById(UserContext.getUser(), 0, begin, pageSize);
                return new Result<List<Transaction>>(Result.PROJECT_SUCCESS,"success",transactions);
            }else{
                // 说明找全部的
                List<Transaction> transactions = baseMapper.listAllById(UserContext.getUser(), begin, pageSize);
                return new Result<List<Transaction>>(Result.PROJECT_SUCCESS,"success",transactions);
            }
        }
    }

    @Override
    public Result removeTransaction(String tid, Integer type) {
        if(StringUtils.hasText(tid)){
            // 说明为删除单条的
            baseMapper.deleteByTid(Long.valueOf(tid));
            return new Result(Result.PROJECT_SUCCESS,"success");
        }else{
            // 说明为删除多条的
            if(type==1){
                // 删除代做的
                baseMapper.deleteByUid(UserContext.getUser(),1);
                return new Result(Result.PROJECT_SUCCESS,"success");
            }else if(type==2){
                // 删除完成的
                baseMapper.deleteByUid(UserContext.getUser(),0);
                return new Result(Result.PROJECT_SUCCESS,"success");
            }else {
                // 删除全部的
                baseMapper.deleteAllByUid(UserContext.getUser());
                return new Result(Result.PROJECT_SUCCESS,"success");
            }
        }
    }
}
