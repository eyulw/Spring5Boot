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

let zipbtn = document.querySelector("#findzipbtn");
let dong = document.querySelector("#dong")
let addrlist =document.querySelector("#addrlist");
const showzipaddr = (jsons) => {
    jsons = JSON.parse(jsons);  //문자열을 json객체로 변환
    let addr = '';
    jsons.forEach(function (data,idx){  //json 반복처리
        addr+=`<option>${data['zipcode']} ${data['sido']} ${data['gugun']}
                ${data['dong']} ${data['bunji']}</option>`;
    });
    addrlist.innerHTML = addr;
};
zipbtn?.addEventListener('click',()=>{
    if(dong.value===''){
        alert('동이름을 입력하세요!');
        return;
    }
    const url='/join/zipcode?dong='+dong.value;
    fetch(url).then(response=>response.text())
        .then(text=>showzipaddr(text));
})
