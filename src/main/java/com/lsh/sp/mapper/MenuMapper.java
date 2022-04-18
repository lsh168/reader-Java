package com.lsh.sp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.sp.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 15:56 2022/4/8
 **/
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}
