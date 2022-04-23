package com.lsh.sp.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 9:48 2022/4/21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EvaluationBookMemberVo {
//    SELECT m.nickname,m.username,
//    b.book_name,
//    e.content,e.enjoy,e.score,e.create_time,e.disable_reason,
//    e.disable_time,e.state,e.evaluation_id
//    FROM evaluation e,member m,book b
//    where e.member_id=m.member_id AND b.book_id=e.book_id
    private String nickname;
    private String username;
    private String bookName;
    private String content;
    private Integer enjoy;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private String disableReason;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date disableTime;
    private String state;
    private Long evaluationId;
}
