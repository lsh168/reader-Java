package com.lsh.sp.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 20:10 2022/4/16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserReadState {
    private Long bookId;
    private Integer readState;
    private Integer count;
}
