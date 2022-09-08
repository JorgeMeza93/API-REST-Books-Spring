package com.company.books.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.books.backend.response.LibroResponseRest;


public interface ILibroService {
	public ResponseEntity<LibroResponseRest> buscarLibro();
}
