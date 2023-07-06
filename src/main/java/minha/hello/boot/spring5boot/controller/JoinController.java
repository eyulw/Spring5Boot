package minha.hello.boot.spring5boot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/join")
@Controller
public class JoinController {
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

    @GetMapping("/joinme")
    public String joinme(){
        logger.info("join/joinme 호출");
        return "join/joinme";
    }

    @GetMapping("/joinok")
    public String joinok(){
        logger.info("join/joinok 호출");
        return "join/joinok";
    }
}
