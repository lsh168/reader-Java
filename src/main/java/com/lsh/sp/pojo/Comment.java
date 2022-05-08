package com.lsh.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 9:39 2022/5/7
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("comment")
public class Comment {
    @TableId(type=IdType.AUTO)
    private Long id;
    private Long userId;
    private String userName;
    private Long articleId;
    private String articleTitle;
    private Long parentCommentId;
    private Long parentCommentUserId;
    private Long replyCommentId;
    private Long replyCommentUserId;
    private Integer commentLevel;
    private String content;
    private Integer status;
    private Integer praiseNum;
    private Integer topStatus;
    private Timestamp createTime;

}
