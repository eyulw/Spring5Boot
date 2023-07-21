package minha.hello.boot.spring5boot.dao;

import minha.hello.boot.spring5boot.model.GalAttach;
import minha.hello.boot.spring5boot.model.Gallery;

import java.util.List;

public interface GalleryDAO {

    List<Gallery> selectGallery(int stnum);

    int insertGallery(Gallery g);

    int insertGalAttach(GalAttach ga);

    Gallery selectOneGallery(String gno);
}
