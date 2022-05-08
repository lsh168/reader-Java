package com.lsh.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 12:30 2022/5/7
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("label")
public class Label {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String labelName;
    private String state;
    private Long count;
    private String recommend;
    private Long fans;
    private String img;
}
