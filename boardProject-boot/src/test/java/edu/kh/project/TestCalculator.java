package edu.kh.project;

public class TestCalculator {
	public int add(int a, int b) {
		return a + b;
		}

		public int subtract(int a, int b) {
		return a - b;
		}

		public int multiply(int a, int b) {
		return a * b;
		}

		public int divide(int a, int b) {
		if (b == 0) {
		throw new IllegalArgumentException("0으로 나눌 수 없음");
		}
		return a / b;
		}
}
