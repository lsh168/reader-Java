package com.lsh.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 图书实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("book")
public class Book {
    @TableId(type= IdType.AUTO)
    private Long bookId;
    private String bookName;
    private String subTitle;
    private String author;
    private String cover;
    private String description;
    private Long categoryId;
    private Float evaluationScore;
    private Integer evaluationQuantity;
    private Integer deleted;

}
