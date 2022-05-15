package com.lsh.sp.controller;

import com.lsh.sp.domain.ResponseResult;
import com.lsh.sp.pojo.Evaluation;
import com.lsh.sp.pojo.Member;
import com.lsh.sp.service.MemberService;
import com.lsh.sp.service.exception.BussinessException;
import com.lsh.sp.utils.HTTPUserId;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author luminous
 * @Description //TODO
 * @Date 17:06 2022/1/12
 **/
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;



    //会员状态禁用
    @PostMapping("updateStatus")
    public ResponseResult updateStatus(Member member){
        ResponseResult result = memberService.updateStatus(member);
        return result;
    }



    /**
     * @Author luminous
     * @Description //TODO 注册会员
     * @Date 11:48 2022/1/22
     * @param vc: 用户输入的验证码
    * @param username: 用户名
    * @param password: 密码
    * @param nickname: 昵称
    * @param request: 后端返回的验证码
     * @return java.util.Map
    **/
//    @PostMapping("registe")
//    public Map registe(String vc, String username, String password , String nickname, HttpServletRequest request){
//        //正确验证码
//        String verifyCode=(String)request.getSession().getAttribute("kaptchaVerifyCode");
//        //验证码对比
//        Map result=new HashMap();
//        if (vc==null||verifyCode==null||!vc.equalsIgnoreCase(verifyCode)){
//            result.put("code", "VC01");
//            result.put("msg", "验证码错误");
//        }else {
//            try{
//                memberService.createMember(username,password,nickname);
//                result.put("code",0);
//                result.put("msg","success");
//            }catch (BussinessException e){
//                e.getStackTrace();
//                result.put("code",e.getCode());
//                result.put("msg",e.getMsg());
//            }
//        }
//        return result;
//    }

//    @PostMapping("check_login")
//    public Map checkLogin( String username, String password, String vc, HttpSession session){
//        //正确的验证码
//        String verifyCode=(String) session.getAttribute("kaptchaVerifyCode");
//        Map result=new HashMap();
//        if (vc==null||verifyCode==null||!vc.equalsIgnoreCase(verifyCode)){
//            result.put("code","vc01");
//            result.put("msg","验证码错误");
//        }else{
//            try{
//                Member member = memberService.checkLogin(username, password);
//                session.setAttribute("loginMember",member);
//                result.put("code",0);
//                result.put("msg","success");
//            }catch (BussinessException e){
//                result.put("code",e.getCode());
//                result.put("msg",e.getMsg());
//            }
//        }
//        return result;
//    }

    /**
     * @Author luminous
     * @Description //TODO 想看/看过的阅读状态
     * @Date 15:41 2022/1/2
 * @param bookId: 图书编号
 * @param readState: 阅读状态 1想看 2 看过
     * @return java.util.Map
    **/
    @PostMapping("update_read_state")
    public Map updateReadState(HttpServletRequest request,Long bookId,Integer readState){
        Long userId = HTTPUserId.getUserId(request);
        Map result=new HashMap();
        try{
            memberService.updateMemberReadState(userId, bookId, readState);
            result.put("code",0);
            result.put("msg","success");
        }catch (BussinessException e){
            result.put("code",e.getCode());
            result.put("msg",e.getMsg());
        }
        return result;
    }
    /**
     * @Author luminous
     * @Description //TODO  写短评
     * @Date 18:24 2022/1/22
     * @param memberId:
     * @param bookId: 图书编号
     * @param score: 评分
     * @param content: 评论内容
     * @return java.util.Map
    **/
    @PostMapping("evaluate")
    public Map evaluate(Long memberId,Long bookId,Integer score,String content){
        Map result=new HashMap();
        try{
            Evaluation evaluation = memberService.evaluate(memberId, bookId, score, content);
            result.put("code","0");
            result.put("msg","success");
            result.put("evaluation",evaluation);
        }catch (BussinessException e){
            e.printStackTrace();
            result.put("code",e.getCode());
            result.put("msg",e.getMsg());
        }

        return result;
    }
    /**
     * 评论点赞
     * @param evaluationId
     * @return
     */
    @PostMapping("enjoy")
    public Map enjoy(@Param("evaluationId") Long evaluationId){
        Map result = new HashMap();
        try {
            Evaluation eva = memberService.enjoy(evaluationId);
            result.put("code", "0");
            result.put("msg", "success");
            result.put("evaluation", eva);
        }catch(BussinessException ex){
            ex.printStackTrace();
            result.put("code", ex.getCode());
            result.put("msg", ex.getMsg());
        }
        return result;
    }

}
