package minha.hello.boot.spring5boot.pds;


import minha.hello.boot.spring5boot.dao.PdsDAO;
import minha.hello.boot.spring5boot.dao.PdsDAOImpl;
import minha.hello.boot.spring5boot.model.Pds;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PdsDAOImpl.class)
public class PdsDAOUnitTest {

    @Autowired
    private PdsDAO pdao;

    @Test
    @DisplayName("PdsDAO select Test")
    void selectPds(){
        int cpg=1;
        int stnum=(cpg-1)*25;
        List<Pds> results = pdao.selectPds(stnum);
        assertNotNull(results);
        System.out.println(results);
    }

    @Test
    @DisplayName("PdsDAO countPages Test")
    void countPages(){
        int result = pdao.countPages();
        assertNotNull(result);
        System.out.println(result);
    }

    @Test
    @DisplayName("PdsDAO selectOnePds Test")
    void selectOnePds(){
        String pno="7";
        Pds result = pdao.selectOnePds(pno);
        assertNotNull(result);
        System.out.println(result);
    }

}
