/* agree */
let agree1 = document.querySelector("#agree1");
let agree2 = document.querySelector("#agree2");
let okagree = document.querySelector("#okagree");
let noagree = document.querySelector("#noagree");

okagree?.addEventListener('click',()=>{
    if(!agree1.checked){
        alert("이용약관 동의에 체크하세요");
    }else if (!agree2.checked){
        alert("개인정보 수집 및 이용 동의에 체크하세요");
    }else{
        location.href="/join/checkme";
    }
});
noagree?.addEventListener('click',()=>{
    location.href="/";
});

/* checkme */
let checkbtn2 = document.querySelector("#checkbtn2");
let cancelbtn2=document.querySelector("#cancelbtn2");
let checkfrm=document.forms.checkfrm2;
checkbtn2?.addEventListener('click',()=>{
    if(checkfrm.name2.value===''){
        alert("이름을 입력하세요");
    }else if(checkfrm.jumin1.value===''){
        alert("주민등록번호 앞자리를 입력하세요");
    }else if(checkfrm.jumin2.value===''){
        alert("주민등록번호 뒷자리를 입력하세요");
    }else if(!checkfrm.infoagree.checked){
        alert("주민등록번호 처리 동의를 체크하세요");
    }else{
        checkfrm.method='post';
        checkfrm.submit();
    }
});

cancelbtn2?.addEventListener('click',()=>{
   location.href="/";
});

//joinme

let fzipbtn = document.querySelector("#findzipbtn");
let zipbtn =document.querySelector("#zipbtn");
let dong = document.querySelector("#dong")
let zipmodal =document.querySelector("#zipmodal");

let addrlist =document.querySelector("#addrlist");
let sendzip =document.querySelector("#sendzip");
let modal = null;   //우편번호 모달

let email3 =document.querySelector("#email3");

zipbtn?.addEventListener('click',()=>{
    while(addrlist.lastChild){      //addrlist에 마지막 자식이 있는지 확인
        addrlist.removeChild(addrlist.lastChild);
    }   //이전 검색 결과 지움
    dong.value='';  //이전 검색 키워드 지움
    try{
        // 새로운 모달 창 생성
        modal=new bootstrap.Modal(zipmodal,{});
    }catch (e){}
    modal.show();
});

const showzipaddr = (jsons) => {
    jsons = JSON.parse(jsons);  //문자열을 json객체로 변환
    let addr = '';
    jsons.forEach(function (data,idx){  //json 반복처리
        addr+=`<option>${data['zipcode']} ${data['sido']} ${data['gugun']}
                ${data['dong']} ${data['bunji']}</option>`;
    });
    addrlist.innerHTML = addr;
};
fzipbtn?.addEventListener('click',()=>{
    if(dong.value===''){
        alert('동이름을 입력하세요!');
        return;
    }
    const url='/join/zipcode?dong='+dong.value;
    fetch(url).then(response=>response.text())
        .then(text=>showzipaddr(text));
});

sendzip?.addEventListener('click',()=>{
    let frm = document.forms.joinfrm1;
    let addr = addrlist.value;  //선택한 주소 항목
    if(addr != ''){
        // ex) 123-456 서울 관악구 신림동 이라면
        let zip =addr.split(' ')[0];    //우편번호 추출
        let addrs = addr.split(' ');
        let vaddr = `${addrs[1]} ${addrs[2]} ${addrs[3]}`;  //주소추출
        frm.zip1.value = zip.split('-')[0];
        frm.zip2.value = zip.split('-')[1];
        frm.addr1.value=vaddr;
        
        modal.hide();   //모달창 닫음
    }else{
        alert("주소를 선택하세요!");
    }
});

email3?.addEventListener('click',()=>{
    let frm = document.forms.joinfrm1;
    if(email3.value === '직접입력하기') {
        frm.email2.readOnly = false;
        frm.email2.value = "";
    }else if(email3.value!=='선택하세요'){
        frm.email2.readOnly = true;
        frm.email2.value = email3.value;
    }
})

// 우편번호 검색 엔터키 입력차단
dong?.addEventListener('keydown',(e)=>{
    if(e.keyCode === 13)    //엔터키(13)가 입력되면
        e.preventDefault(); //이벤트 전파 방지
})

// 비밀번호 확인
let pwd = document.joinfrm1.passwd;
let repwd=document.joinfrm1.repasswd;
let pwdmsg=document.querySelector("#pwdmsg");
repwd?.addEventListener('blur',()=>{
    let pmsg = "비밀번호가 서로 일치하지 않습니다.";
    pwdmsg.className='text-danger';
    if(pwd.value === repwd.value){
        pmsg="비밀번호가 서로 일치합니다.";
        pwdmsg.className='text-primary';
    }
    pwdmsg.innerText=pmsg;
})

//아이디 중복 검사
let userid = document.joinfrm1.userid;
let checkuid = document.joinfrm1.checkuid;
let uidmsg=document.querySelector("#uidmsg");

const checkUserid = (chkuid) => {
    let umsg = '사용 불가능한 아이디입니다.';
    uidmsg.className='text-danger';
    checkuid.value='no';
    if(chkuid==='0'){
        umsg='사용가능한 아이디입니다.';
        uidmsg.className='text-primary';
        checkuid.value='yes';
    }
    uidmsg.innerText=umsg;
};
userid?.addEventListener('blur',()=>{
    if(userid.value===''){
        uidmsg.innerText='6~16 자의 영문 소문자, 숫자와 특수기호(_)만 사용할 수 있습니다';
        uidmsg.className='text-warning';
        checkuid.value='no';
        return;
    }
    const url="/join/checkuid/"+userid.value;
    fetch(url).then(response=>response.text())
        .then(text=>checkUserid(text))
})
