package edu.kh.project.admin.model.service;

import java.util.List;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;

public interface AdminService {

	/** 관리자 로그인
	 * @param inputMember
	 * @return
	 */
	Member login(Member inputMember);

	/** 최대 조회수 게시글 조회
	 * @return
	 */
	Board maxReadCount();

	/** 최대 좋아요 게시글 조회
	 * @return
	 */
	Board maxLikeCount();

	/** 최대 댓글수 게시글 조회
	 * @return
	 */
	Board maxCommentCount();

	/** 신규 가입 회원 조회
	 * @return
	 */
	List<Member> newMember();

	/** 탈퇴한 회원 목록 조회
	 * @return
	 */
	List<Member> selectWithdrawnMemberList();

	/** 삭제된 게시글 목록 조회
	 * @return
	 */
	List<Board> selectDeleteBoardList();

	/** 탈퇴한 사용자 복구
	 * @param memberNo
	 * @return
	 */
	int restoreMember(int memberNo);

	/** 삭제된 게시글 복구
	 * @param boardNo
	 * @return
	 */
	int restoreBoard(int boardNo);

	/** 관리자 이메일 중복 여부 검사
	 * @param memberEmail
	 * @return
	 */
	int checkEmail(String memberEmail);

	/** 관리자 계정 발급
	 * @param member
	 * @return
	 */
	String createAdminAccount(Member member);

	/** 관리자 목록 조회
	 * @return
	 */
	List<Member> selectManagerList();

}
