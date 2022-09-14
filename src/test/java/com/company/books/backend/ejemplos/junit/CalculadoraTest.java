package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	@BeforeAll
	public static void primero(){
		System.out.println("Primero");
	}
	
	@AfterAll
	public static void ultimo(){
		System.out.println("Al último");
	}
	
	
	@Test
	public void calculadoraAssertEqualTest(){
		Calculadora calculadora1 = new Calculadora();
		assertEquals(3, calculadora1.sumar(1.5, 1.5));
		assertEquals(20, calculadora1.restar(50, 30));
		assertEquals(99, calculadora1.multiplicar(3, 33));
		assertEquals(4, calculadora1.dividir(80, 20));
	}
	
	@Test
	public void calculadoraTest(){
		Calculadora calculadora1 = new Calculadora();
		assertTrue(calculadora1.sumar(3, 999) == 1002);
		assertTrue(calculadora1.restar(100, 7) == 93);
		assertTrue(calculadora1.multiplicar(25, 3) == 75);
		assertTrue(calculadora1.dividir(100, 5) == 20);
	}
	
}
