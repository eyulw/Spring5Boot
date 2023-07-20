package minha.hello.boot.spring5boot.dao;

import minha.hello.boot.spring5boot.model.Gallery;

import java.util.List;

public interface GalleryDAO {

    List<Gallery> selectGallery(int stnum);
}
