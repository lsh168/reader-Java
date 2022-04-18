package com.lsh.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@TableName("evaluation")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Evaluation {
    @TableId(type= IdType.AUTO)
    private Long evaluationId;
    private Long bookId;
    private String content;
    private Integer score;
    private Long memberId;
    private Date createTime;
    private Integer enjoy;
    private String state;
    private String disableReason;
    private Date disableTime;
    @TableField(exist = false) //说明book属性没有对应字段,不会参与到sql自动生成
    private Book book;
    @TableField(exist = false)
    private Member member;
}
