package minha.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import minha.hello.boot.spring5boot.model.Pds;
import minha.hello.boot.spring5boot.service.PdsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/pds")
@Controller
@RequiredArgsConstructor
public class PdsController {
    Logger logger = LogManager.getLogger(PdsController.class);
    final PdsService psrv;

    @GetMapping("/list")
    public String list(){
        logger.info("pds/list 호출");
        return "list";
    }

    @GetMapping("/write")
    public String write(){
        logger.info("pds/write 호출");
        return "pds/write";
    }

    @PostMapping("/write")
    public String writeok(Pds p, MultipartFile attach){
        logger.info("pds/writeok 호출");
        String returnPage="redirect:/pds/fail";
        /*작성한 게시글을 먼저 디비에 저장하고 글번호를 알아냄*/
        int pno=psrv.newPds(p);
        /*알아낸 pno를 이용해서 첨부파일 처리(디비저장, 업로드)*/
        if(!attach.isEmpty()){  //첨부파일이 존재한다면
            psrv.newPdsAttach(attach,pno);
            returnPage="redirect:/pds/list/1";
        }
        return returnPage;
    }
}
