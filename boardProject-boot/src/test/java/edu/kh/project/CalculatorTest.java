package edu.kh.project;
// JUnit 테스트 코드는 반드시 src/test/java 경로에 위치해야함

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.kh.project.test.Calculator;

// src/main/java : 실제 애플리케이션 코드
// src/test/java : 테스트 코드(JUnit 포함됨)

// 테스트 특징과 규칙
// 1. 기본적으로 JUnit5 는 테스트 메서드의 실행 순서를 보장하지 않음
// 2. 테스트는 순서에 의존하지 않도록 설계하는것이 관장된다.
// 3. 테스트는 독립적이어야 하며, 순서에 따라 실패 or 통과하면 안된다
// -> 테스트가 순서에 의존하면 좋은 테스트가 아님
// 4. 그래도 순서를 따지고 싶다면 @TestMethodOrder / @Order 사용할 수 있다.

@TestMethodOrder(OrderAnnotation.class)
public class CalculatorTest {
	private static Calculator calculator;
	
	// @BeforeAll, @AfterAll - static 메서드에서 사용해야 함!!
	@BeforeAll
	static void initAll() {
		// 모든 테스트 실행 전에 1번만 실행
		calculator = new Calculator();
		System.out.println("@BeforeAll - 테스트 시작 전 초기화");
	}
	
	@AfterAll
	static void end() {
		// 모든 테스트가 끝난 뒤 1번만 실행
		System.out.println("@AfterAll - 모든 테스트 완료");
	}
	
	@Test	// 테스트 메서드 표시
	@Order(1)
	void testAdd() {
		System.out.println("testAdd 실행");
		assertEquals(12, calculator.add(3, 9), "3+9는 12여야 합니다.");
		// assertEquals(expected, actual) : 두 값이 같은가?
		
	}
	
	@Test	// 테스트 메서드 표시
	@Order(3)
	void testSubtract() {
		System.out.println("testSubtract 실행");
		assertEquals(6, calculator.subtract(9, 3), "9-3는 6여야 합니다.");
		// assertEquals(expected, actual) : 두 값이 같은가?
		
	}
	
	@Test	// 테스트 메서드 표시
	@Order(2)
	void testMultiply() {
		System.out.println("testMultiply 실행");
		assertEquals(27, calculator.multiply(9, 3), "9*3는 27여야 합니다.");
		// assertEquals(expected, actual) : 두 값이 같은가?
		
	}
	
	@Test	// 테스트 메서드 표시
	@Order(4)
	void testDivide() {
		System.out.println("testDivide 실행");
		assertEquals(3, calculator.divide(9, 3), "9/3는 3여야 합니다.");
		// assertEquals(expected, actual) : 두 값이 같은가?
		
	}
	
	@Test
	@Order(5)
	void testTrueFalse() {
		int result = calculator.add(2, 2);
		assertTrue(result == 4, "결과가 4여야합니다");
		
		assertFalse(result ==5, "결과가 5면 안됩니다");
	}
	
	@Test
	@Order(6)
	void testDivideByZero() {
		System.out.println("testDivideByZero 실행");
		
		// assertThrows(Exception.class, executable) : 예외 발생 여부
		Exception exception = assertThrows(IllegalArgumentException.class, 
				() -> {
					calculator.divide(10, 0);
				});
		assertEquals("0으로 나눌 수 없음", exception.getMessage());
	}
}
