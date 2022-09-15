package com.company.books.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;

public class CategoriaServiceImpTest {
	@InjectMocks
	 CategoriaServiceImpl service;
	 @Mock
	 ICategoriaDao categoriaDao;
	 List<Categoria> lista = new ArrayList<Categoria>();
	 @BeforeEach
	 public void init(){
		 MockitoAnnotations.openMocks(this);
		 this.buscarCategoriasTest();
	 }
	 @Test
	 public void buscarCategoriasTest(){
		 // retorna información fija
		 when(categoriaDao.findAll()).thenReturn(lista);
		 ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		 assertEquals(4, response.getBody().getCategoriaResponse().getCategorias().size());
		 verify(categoriaDao, times(1)).findAll();
	 }
	 public void cargarCategorias(){
		 Categoria categoria1 = new Categoria(Long.valueOf(1), "Abarrotes", "Distintos abarrotes");
		 Categoria categoria2 = new Categoria(Long.valueOf(1), "Lacteos", "variedad de lacteos"); 	 
		 Categoria categoria3 = new Categoria(Long.valueOf(1), "Bebidas", "Bebidas sin azúcar"); 	 
		 Categoria categoria4 = new Categoria(Long.valueOf(1), "Carnes blancas", "Distintas carnes"); 	 
		 lista.add(categoria1);
		 lista.add(categoria2);
		 lista.add(categoria3);
		 lista.add(categoria4);

	 }
	 
}
