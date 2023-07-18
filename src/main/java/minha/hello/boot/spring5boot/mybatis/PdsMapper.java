package minha.hello.boot.spring5boot.mybatis;

import minha.hello.boot.spring5boot.model.Pds;
import minha.hello.boot.spring5boot.model.PdsAttach;
import minha.hello.boot.spring5boot.model.PdsComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PdsMapper {
    int insertPds(Pds b);

    int lastPdsPno();

    int insertPdsAttach(PdsAttach pa);

    List<Pds> selectPds(int stnum);
    Pds selectOnePds(String pno);
    int countPages();
    PdsAttach selectOnePdsAttach(String pno);
    int insertPdsComment(PdsComment pc);
    List<PdsComment> selectPdsComment(String pno);

//    int deletePds(String bno);
//    int updateViewPds(String bno);
//    List<Pds> selectFindPds(Map<String,Object> params);
//    int countFindPds(Map<String,Object> params);
}
