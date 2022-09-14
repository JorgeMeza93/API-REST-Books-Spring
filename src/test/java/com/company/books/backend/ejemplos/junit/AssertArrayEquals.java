package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssertArrayEquals {

	@Test
	public void pruebaArregloIguales(){
		String [] arreglo1 = {"a", "b", "c"};
		String [] arreglo2 = {"a", "b", "c"};
		String [] arreglo3 = {"a", "b", "c", "d"};
		
		assertArrayEquals(arreglo1, arreglo2);
		assertArrayEquals(arreglo1, arreglo3);
		
	}
	
}
