package com.company.books.backend.response;

import java.util.List;

import com.company.books.backend.model.Categoria;

public class CategoriaResponse {
	
	private List<Categoria> categorias;

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	
}
