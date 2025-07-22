package edu.kh.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestCalculatorCall {
	private static TestCalculator calculator;
	
	@BeforeAll
	public static void setUp() {
		calculator = new TestCalculator();
		log.info("테스트 시작");
	}

	@Test
	public void testAdd() {
		assertEquals(5, calculator.add(2, 3), "결과가 5여야 합니다");
	}

	@Test
	public void testSubtract() {
		assertEquals(1, calculator.subtract(3, 2));
	}

	@Test
	public void testMultiply() {
		assertEquals(6, calculator.multiply(2, 3));
	}

	@Test
	public void testDivide() {
		assertEquals(2, calculator.divide(6, 3));
	}

	@Test
	public void testDivideByZero() {
		assertThrows(IllegalArgumentException.class, () -> calculator.divide(5, 0));
	}

	@AfterAll
	public static void testComplete() {
		log.info("모든 테스트 완료");
	}

}
