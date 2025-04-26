package edu.kh.project.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@SessionAttributes({"loginMember"})
@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {

	@Autowired
	private MemberService service;
	
	/* [로그인]
	 * - 특정 사이트에 아이디/비밀번호 등을 입력해서
	 *   해당 정보가 DB에 있으면 조회/서비스 이용
	 *   
	 * - 로그인 한 회원 정보는 session 에 기록하여
	 *  로그아웃 또는 브라우저 종료 시 까지
	 *  해당 정보를 계속 이용할 수 있게 함
	 * 
	 * */
	
	/**
	 * @param inputMember : 커맨드 객체(@ModelAddribute 생략)
	 * 						memberEmail, memberPw 세팅된 상태
	 * @param ra : 리다이렉트 시 request scope -> session scope -> request로 데이터 전달
	 * @Model model : 데이터 전달용 객체(기본 request scope)
	 * 				@SessionAttributes 어노테이션과 함께 사용시 session scope로 이동
	 * @return
	 */
	@PostMapping("login")
	public String login(@ModelAttribute Member inputMember, RedirectAttributes ra,
			Model model, @RequestParam(value="saveId", required = false) String saveId,
			HttpServletResponse resp) {
		
		// 체크박스
		// - 체크가 된 경우 	: "on"
		// - 체크가 안 된 경우 	: null
		
		
		// 로그인 서비스 호출
		Member loginMember = service.login(inputMember);
		
		// 로그인 실패 시
		if(loginMember == null) {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		} else { //로그인 성공 시
			
			// session scope 에 loginMember 추가
			model.addAttribute("loginMember", loginMember);
			// 1단계 : model을 이용하여 request scope에 세팅됨
			// 2단계 : 클래서 위에 @SessionAttribute 어노테이셔 작성하여
			// 			session scope로 loginMember를 이동
			
			// ******************** Cookie ***********************
			//로그인 시 작성한 이메일 저장(쿠키에)
			
			// 쿠키 객체 생성( K : V )
			Cookie  cookie = new Cookie("saveId", loginMember.getMemberEmail());
			// saveId = user01@kh.or.kr
			log.debug("cookie : " + cookie);
			log.debug("loginMember.getMemberEmail() : " + loginMember.getMemberEmail());
			//클라이언트가 어떤 요청을 할 때 쿠키를 첨부할지 지정
			// ex) ":/" : IP 또는 도메인 localhost
			//			--> 메인페이지 + 그 하위주소 
		
			cookie.setPath("/");
			
			// 쿠키의 만료기간 지정
			if(saveId != null) { // 아이디 저장 체크박스 체크했을 때
				cookie.setMaxAge(60 * 60 * 24 * 30);// 30일 초 (단우로 지정)
				
			} else { // 체크 안했을 때
				cookie.setMaxAge(0); 	// 0초 (클라이언트 쿠키 바로 삭제
				
			}
			
			// 응답 객체에 쿠키 추가 -> 클라이언트에게 전달
			resp.addCookie(cookie);
			
		}
		
		
		return "redirect:/";		
	}
	
	/** 로그아웃 : session에 저장된 로그인 된 회원 정보를 없앰
	 * @param essionStatus :  @SesstionAttributes로 지정된 특정 속성을 세션에서 제거 가능
	 * @return
	 */
	@GetMapping("logout")
	public String logout(SessionStatus status) {
		status.setComplete(); // 세션을 완료시킴(= @SessionAttribute로 등록된 세션 제거
		return "redirect:/";
		
	}
	
	/** 회원 가입 페이지로 이동
	 * @return
	 */
	@GetMapping("signup")
	public String signupPage() {
		return "member/signup";
	}
	
	/** 이메일 중복검사(비동기 요청)
	 * @return
	 */
	@ResponseBody	// 응답/ 본문(fetch)으로 돌려보냄
	@GetMapping("checkEmail")
	public int checkEmail(@RequestParam("memberEmail") String memberEmail) {
		return service.checkEmail(memberEmail);
	}
	
	/** 닉네임 중복검사
	 * @param memberNickname
	 * @return 중복 1 / 아님 0
	 */
	@ResponseBody
	@GetMapping("checkNickname")
	public int checkNickname(@RequestParam("memberNickname") String memberNickname) {
		return service.checkNickname(memberNickname);
	}
	
	/** 회원가입
	 * @param inputmember : 입력된 회원 정보(memberEmail, memberNickname, memberTel,
	 * 						membetAddress(따로 배열로 받아 처리)
	 * @param memberAddress : 입력한 주소 input 3개의 값을 배열로 전달[우편번호, 도로명/지번주소, 상세주소]
	 * @param ra : 리다이렉트 시 request -> session -> request로 데이터 전달하는 객체
	 * @return
	 */
	@PostMapping("signup")
	public String signUp(Member inputMember, @RequestParam("memberAddress") String[] memberAddress,
						RedirectAttributes ra) {
		
		log.debug("inputmember : " + inputMember);
		log.debug("memberAddress : " + memberAddress);
		
		// 회원가입 서비스 호출
		int result = service.signup(inputMember, memberAddress);
		
		String path = null;
		String message = null;
		
		if (result > 0) { // 성공
			message = inputMember.getMemberNickname() + "님의 가입을 환영합니다!";
			path = "/";
			
		} else { // 실패
			message = "회원 가입 실패";
			path = "signup";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
		// 성공 redirect:/
		// 실패 redirect:/signup(상대경로)
		//     현재 주소 /member/signup(GET 방식 요청)
				
		
	}
	
	// -------------------------------------------------------
	// 회원 아이디 찾기
	
	/** 회원 아이디 찾기 페이지로 이동
	 * @return
	 */
	@GetMapping("findMember")
	public String findMemberPage() {
		return "member/findMember";
	}
	
	@PostMapping("findMember")
	public String findMember(@RequestParam("memberNickname") String memberNickname,
							@RequestParam("memberTel") String memberTel,
							RedirectAttributes ra) {
		
		
		String memberEmail = service.findMember(memberNickname, memberTel);
		
		if(memberEmail != null) {
			ra.addFlashAttribute("memberEmail", memberEmail);
			
		} else {
			ra.addFlashAttribute("message", "일치하는 회원이 없습니다.");
		}
		
		return "redirect:findMember";
	}
	
	
	
	
}
