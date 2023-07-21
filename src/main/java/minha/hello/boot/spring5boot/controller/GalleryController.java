package minha.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import minha.hello.boot.spring5boot.model.Gallery;
import minha.hello.boot.spring5boot.model.Pds;
import minha.hello.boot.spring5boot.service.GalleryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/gallery")
@Controller
@RequiredArgsConstructor
public class GalleryController {
    Logger logger = LogManager.getLogger(GalleryController.class);
    final GalleryService gsrv;

    @GetMapping("/list/{cpg}")
    public String list(Model m,@PathVariable Integer cpg){
        logger.info("gallery/list 호출");
        m.addAttribute("gal",gsrv.readGallery(cpg));
        m.addAttribute("psnum",10*((cpg-1)/10)+1);
        m.addAttribute("cntpg",gsrv.countGallery());
        m.addAttribute("cpg",cpg);
/*        if(cpg > (int)m.getAttribute("cntpg"))
            return "redirect:/gallery/list/1";*/
        return "gallery/list";
    }

    @GetMapping("/write")
    public String write(){
        logger.info("gallery/write 호출");
        return "gallery/write";
    }

    @PostMapping("/write")
    public String writeok(Gallery g, List<MultipartFile> attachs){
        logger.info("gallery/writeok 호출");
        String returnPage="redirect:/gallery/fail";
        /*작성한 게시글을 먼저 디비에 저장하고 글번호를 알아냄*/
        int gno=gsrv.newGallery(g);
        /*알아낸 pno를 이용해서 첨부파일 처리(디비저장, 업로드)*/
        if(!attachs.isEmpty()){  //첨부파일이 존재한다면
            gsrv.newGalAttach(attachs,gno);
            returnPage="redirect:/gallery/list/1";
        }
        return returnPage;
    }

}
