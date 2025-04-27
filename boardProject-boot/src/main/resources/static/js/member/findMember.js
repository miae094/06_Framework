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
}

// 메인으로 돌아가는 버튼
if(goToMainBtn != null) {
    goToMainBtn.addEventListener("click", () => {
        location.href = "/";
    });
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