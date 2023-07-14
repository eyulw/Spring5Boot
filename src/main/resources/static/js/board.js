//board view
let newbdbtn =document.querySelector("#newbdbtn");
let modbdbtn =document.querySelector("#modbdbtn");
let rmvbdbtn =document.querySelector("#rmvbdbtn");
let lstbdbtn =document.querySelector("#lstbdbtn");
let prebd =document.querySelector("#prebd");
let nextbd =document.querySelector("#nextbd");
let delbno = document.querySelector("#delbno");

newbdbtn?.addEventListener('click',()=>{
    location.href='/board/write';
})
modbdbtn?.addEventListener('click',()=>{
    if(confirm("정말 수정하시겠어요?")){
        alert('아직 미지원 기능입니다.');
    }
})

rmvbdbtn?.addEventListener('click',()=>{
    if(confirm("정말 삭제하시겠어요?")){
        location.href='/board/delete/'+delbno.value;
    }
})
lstbdbtn?.addEventListener('click',()=>{
    location.href='/board/list/1'
})

//board write
let wrtbdbtn = document.querySelector("#wrtbdbtn");
let cancelbtn3 = document.querySelector("#cancelbtn3");

wrtbdbtn?.addEventListener('click',()=>{
    let frm = document.forms.bdfrm;
    if(frm.title.value===''){
        alert("제목을 작성하세요.");
    }else if(frm.contents.value===''){
        alert("본문을 작성하세요.");
    }else if(grecaptcha.getResponse()===''){
        alert("자동쓰기방지를 체크하세요.");
    }else{
        frm.method='post';
        frm.submit();
    }
});

cancelbtn3?.addEventListener('click',()=>{
    location.href='/board/list/1';
})

//board find
let findbtn=document.querySelector("#findbtn");
let findtype=document.querySelector("#findtype");
let findkey=document.querySelector("#findkey");

findbtn?.addEventListener('click',()=>{
   if(findkey.value===''){
       alert('검색어를 입력하세요');
   }
   else{
       location.href=`/board/find/${findtype.value}/${findkey.value}/1`;
   }
});

