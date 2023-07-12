package minha.hello.boot.spring5boot.service;

import lombok.RequiredArgsConstructor;
import minha.hello.boot.spring5boot.dao.BoardDAO;
import minha.hello.boot.spring5boot.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bsrv")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    final BoardDAO bdao;
    @Override
    public boolean saveBoard(Board b) {
        boolean isSaved = false;
        if(bdao.insertBoard(b)>0){
            isSaved = true;
        }
        return isSaved;
    }

    @Override
    public List<Board> readBoard(int cpg) {
        int stnum=25*(cpg-1);
        return bdao.selectBoard(stnum);
    }

    @Override
    public Board readOneBoard(String bno) {
        return bdao.selectOneBoard(bno);
    }

    @Override
    public int getCountPages() {
        return bdao.countPages();
    }
}
