package minha.hello.boot.spring5boot.dao;

import minha.hello.boot.spring5boot.model.Pds;
import minha.hello.boot.spring5boot.model.PdsAttach;
import minha.hello.boot.spring5boot.model.PdsComment;
import minha.hello.boot.spring5boot.mybatis.PdsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("pdao")
public class PdsDAOImpl implements PdsDAO{
    @Autowired private PdsMapper pdsMapper;
    @Override
    public int insertPds(Pds p) {
        int cnt = pdsMapper.insertPds(p);
        if(cnt>0)
            cnt = pdsMapper.lastPdsPno();
        return cnt;
    }

    @Override
    public int insertPdsAttach(PdsAttach pa) {
        return pdsMapper.insertPdsAttach(pa);
    }

    @Override
    public List<Pds> selectPds(int stnum) {
        return pdsMapper.selectPds(stnum);
    }

    @Override
    public int countPages() {
        return pdsMapper.countPages();
    }

    @Override
    public Pds selectOnePds(String pno) {
        return pdsMapper.selectOnePds(pno);
    }

    @Override
    public PdsAttach selectOnePdsAttach(String pno) {
        return pdsMapper.selectOnePdsAttach(pno);
    }

    @Override
    public int insertPdsComment(PdsComment pc) {
        return pdsMapper.insertPdsComment(pc);
    }

    @Override
    public List<PdsComment> selectPdsComment(String pno) {
        return pdsMapper.selectPdsComment(pno);
    }

    @Override
    public int insertPdsReply(PdsComment pc) {
        return pdsMapper.insertPdsReply(pc);
    }
}
