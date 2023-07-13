package minha.hello.boot.spring5boot.mybatis;

import minha.hello.boot.spring5boot.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int insertBoard(Board b);
    List<Board> selectBoard(int stnum);
    Board selectOneBoard(String bno);
    int countPages();
    int deleteBoard(String bno);
    int updateViewBoard(String bno);
    List<Board> selectFindBoard(Map<String,Object> params);
}
