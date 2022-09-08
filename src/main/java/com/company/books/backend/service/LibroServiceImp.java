package com.company.books.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.model.Libro;
import com.company.books.backend.model.dao.ILibroDao;
import com.company.books.backend.response.LibroResponseRest;

@Service
public class LibroServiceImp implements ILibroService{
	
	@Autowired
	private ILibroDao libroDao;
	private static Logger log = LoggerFactory.getLogger(LibroServiceImp.class);
	
	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<LibroResponseRest> buscarLibro() {
		log.info("Inicio del método buscarLibro()");
		LibroResponseRest response = new LibroResponseRest();
		try {
			List<Libro> libros = (List<Libro>) libroDao.findAll();
			response.getLibroResponse().setLibros(libros);
			response.setMetadata("Respuesta Ok", "00", "Respuesta ok");
		} catch (Exception e) {
			response.setMetadata("Respuesta no Ok", "-1", "Error al consultar libros");
			log.error("Error al consultar libros: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

}
