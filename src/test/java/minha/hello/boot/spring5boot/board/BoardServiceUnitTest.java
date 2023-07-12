package minha.hello.boot.spring5boot.board;

import minha.hello.boot.spring5boot.dao.BoardDAOImpl;
import minha.hello.boot.spring5boot.dao.MemberDAOImpl;
import minha.hello.boot.spring5boot.model.Board;
import minha.hello.boot.spring5boot.model.Member;
import minha.hello.boot.spring5boot.service.BoardService;
import minha.hello.boot.spring5boot.service.BoardServiceImpl;
import minha.hello.boot.spring5boot.service.MemberService;
import minha.hello.boot.spring5boot.service.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({BoardServiceImpl.class, BoardDAOImpl.class})
public class BoardServiceUnitTest {

    @Autowired
    private BoardService bsrv;

    @Test
    @DisplayName("BoardService save Test")
    @Transactional
    void saveBoard(){
        Board b = new Board();
        b.setUserid("abc123");
        b.setTitle("test");
        b.setContents("testtest");
        b.setIpaddr("test");
        boolean result = bsrv.saveBoard(b);
        System.out.println(result);
        assertEquals(result,true);
    }

    @Test
    @DisplayName("BoardService read Test")
    void readBoard(){
        int cpg=1;
        List<Board> results = bsrv.readBoard(cpg);
        assertNotNull(results);
        System.out.println(results);
    }

}
