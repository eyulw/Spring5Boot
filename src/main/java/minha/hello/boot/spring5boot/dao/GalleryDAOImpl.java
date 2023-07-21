package minha.hello.boot.spring5boot.dao;

import minha.hello.boot.spring5boot.model.GalAttach;
import minha.hello.boot.spring5boot.model.Gallery;
import minha.hello.boot.spring5boot.mybatis.GalleryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gdao")
public class GalleryDAOImpl implements GalleryDAO{

    @Autowired private GalleryMapper galleryMapper;
    @Override
    public List<Gallery> selectGallery(int stnum) {
        return galleryMapper.selectGallery(stnum);
    }

    @Override
    public int insertGallery(Gallery g) {
        int cnt=galleryMapper.insertGallery(g);
        if(cnt>0) cnt=galleryMapper.lastGalGno();
        return cnt;
    }

    @Override
    public int insertGalAttach(GalAttach ga) {
        return galleryMapper.insertGalAttach(ga);
    }
}
