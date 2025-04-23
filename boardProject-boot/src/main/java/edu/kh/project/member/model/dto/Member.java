package edu.kh.project.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO(Data Transfer Object)
// - 데이터 전달용 객체
// - DB에서 조회된 결과를 담을 용도 또는
// 	 SQL 구문에 사용할 값을 전달하는 용도로
//	 관련성 있는 데이터를 한번에 묶어서 다룸

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	private int memberNo;
	private String memberEmail;
	private String memberPw;
	private String memberNickname;
	private String memberTel;
	private String memberAddress;
	private String profileImg;
	private String enrollDate;
	private String memberDelFl;
	private int authority;
}
