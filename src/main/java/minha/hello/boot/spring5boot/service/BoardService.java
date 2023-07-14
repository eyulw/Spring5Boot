package minha.hello.boot.spring5boot.service;

import minha.hello.boot.spring5boot.model.Board;

import java.util.List;

public interface BoardService {
    boolean saveBoard(Board b);
    List<Board> readBoard(Integer cpg);
    Board readOneBoard(String bno);
    int getCountPages();
    boolean removeBoard(String bno);
    List<Board> readFindBoard(Integer cpg,String ftype,String fkey);
    int countFindPages(String ftype,String fkey);
}
