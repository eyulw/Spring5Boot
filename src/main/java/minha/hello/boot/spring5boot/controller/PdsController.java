package minha.hello.boot.spring5boot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pds")
@Controller
public class PdsController {
    Logger logger = LogManager.getLogger(PdsController.class);

    @GetMapping("/list")
    public String list(){
        logger.info("pds/list 호출");
        return "pds/list";
    }
}
