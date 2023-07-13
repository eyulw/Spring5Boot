package minha.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import minha.hello.boot.spring5boot.model.Board;
import minha.hello.boot.spring5boot.service.BoardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
    Logger logger = LogManager.getLogger(BoardController.class);
    final BoardService bsrv;
    @GetMapping("/list/{cpg}")
    public String list(Model m, @PathVariable Integer cpg){
        int cntpg =bsrv.getCountPages();
        m.addAttribute("bds",bsrv.readBoard(cpg));
        m.addAttribute("psnum",10*((cpg-1)/10)+1);
        m.addAttribute("cntpg",cntpg);
        m.addAttribute("cpg",cpg);
        //만일, cpg가 cntpg보다 크다면
        //1페이지로 강제 이동
        /* if(cpg > (int)m.getAttribute("cntpg")) */
        if(cpg > cntpg)
            return "redirect:/board/list/1";

        logger.info("board/list 호출");
        return "board/list";
    }

    @GetMapping("/view/{bno}")
    public String view(Model m, @PathVariable String bno){
        m.addAttribute("bd",bsrv.readOneBoard(bno));
        logger.info("board/view 호출");
        return "board/view";
    }

    @GetMapping("/write")
    public String write(){
        logger.info("board/write 호출");
        return "board/write";
    }

    @PostMapping("/write")
    public String writeok(Board b){
        logger.info("board/writeok 호출");
        String returnPage="redirect:/board/fail";
        if(bsrv.saveBoard(b))
            returnPage="redirect:/board/list/1";
        return returnPage;
    }

    @GetMapping("/delete/{bno}")
    public String delete(@PathVariable String bno){
        logger.info("board/delete 호출");
        String returnPage="redirect:/board/fail";
        if(bsrv.removeBoard(bno))
            returnPage="redirect:/board/list/1";
        return returnPage;
    }


    @GetMapping("/find/{cpg}/{findtype}/{findkey}")
    public String find(Model m, @PathVariable Integer cpg,@PathVariable String findtype, @PathVariable String findkey){
        m.addAttribute("bds",bsrv.readFindBoard(cpg,findtype,findkey));
        m.addAttribute("psnum",10*((cpg-1)/10)+1);
        m.addAttribute("cntpg",bsrv.getCountPages());
        m.addAttribute("cpg",cpg);
        //만일, cpg가 cntpg보다 크다면
        //1페이지로 강제 이동
        if(cpg>(int)m.getAttribute("cntpg"))
            return "redirect:/board/list/1";

        logger.info("board/find 호출");
        return "board/list";
    }

}
