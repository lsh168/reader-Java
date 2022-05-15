package com.lsh.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("member")
public class Member {
    @TableId(type= IdType.AUTO)
    private Long memberId;
    private String username;
    private String password;
    private Integer salt;
    private String nickname;
    private Date createTime;
    private String avatar;
    private char status;
}
