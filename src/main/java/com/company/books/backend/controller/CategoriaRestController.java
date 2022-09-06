package com.company.books.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.service.ICategoriaService;

@RestController
@RequestMapping("/v1")
public class CategoriaRestController {
	@Autowired
	private ICategoriaService service;
	@GetMapping("/amigo")
	public CategoriaResponseRest consultarCategorias() {
		CategoriaResponseRest response = service.buscarCategorias();
		return response;
	}
}
