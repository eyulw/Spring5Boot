package minha.hello.boot.spring5boot.mybatis;

import minha.hello.boot.spring5boot.model.GalAttach;
import minha.hello.boot.spring5boot.model.Gallery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GalleryMapper {
    int insertGallery(Gallery g);

    int insertGalAttach(GalAttach ga);

    List<Gallery> selectGallery(int stnum);
    Gallery selectOneGallery(String gno);
    int selectCountGallery();
    GalAttach selectOneGalAttach(String gno);
    int lastGalGno();
//    int insertPdsComment(PdsComment pc);
//    List<PdsComment> selectPdsComment(String pno);
//
//    int insertPdsReply(PdsComment pc);

//    int deletePds(String bno);
//    int updateViewPds(String bno);
//    List<Pds> selectFindPds(Map<String,Object> params);
//    int countFindPds(Map<String,Object> params);

}
