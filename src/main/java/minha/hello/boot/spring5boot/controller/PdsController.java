package minha.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import minha.hello.boot.spring5boot.model.Pds;
import minha.hello.boot.spring5boot.model.PdsComment;
import minha.hello.boot.spring5boot.service.PdsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RequestMapping("/pds")
@Controller
@RequiredArgsConstructor
public class PdsController {
    Logger logger = LogManager.getLogger(PdsController.class);
    final PdsService psrv;

    @GetMapping("/list/{cpg}")
    public String list(Model m, @PathVariable Integer cpg){
        m.addAttribute("pds",psrv.readPds(cpg));
        m.addAttribute("psnum",10*((cpg-1)/10)+1);
        m.addAttribute("cntpg",psrv.getCountPages());
        m.addAttribute("cpg",cpg);
        //만일, cpg가 cntpg보다 크다면
        //1페이지로 강제 이동
        if(cpg > (int)m.getAttribute("cntpg"))
            return "redirect:/pds/list/1";
        logger.info("pds/list 호출");
        return "pds/list";
    }

    @GetMapping("/write")
    public String write(){
        logger.info("pds/write 호출");
        return "pds/write";
    }

    @PostMapping("/write")
    public String writeok(Pds p, MultipartFile attach){
//        text는 Pds p로, 첨부파일은 MultipartFile attach로 넘어옴
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

    @GetMapping("/view/{pno}")
    public String view(Model m, @PathVariable String pno){
        m.addAttribute("p",psrv.readOnePds(pno));   //본문글
        m.addAttribute("pcs",psrv.readPdsComment(pno));  //댓글, 대댓글
        logger.info("pds/view 호출");
        return "pds/view";
    }

    @PostMapping("/cmt/write")
    public String cmtwriteok(PdsComment pc){
        logger.info("pds/cmt/write 호출");
        String returnPage="redirect:/pds/fail";
        /*작성한 댓글을 댓글에 저장*/
        if(psrv.newPdsComment(pc)){
            /*작성한 댓글을 확인하기 위해 바로 본문 출력*/
            returnPage="redirect:/pds/view/"+pc.getPno();
        }
        return returnPage;
    }
    @PostMapping("/reply/write")
    public String rpywriteok(PdsComment pc){
        logger.info("pds/reply/write 호출");
        String returnPage="redirect:/pds/fail";
        /*작성한 대댓글을 테이블에 저장*/
        if(psrv.newPdsReply(pc)){
            /*작성한 대댓글을 확인하기 위해 바로 본문 출력*/
            returnPage="redirect:/pds/view/"+pc.getPno();
        }
        return returnPage;
    }

    @GetMapping("/down/{pno}")
    public ResponseEntity<Resource> down(@PathVariable String pno){
        logger.info("pds/down 호출");
        /*업로드한 파일에 대한 파일명 알아냄*/
        String fname = psrv.readOnePdsAttach(pno);
        /*알아낸 파일명을 이용해서 헤더와 리소스 객체 생성*/
        Map<String,Object> objs=psrv.getHeaderResource(fname);
        return ResponseEntity.ok().headers((HttpHeaders) objs.get("header")).body((UrlResource)objs.get("resource"));
    }
}
