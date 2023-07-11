package minha.hello.boot.spring5boot.controller;

import minha.hello.boot.spring5boot.model.CheckMe;
import minha.hello.boot.spring5boot.model.Member;
import minha.hello.boot.spring5boot.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("/join")
@Controller
public class JoinController {
    @Autowired MemberService msrv;
    Logger logger = LogManager.getLogger(JoinController.class);
    @GetMapping("/agree")
    public String agree(){
        logger.info("join/agree 호출");
        return "join/agree";
    }

    @GetMapping("/checkme")
    public String checkme(){
        logger.info("join/checkme 호출");
        return "join/checkme";
    }

    @PostMapping("/checkme")
    public String checkmeok(CheckMe checkme,HttpSession sess){
        /*checkme에 보낸 개인정보 세션에 저장하고 joinme로 이동*/
        logger.info("join/checkmeok 호출");
        String viewPage = "redirect:/join/checkme";

        if(checkme.getName2()!=null){
            sess.setAttribute("checkme",checkme);
            viewPage="redirect:/join/joinme";
        }
        return viewPage;
    }


    @GetMapping("/joinme")
    public String joinme(HttpSession sess){
        logger.info("join/joinme 호출");
        if(sess.getAttribute("checkme")==null)
            return "redirect:/join/agree";
        return "join/joinme";
    }

    @PostMapping("/joinme")
    public String joinmeok(Member m){
        logger.info("join/joinmeok 호출");
        String viewPage = "redirect:/join/fail";
        if(msrv.saveMember(m))
            viewPage="redirect:/join/joinok";
        return viewPage;
    }

    // 우편번호 검색
    // /join/zipcode?dong=동이름
    // /join/zipcode/동이름
    // 검색결과는 뷰페이지 없이 바로 응답으로 출력 : RESTful 방식
    // 서블릿에서 제공하는 HttpServletResponse 객체를 이용하면
    // 스프링의 뷰리졸버 없이 바로 응답으로 출력할 수 있음
    // 단, 응답유형은 JSON 형식으로 함
    @GetMapping("/zipcode")
    @ResponseBody
    public void findzip(String dong, HttpServletResponse res) throws IOException {

//        우편번호 조회결과를 JSON 형식으로 보냄
//        따라서, 응답유형을 JSON 형식으로 지정
        res.setContentType("application/json; charset=utf-8");
//        검색된 결과를 뷰없이 바로 응답(response)으로 출력
        res.getWriter().print(msrv.findzip(dong));
    }

    // 아이디 중복검사
    // /join/checkuid?uid=아이디
    // /join/checkuid/아이디
    //@PathVariable을 해줘야 변수로 넘어옴
    @GetMapping("/checkuid/{uid}")
    @ResponseBody
    public void checkuid(@PathVariable String uid, HttpServletResponse res) throws IOException {
        res.setContentType("application/json; charset=utf-8");
        res.getWriter().print(msrv.checkuid(uid));
    }

    @GetMapping("/joinok")
    public String joinok(){
        logger.info("join/joinok 호출");
        return "join/joinok";
    }

    //로그인 처리
    @PostMapping("/login")
    public String login(Member m, HttpSession sess){
        logger.info("login 호출");
        String returnPage="redirect:/loginfail";
        m = msrv.readOneMember(m);
        if(m!=null){
            sess.setAttribute("member",m);
            returnPage="redirect:/";
        }
        return returnPage;
    }

    @GetMapping("/logout")
    public String logout(HttpSession sess){
        logger.info("logout 호출");
        sess.invalidate();
        return "redirect:/";
    }
}
