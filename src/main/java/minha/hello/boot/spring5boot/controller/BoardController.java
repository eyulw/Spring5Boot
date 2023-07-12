package minha.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import minha.hello.boot.spring5boot.service.BoardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
    Logger logger = LogManager.getLogger(BoardController.class);
    final BoardService bsrv;
    @GetMapping("/list/{cpg}")
    public String list(Model m, @PathVariable Integer cpg){
        m.addAttribute("bds",bsrv.readBoard(cpg));
        m.addAttribute("psnum",10*((cpg-1)/10)+1);
        m.addAttribute("allpg",bsrv.getCountPages());
        m.addAttribute("cpg",cpg);
        logger.info("board/list 호출");
        return "board/list";
    }

    @GetMapping("/view/{bno}")
    public String view(Model m, @PathVariable String bno){
        m.addAttribute("bd",bsrv.readOneBoard(bno));
        logger.info("board/view 호출");
        return "board/view";
    }
}
