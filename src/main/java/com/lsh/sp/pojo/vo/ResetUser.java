package com.lsh.sp.pojo.vo;

import com.lsh.sp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 16:05 2022/4/28
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ResetUser {
    private String userName;
    private String password;
    private String newPassword;
}
