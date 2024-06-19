package com.yuyu.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuyu.transaction.domain.po.Transaction;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TransactionMapper extends BaseMapper<Transaction> {
    @Update("update transaction set type = ${type} where tid = ${tid}")
    void updateTypeByTid(@Param("tid") Long tid,@Param("type") Integer type);
    @Update("update transaction set type = ${type} where uid = ${uid} and type != ${type}")
    void updateTypeByUid(@Param("uid") Long uid,@Param("type") Integer type);

    @Select("select tid,uid,content,type,create_at,update_at,delete_at from transaction where uid = ${uid} and type = ${type} limit ${begin},${end}")
    List<Transaction> listTransactionById(@Param("uid") Long uid, @Param("type") Integer type, @Param("begin") Integer begin, @Param("end") Integer end);
    @Select("select tid,uid,content,type,create_at,update_at,delete_at from transaction where uid = ${uid} limit ${begin},${end}")
    List<Transaction>  listAllById(@Param("uid") Long uid,@Param("begin") Integer begin,@Param("end") Integer end);

    @Select("select tid,uid,content,type,create_at,update_at,delete_at from transaction where uid = ${uid} and type = ${type} and content like '%${key}%' limit ${begin},${end}")
    List<Transaction>  listTransactionByIdWithKey(@Param("uid") Long uid,@Param("type") Integer type,@Param("key") String keyword,@Param("begin") Integer begin,@Param("end") Integer end);
    @Select("select tid,uid,content,type,create_at,update_at,delete_at from transaction where uid = ${uid} and content like '%${key}%' limit ${begin},${end}")
    List<Transaction>  listAllByIdWithKey(@Param("uid") Long uid,@Param("key") String keyword,@Param("begin") Integer begin,@Param("end") Integer end);

    @Update("update transaction set deleted = 0 where tid = ${tid}")
    void deleteByTid(@Param("tid") Long tid);
    @Update("update transaction set deleted = 0 where uid = ${uid} and type = ${type}")
    void deleteByUid(@Param("uid") Long uid,@Param("type") Integer type);
    @Update("update transaction set deleted = 0 where uid = ${uid}")
    void deleteAllByUid(@Param("uid") Long uid);
}
