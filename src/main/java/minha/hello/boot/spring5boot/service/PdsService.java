package minha.hello.boot.spring5boot.service;

import minha.hello.boot.spring5boot.model.Pds;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PdsService {
    int newPds(Pds p);
    boolean newPdsAttach(MultipartFile attach,int pno);
}