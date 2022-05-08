package com.lsh.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lsh.sp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 18:51 2022/5/6
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("topic")
public class Topic {
    @TableId(type = IdType.AUTO)
    private Long id;
//    内容
    private String content;
    private Date createtime;
    private Date updatetime;
//    用户id
    private Long userid;
//    浏览量
    private Long visits;
//    点赞数
    private Long thumbup;
//    评论数
    private Long reply;
//    标签
    private Long labelId;
    @TableField(exist = false)
    private List<Comment> comments;
    @TableField(exist = false)
    private User user;

}
