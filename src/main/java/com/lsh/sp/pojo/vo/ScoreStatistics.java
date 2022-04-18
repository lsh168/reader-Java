package com.lsh.sp.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 13:13 2022/4/16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScoreStatistics {
    private String bookName;
    private Integer score;
    private Integer sum;

}
