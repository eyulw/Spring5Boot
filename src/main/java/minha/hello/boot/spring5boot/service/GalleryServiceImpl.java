package minha.hello.boot.spring5boot.service;

import lombok.RequiredArgsConstructor;
import minha.hello.boot.spring5boot.dao.GalleryDAO;
import minha.hello.boot.spring5boot.model.Gallery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("gsrv")
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService{

    final GalleryDAO gdao;

    @Override
    public List<Gallery> readGallery(Integer cpg) {
        return gdao.selectGallery(cpg - 1);
    }

    @Override
    public int countGallery() {
        return 0;
    }
}
