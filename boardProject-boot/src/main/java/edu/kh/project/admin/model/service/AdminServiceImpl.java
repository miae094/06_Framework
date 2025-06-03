package edu.kh.project.admin.model.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.admin.model.mapper.AdminMapper;
import edu.kh.project.board.controller.BoardController;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.common.util.Utility;
import edu.kh.project.member.model.dto.Member;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final AdminMapper mapper;
	private final BCryptPasswordEncoder bcrypt;

	
	@Override
	public Member login(Member inputMember) {
		
		Member loginMember = mapper.login(inputMember.getMemberEmail());
		if(loginMember == null) {
			return null;
		}
		
		if(!bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
			return null;
		}
		
		loginMember.setMemberPw(null);
		
		return loginMember;
	}

	@Override
	public Board maxReadCount() {
		return mapper.maxReadCount();
	}

	@Override
	public Board maxLikeCount() {
		return mapper.maxLikeCount();
	}

	@Override
	public Board maxCommentCount() {
		return mapper.maxCommentCount();
	}
	
	@Override
	public List<Member> newMember() {
		return mapper.newMember();
	}

	// 탈퇴 회원 조회
	@Override
	public List<Member> selectWithdrawnMemberList() {
		
		return mapper.selectWithdrawnMemberList();
	}

	// 삭제된 게시글 목록 조회
	@Override
	public List<Board> selectDeleteBoardList() {
		return mapper.selectDeleteBoardList();
	}

	// 탈퇴 회원 복구
	@Override
	public int restoreMember(int memberNo) {
		return mapper.restoreMember(memberNo);
	}

	// 삭제 게시글 복구
	@Override
	public int restoreBoard(int boardNo) {
		return mapper.restoreBoard(boardNo);
	}

	// 관리자 이메일 중복 여부 검사
	@Override
	public int checkEmail(String memberEmail) {
		
		return mapper.checkEmail();
	}

	
	// 관리자 계정 발급
	@Override
	public String createAdminAccount(Member member) {
		
		// 1. 영어(대소문자) 6자리 난수로 만든 비밀번호를 암호화 한 값 구하기
		String rawPw = Utility.generatePassword(); // 평문 비번
		
		// 2. 평문비밀번호를 암호화하여 저장
		String enpPw = bcrypt.encode(rawPw);
		
		// 3. member에 암호화된 비밀번호 세팅
		member.setMemberPw(enpPw);
		
		// 4. DB에 암호화된 비밀번호가 세팅된 member를 전달하여 계정발급
		int result = mapper.createAdminAccount(member);
				
		// 5. 계정 발급 정상처리 되었다면, 발급된(평문) 비밀번호 리턴
		if(result > 0) {
			return rawPw;
		} else {
			return null;
		}
				
				
	}

	// 관리자 계정 조회
	@Override
	public List<Member> selectManagerList() {
		
		return mapper.selectManagerList();
	}
	
	
}
