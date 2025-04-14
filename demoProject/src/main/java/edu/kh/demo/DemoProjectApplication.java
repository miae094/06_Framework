package edu.kh.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // SpringBootApplication에 필요한 필수 어노테이션과 설정을 모아둔 어노테이션
public class DemoProjectApplication {

	/*
	 * Spring Boot 프로젝트로 만든 어플리케이션의 실행을 담당하는 클래스
	 * Spring Application 을 최소 설정으로 간단하고 빠르게 실행 할 수 있게 해줌
	 * *** Java 파일을 실행하듯이 Run 버튼(ctrl + F11) 클릭하면 배포가 시작됨 ***
	 * 
	 * */
	
	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
	}

}
