package com.lsh.sp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsh.sp.pojo.MemberReadState;
import com.lsh.sp.pojo.vo.UserReadState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 20:41 2022/1/12
 **/
@Repository
public interface MemberReadStateMapper extends BaseMapper<MemberReadState> {
    UserReadState selectReadStateByMap(@Param("bookId") Long bookId, @Param("readState") Integer readState);
}
