package minha.hello.boot.spring5boot.mybatis;

import minha.hello.boot.spring5boot.model.Pds;
import minha.hello.boot.spring5boot.model.PdsAttach;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PdsMapper {
    int insertPds(Pds b);

    int lastPdsPno();

    int insertPdsAttach(PdsAttach pa);

/*    List<Pds> selectPds(int stnum);
    Pds selectOnePds(String bno);
    int countPages();
    int deletePds(String bno);
    int updateViewPds(String bno);
    List<Pds> selectFindPds(Map<String,Object> params);
    int countFindPds(Map<String,Object> params);*/
}
