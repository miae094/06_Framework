package edu.kh.project.mypage.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.member.model.dto.Member;

@Mapper
public interface MyPageMapper {
	/** 회원 정보 수정
	 * @param inputMember
	 * @return
	 */
	public int updateInfo(Member inputMember) ;
}
