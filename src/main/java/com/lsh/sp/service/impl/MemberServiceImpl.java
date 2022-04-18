package com.lsh.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.mapper.BookMapper;
import com.lsh.sp.mapper.EvaluationMapper;
import com.lsh.sp.mapper.MemberMapper;
import com.lsh.sp.mapper.MemberReadStateMapper;
import com.lsh.sp.pojo.Book;
import com.lsh.sp.pojo.Evaluation;
import com.lsh.sp.pojo.Member;
import com.lsh.sp.pojo.MemberReadState;
import com.lsh.sp.pojo.vo.UserReadState;
import com.lsh.sp.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 16:16 2022/1/12
 **/
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberReadStateMapper memberReadStateMapper;
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private BookMapper bookMapper;
    /**
     * 会员注册,创建新会员
     *
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     * @return 新会员对象
     */
//    @Override
//    public Member createMember(String username, String password, String nickname) {
//        QueryWrapper<Member> queryWrapper=new QueryWrapper<Member>();
//        queryWrapper.eq("username",username);
//        List<Member> memberList = memberMapper.selectList(queryWrapper);
//        if (memberList.size()>0)
//            throw new BussinessException("E01","用户名已存在！");
//        Member member=new Member();
//        member.setUsername(username);
//        member.setNickname(nickname);
//        //盐值
//        int salt=new Random().nextInt(1000)+1000;
//        String md5 = MD5Utils.md5Digest(password, salt);
//        member.setSalt(salt);
//        member.setPassword(md5);
//        member.setCreateTime(new Date());
//        memberMapper.insert(member);
//        return member;
//    }

    /**
     * 登录检查
     *
     *
//     */
//    @Override
//    public Member checkLogin(String username, String password) {
//        QueryWrapper<Member> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("username",username);
//        Member member = memberMapper.selectOne(queryWrapper);
//        if (member==null){
//            throw new BussinessException("E02","用户不存在");
//        }
//        String md5=MD5Utils.md5Digest(password, member.getSalt());
//        if (!md5.equals(member.getPassword()))
//            throw new BussinessException("E03","密码错误");
//        return member;
//    }

    @Override
    public ResponseResult selectReadStateByMap(Long bookId) {
        Map data=new HashMap();
        UserReadState userReadState1 = memberReadStateMapper.selectReadStateByMap(bookId, 1);
        if (userReadState1!=null)
            data.put("toRead",userReadState1.getCount());
        else
            data.put("toRead",0);

        UserReadState userReadState2 = memberReadStateMapper.selectReadStateByMap(bookId, 2);
        if (userReadState2!=null)
            data.put("reading",userReadState2.getCount());
        else
            data.put("reading",0);
        UserReadState userReadState3 = memberReadStateMapper.selectReadStateByMap(bookId, 3);
        if (userReadState3!=null)
            data.put("readed",userReadState3.getCount());
        else
            data.put("readed",0);

        return new ResponseResult(200,"success",data);
    }

    /**
     * 获取阅读状态
     *
     * @param memberId 会员编号
     * @param bookId   图书编号
     * @return 阅读状态对象
     */
    @Override
    public MemberReadState selectMemberReadState(Long memberId, Long bookId) {
        QueryWrapper<MemberReadState> queryWrapper=new QueryWrapper<MemberReadState>();
        queryWrapper.eq("book_id",bookId);
        queryWrapper.eq("member_id",memberId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(queryWrapper);
        return memberReadState;
    }

    /**
     * 更新阅读状态
     *
     * @param memberId  会员编号
     * @param bookId    图书编号
     * @param readState 阅读状态
     * @return 阅读状态对象
     */
    @Override
    public MemberReadState updateMemberReadState(Long memberId, Long bookId, Integer readState) {
        QueryWrapper<MemberReadState> queryWrapper=new QueryWrapper<MemberReadState>();
        queryWrapper.eq("member_id",memberId);
        queryWrapper.eq("book_id",bookId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(queryWrapper);
        if (memberReadState==null){
            memberReadState=new MemberReadState();
            memberReadState.setBookId(bookId);
            memberReadState.setMemberId(memberId);
            memberReadState.setReadState(readState);
            memberReadState.setCreateTime(new Date());
            memberReadStateMapper.insert(memberReadState);
        }else {
            memberReadState.setReadState(readState);
            memberReadStateMapper.updateById(memberReadState);
        }
        return memberReadState;
    }

    /**
     * 发布新的短评
     *
     * @param memberId 会员编号
     * @param bookId   图书编号
     * @param score    评分
     * @param content  短评内容
     * @return 短评对象
     */
    @Override
    public Evaluation evaluate(Long memberId, Long bookId, Integer score, String content) {
        Evaluation evaluation=new Evaluation();
        evaluation.setMemberId(memberId);
        evaluation.setBookId(bookId);
        evaluation.setScore(score);
        evaluation.setContent(content);
        evaluation.setCreateTime(new Date());
        evaluation.setState("enable");
        evaluation.setEnjoy(0);
        Member member = memberMapper.selectById(memberId);
        evaluation.setMember(member);
        Book book = bookMapper.selectById(bookId);
        evaluation.setBook(book);
        evaluationMapper.insert(evaluation);
        return evaluation;
    }

    /**
     * 短评点赞
     *
     * @param evaluationId 短评编号
     * @return 短评对象
     */
    @Override
    public Evaluation enjoy(Long evaluationId) {
        Evaluation evaluation=evaluationMapper.selectById(evaluationId);
        evaluation.setEnjoy(evaluation.getEnjoy()+1);
        evaluationMapper.updateById(evaluation);
        return evaluation;
    }
}
