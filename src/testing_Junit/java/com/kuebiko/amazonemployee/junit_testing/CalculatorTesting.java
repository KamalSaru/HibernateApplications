package com.kuebiko.amazonemployee.junit_testing;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
public class CalculatorTesting {
	
	/*
	 * Junit testing - java unit testing
	 * test case - whether actual result is matching with expected or not
	 */

	Calculator calculator = new Calculator();
	
	@Test // this tells that it is an single test case
	public void sumTestPositive() {
		int actualResult = calculator.sum(10, 2);
		assertNotNull(actualResult);
		assertEquals(10, actualResult);
	}
	
	@Test // this tells that it is an single test case
	public void sumTestNegative() {
		int actualResult = calculator.sum(10, 2);
		assertNotNull(actualResult);
		assertNotEquals(4, actualResult);
	}
	
	@Test // this tells that it is an single test case
	public void diffTestPositive() {
		int actualResult = calculator.diff(10, 2);
		assertNotNull(actualResult);
		assertEquals(8, actualResult);
	}
	
	@Test // this tells that it is an single test case
	public void diffTestNegative() {
		int actualResult = calculator.diff(10, 2);
		assertNotNull(actualResult);
		assertNotEquals(4, actualResult);
	}
	
	@Test // this tells that it is an single test case
	public void mulTestPositive() {
		int actualResult = calculator.mul(10, 2);
		assertNotNull(actualResult);
		assertEquals(20, actualResult);
	}
	
	@Test // this tells that it is an single test case
	public void mulTestNegative() {
		int actualResult = calculator.mul(10, 2);
		assertNotNull(actualResult);
		assertNotEquals(4, actualResult);
	}
	
	@Test // this tells that it is an single test case
	public void divTestPositive() {
		int actualResult = calculator.div(10, 2);
		assertNotNull(actualResult);
		assertEquals(5, actualResult);
	}
	
	@Test // this tells that it is an single test case
	public void divTestNegative() {
		int actualResult = calculator.div(10, 2);
		assertNotNull(actualResult);
		assertNotEquals(4, actualResult);
	}

	@BeforeEach
	public void beforeEach(){
		System.out.println("Before each...");
	}

	@AfterEach
	public void afterEach(){
		System.out.println("after each...");
	}

	@BeforeAll
	public static void beforeAll(){
		System.out.println("Before all...");
	}

	@AfterAll
	public static void afterAll() {
		System.out.println("After all...");
	}

	//@org.junit.Test
	@DisplayName("This method tests add method of Calculator class")
	public void testAdd(){
		int actualSum = calculator.sum(2,5);
		int expectedSum = 7;
		Assertions.assertEquals(expectedSum, actualSum);
	}

	//@org.junit.Test
	@DisplayName("This method tests subtract method of Calculator class")
	@Disabled
	public void testSubtract(){
		int actualDiff = calculator.diff(5,2);
		int expectedDiff = 3;
		Assertions.assertEquals(expectedDiff, actualDiff);
	}
}
