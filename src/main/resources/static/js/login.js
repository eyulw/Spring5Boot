//login

let loginbtn=document.querySelector("#loginbtn");
let lguid=document.querySelector("#userid");
let lgpwd=document.querySelector("#passwd");
let lgnfrm=document.querySelector("#lgnfrm");

loginbtn?.addEventListener('click',()=>{
    if(lguid.value===''){
        alert("아이디를 입력하세요");
    }else if(lgpwd.value===''){
        alert("비밀번호를 입력하세요");
    }else{
        lgnfrm.method='post';
        lgnfrm.action='/join/login';
        lgnfrm.submit();
    }
})

//logout
let lgoutbtn = document.querySelector("#lgoutbtn");
lgoutbtn?.addEventListener('click',()=>{
    location.href='/join/logout';
})

let joinBtn=document.querySelector("#joinBtn");
joinBtn?.addEventListener('click',()=>{
    location.href="/join/agree";
});