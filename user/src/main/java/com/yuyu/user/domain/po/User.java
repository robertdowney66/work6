package com.yuyu.user.domain.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = -40356785423868312L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    @JsonIgnore
    private String password;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private LocalDateTime deleteAt;

    public User() {
    }

    public User(String name,String password, LocalDateTime createAt) {
        this.name = name;
        this.password = password;
        this.createAt = createAt;
    }

    public User(String name,String password, LocalDateTime createAt, LocalDateTime updateAt, LocalDateTime deleteAt) {
        this.name = name;
        this.password = password;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.deleteAt = deleteAt;
    }
}
