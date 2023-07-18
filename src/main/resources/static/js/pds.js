//pds view
let newpdsbtn =document.querySelector("#newpdsbtn");
let modpdsbtn =document.querySelector("#modpdsbtn");
let rmvpdsbtn =document.querySelector("#rmvpdsbtn");
let lstpdsbtn =document.querySelector("#lstpdsbtn");
let delbno = document.querySelector("#delbno");

newpdsbtn?.addEventListener('click',()=>{
    location.href='/pds/write';
})
modpdsbtn?.addEventListener('click',()=>{
    if(confirm("정말 수정하시겠어요?")){
        alert('아직 미지원 기능입니다.');
    }
})

rmvpdsbtn?.addEventListener('click',()=>{
    if(confirm("정말 삭제하시겠어요?")){
        location.href='/pds/delete/'+delbno.value;
    }
})
lstpdsbtn?.addEventListener('click',()=>{
    location.href='/pds/list/1'
})

//pds write
let wrtpdsbtn = document.querySelector("#wrtpdsbtn");
let cancelbtn3 = document.querySelector("#cancelbtn3");

wrtpdsbtn?.addEventListener('click',()=>{
    let frm = document.forms.pdsfrm;
    if(frm.title.value===''){
        alert("제목을 작성하세요.");
    }else if(frm.contents.value===''){
        alert("본문을 작성하세요.");
    }else if(grecaptcha.getResponse()===''){
        alert("자동쓰기방지를 체크하세요.");
    }else{
        frm.method='post';
        frm.enctype='multipart/form-data';  /*첨부기능을 위해 추가*/
        frm.submit();
    }
});

cancelbtn3?.addEventListener('click',()=>{
    location.href='/pds/list/1';
})

//pds find
let findbtn=document.querySelector("#findbtn");
let findtype=document.querySelector("#findtype");
let findkey=document.querySelector("#findkey");

findbtn?.addEventListener('click',()=>{
   if(findkey.value===''){
       alert('검색어를 입력하세요');
   }
   else{
       location.href=`/pds/find/${findtype.value}/${findkey.value}/1`;
   }
});
