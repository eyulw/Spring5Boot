package minha.hello.boot.spring5boot.controller;

import lombok.RequiredArgsConstructor;
import minha.hello.boot.spring5boot.service.GalleryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        if(cpg > (int)m.getAttribute("cntpg"))
            return "redirect:/pds/list/1";
        return "gallery/list";
    }

}
