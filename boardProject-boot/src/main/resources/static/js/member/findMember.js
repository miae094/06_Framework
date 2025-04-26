// 아이디 찾기
const findMember = document.querySelector("#findMember");
const memberNickname = document.querySelector("#memberNickname");
const memberTel = document.querySelector("#memberTel");

const goToMainBtn = document.querySelector("#goToMainBtn");

if(findMember != null) {
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

if(goToMainBtn != null) {
    goToMainBtn.addEventListener("click", () => {
        location.href = "/";
    });
}