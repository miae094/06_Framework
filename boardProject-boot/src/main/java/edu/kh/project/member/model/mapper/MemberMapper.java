package edu.kh.project.member.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;


@Mapper
public interface MemberMapper {
	
	/** 로그인 SQL 실행
	 * @param memberEmail
	 * @return
	 */
	Member login(String memberEmail);

	/** 이메일 중복검사
	 * @param memberEmail
	 * @return
	 */
	int checkEmail(String memberEmail);

	/** 닉네임 중복검사 서비스
	 * @param memberNickname
	 * @return
	 */
	int checkNickname(String memberNickname);

	/** 회원 가입 서비스
	 * @param inputMember
	 * @return
	 */
	int signup(Member inputMember);

	/** 회원 아이디 찾기
	 * @param map
	 * @return
	 */
	String findMember(Map<String, String> map);

}
