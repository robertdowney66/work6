package com.yuyu.transaction.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("transaction")
public class Transaction implements Serializable {
    private static final long serialVersionUID = 2396493274899327498L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long tid;

    private Long uid;

    private String content;

    /**
     * 标记是否完成，1-未完成，0-已完成
     */
    private Integer type;

    @TableLogic
    @JsonIgnore
    private Integer deleted;

    public Transaction() {
    }

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public Transaction(Long uid, String content, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt) {
        this.uid = uid;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }

    public Transaction(Long uid, String content, LocalDateTime createAt) {
        this.uid = uid;
        this.content = content;
        this.createAt = createAt;
    }
}
