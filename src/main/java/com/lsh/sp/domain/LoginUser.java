package com.lsh.sp.domain;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 17:50 2022/4/7
 **/

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;
    //存储权限信息
    private List<String> permissions;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }
//    不序列化
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities!=null)
            return authorities;
        //把permissions封装成SimpleGrantedAuthority
        authorities=new ArrayList<>();
        for (String permission :
                permissions) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
            authorities.add(authority);
        }
//        List<GrantedAuthority> authorities=permissions.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());


        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}