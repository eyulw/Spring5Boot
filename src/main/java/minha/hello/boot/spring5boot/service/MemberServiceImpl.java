package minha.hello.boot.spring5boot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import minha.hello.boot.spring5boot.dao.MemberDAO;
import minha.hello.boot.spring5boot.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("msrv")
public class MemberServiceImpl implements MemberService{
    @Autowired private MemberDAO mdao;
    @Override
    public boolean saveMember(Member m) {
        boolean isSaved = false;
        if(mdao.insertMember(m)>0)
            isSaved=true;
        return isSaved;
    }

    @Override
    public List<Member> readMember() {
        return mdao.selectMember();
    }


    // 우편번호 검색
    // 조회결과 출력방법 1 : csv (쉼표로 구분)
    // 서울, 강남구, 논현동, 123번지

    // 조회결과 출력방법 2 : xml
    // <zip><sido>서울</sido> <gugun>강남구</gugun>
    //      <dong>논현동</dong> <bunji>123번지</bunji></zip>

    // 조회결과 출력방법 3 : json (추천)
    // {'sido':'서울', 'gugun':'강남구',
    //  'dong':'논현동', 'bunji':'123번지'}

    // StringBuilder sb = new StringBuilder();
    // sb.append("{'sido':").append("'서울',")
    // .append("'gugun':").append("'강남구',")
    // .append("'dong':").append("'논현동',")
    // .append("'bunji':").append("'123번지',");
    // .append("}");

    // 코드로 json 형태로 결과물을 만들려면 상당히 복잡함
    // ObjectMapper 라이브러리를 이용하면
    // 손쉽게 JSON 유형의 데이터를 생성할 수 있음
    @Override
    public String findzip(String dong) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        dong+='%';  //부분검색 가능하도록 재조정
        json=mapper.writeValueAsString(mdao.selectzip(dong));
        return json;
    }

    @Override
    public int checkuid(String uid) {
        return mdao.selectOneUserid(uid);
    }
}
