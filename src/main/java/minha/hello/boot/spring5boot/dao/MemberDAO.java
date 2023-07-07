package minha.hello.boot.spring5boot.dao;

import minha.hello.boot.spring5boot.model.Member;
import minha.hello.boot.spring5boot.model.Zipcode;

import java.util.List;

public interface MemberDAO {
    int insertMember(Member m);
    List<Member> selectMember();
    List<Zipcode> selectzip(String dong);
}
