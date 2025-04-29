package edu.kh.project.member.model.service;

import java.util.Map;

import edu.kh.project.member.model.dto.Member;

public interface MemberService {

	/** 로그인 서비스
	 * @param inputMember
	 * @return loginMember(member)
	 */
	Member login(Member inputMember);

	/** 이메일 중복검사 서비스
	 * @param memberEmail
	 * @return
	 */
	int checkEmail(String memberEmail);

	/** 닉네임 중복검사 서비스
	 * @param memberNickname
	 * @return
	 */
	int checkNickname(String memberNickname);

	/** 회원가입 서비스
	 * @param inputmember
	 * @param memberAddress
	 * @return
	 */
	int signup(Member inputMember, String[] memberAddress);

	/** 회원 아이디 찾기 서비스
	 * @param memberNickname
	 * @param memberTel
	 * @return
	 */
	String findMember(String memberNickname, String memberTel);

	/** 회원 비밀번호 찾기(새로 설정) 서비스
	 * @param inputMember
	 * @return
	 */
	int findPw(Member inputMember);

	/** 새로운 비밀번호 설정
	 * @param newPw
	 * @param memberEmail 
	 * @param newPwConfirm
	 * @return
	 */
	int changeNewPw(String newPw, String memberEmail);

}
