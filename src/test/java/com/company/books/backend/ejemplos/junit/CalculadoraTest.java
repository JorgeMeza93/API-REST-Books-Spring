package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	@Test
	public void calculadoraAssertEqualTest(){
		Calculadora calculadora1 = new Calculadora();
		assertEquals(3, calculadora1.sumar(1.5, 1.5));
		assertEquals(20, calculadora1.restar(50, 30));
		assertEquals(99, calculadora1.multiplicar(3, 33));
		assertEquals(4, calculadora1.dividir(80, 20));
	}
	
	
}
