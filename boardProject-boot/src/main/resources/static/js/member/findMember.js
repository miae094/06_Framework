// 아이디 찾기
const findMember = document.querySelector("#findMember");

if(findMember != null) {
    const memberNickname = document.querySelector("#memberNickname");
    const memberTel = document.querySelector("#memberTel");
    const goToMainBtn = document.querySelector("#goToMainBtn");
   
    findMember.addEventListener("submit", e => {
        // 닉네임 유효성 검사
        if(memberNickname.value.trim().length === 0) {
            console.log("memberNickname.value" + memberNickname.value);
            alert("닉네임을 입력해주세요");
            e.preventDefault(); // 제출 막기
            return;
        }

        // 닉네임 정규식에 맞지 않으면
        let regExp = /^[가-힣\w\d]{2,10}$/;
        if( !regExp.test(memberNickname.value)) {
            alert("닉네임이 유효하지 않습니다.");
            e.preventDefault(); // 제출 막기
            return;
        }

        // 전화번호 유효성 검사
        if(memberTel.value.trim().length === 0) {
            alert("전화번호를 입력해 주세요");
            e.preventDefault();
            return;
        }

        // 전화번호 정규식에 맞지 않으면
        regExp = /^01[0-9]{1}[0-9]{3,4}[0-9]{4}$/;
        if( !regExp.test(memberTel.value)) {
            alert("전화번호가 유효하지 않습니다");
            e.preventDefault();
            return;
        }
    });

// 메인으로 돌아가는 버튼
if(goToMainBtn != null) {
    goToMainBtn.addEventListener("click", () => {
        location.href = "/";
    });
}
}



// -----------------------------------------------------------
// 비밀번호 찾기

const findPw = document.querySelector("#findPw");

if(findPw != null) {

    const memberNickname = document.querySelector("#memberNickname");
    const memberEmail = document.querySelector("#memberEmail");
    const memberTel = document.querySelector("#memberTel");

    findPw.addEventListener("submit", e => {
        // 닉네임 유효성 검사
        if(memberNickname.value.trim().length === 0) {
            alert("닉네임을 입력해주세요");
            e.preventDefault(); // 제출 막기
            return;
        }

        // 닉네임 정규식에 맞지 않으면
        let regExp = /^[가-힣\w\d]{2,10}$/;
        if( !regExp.test(memberNickname.value)) {
            alert("닉네임이 유효하지 않습니다.");
            e.preventDefault(); // 제출 막기
            return;
        }

        // 입력된 이메일이 없을경우
        if(memberEmail.trim().length === 0) {
            alert("이메일을 입력해주세요");
            e.preventDefault(); // 제출 막기

            return;
        }

        // 입력된 이메일이 있을 경우 정규식 검사
        regExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

        if(!regExp.test(memberEmail)){
            alert("이메일이 유효하지 않습니다.");
            e.preventDefault(); // 제출 막기
        return;
        }



        // 전화번호 유효성 검사
        if(memberTel.value.trim().length === 0) {
            alert("전화번호를 입력해 주세요");
            e.preventDefault();
            return;
        }

        // 전화번호 정규식에 맞지 않으면
        regExp = /^01[0-9]{1}[0-9]{3,4}[0-9]{4}$/;
        if( !regExp.test(memberTel.value)) {
            alert("전화번호가 유효하지 않습니다");
            e.preventDefault();
            return;
        }


    });

}


// -----------------------------------------------------------
// 새로운 비밀번호로 변경

// 비밀번호 변경 form 태그
const changeNewPw = document.querySelector("#changeNewPw");

// 현재 페이지에서 changeNewPw 요소가 존재할 때 
if(changeNewPw != null) { // if(changeNewPw) 로 사용해도 됨. 다른데서도 js 파일 사용하기 때문에 에러예방으로!
    
    // 제출 되었을 때
    changeNewPw.addEventListener("submit", e => {

        const newPw = document.querySelector("#newPw");
        const newPwConfirm = document.querySelector("#newPwConfirm");

        // - 값을 모두 입력했는가

        let str; // undefined 상태
        if( newPw.value.trim().length == 0 ) str = "새 비밀번호를 입력해주세요";
        else if( newPwConfirm.value.trim().length == 0 ) str = "새 비밀번호 확인을 입력해주세요";

        if(str != undefined) { // str에 값이 대입됨 == if 중 하나 실행됨
            alert(str);
            e.preventDefault();
            return;
        }

        // 새 비밀번호 정규식
        const regExp = /^[a-zA-Z0-9!@#_-]{6,20}$/;

        if( !regExp.test(newPw.value) ) {
            alert("새 비밀번호가 유효하지 않습니다");
            e.preventDefault();
            return;
        }

        // 새 비밀번호 == 새 비밀번호 확인
        if( newPw.value != newPwConfirm.value ) {
            alert("새 비밀번호가 일치하지 않습니다");
            e.preventDefault();
            return;
        } 
    });
};