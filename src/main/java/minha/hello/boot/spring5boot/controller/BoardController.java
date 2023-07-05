package minha.hello.boot.spring5boot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
public class BoardController {
    Logger logger = LogManager.getLogger(BoardController.class);
    @GetMapping("/list")
    public String list(){
        logger.info("board/list 호출");
        return "board/list";
    }
}
