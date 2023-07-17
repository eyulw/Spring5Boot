package minha.hello.boot.spring5boot.dao;

import minha.hello.boot.spring5boot.model.Pds;
import minha.hello.boot.spring5boot.model.PdsAttach;

import java.util.List;

public interface PdsDAO {
    int insertPds(Pds p);

    int insertPdsAttach(PdsAttach pa);

    List<Pds> selectPds(int stnum);
    int countPages();
    Pds selectOnePds(String pno);
}
