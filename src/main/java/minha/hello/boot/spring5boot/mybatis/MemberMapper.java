package minha.hello.boot.spring5boot.mybatis;

import minha.hello.boot.spring5boot.model.Member;
import minha.hello.boot.spring5boot.model.Zipcode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MemberMapper {
    int insertMember(Member m);
    List<Member> selectMember();
    List<Zipcode> findZipcode(String dong);
}
