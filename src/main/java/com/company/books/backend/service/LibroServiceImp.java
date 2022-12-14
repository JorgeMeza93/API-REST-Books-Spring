package com.company.books.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
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
import com.company.books.backend.response.ResponseRest;

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
	@Transactional(readOnly = true)
	@Override
	public ResponseEntity<LibroResponseRest> buscarLibroPorID(Long id) {
		log.info("Inicio del método buscarLibroPorID()");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> lista = new ArrayList<>();
		try {
			Optional<Libro> libro = libroDao.findById(id);
			if(libro.isPresent()) {
				lista.add(libro.get());
				response.getLibroResponse().setLibros(lista);
			}
			else {
				log.error("Error al buscar libro");
				response.setMetadata("Respuesta No Ok", "-1", "Libro no encontrado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error al buscar libro");
			response.setMetadata("Respuesta no ok", "-1", "Error al intentar buscar libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}
	
	@Transactional
	@Override
	public ResponseEntity<LibroResponseRest> crearLibro(Libro libro) {
		log.info("Iniciar método crearLibro()");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> lista = new ArrayList<>();
		try {
			Libro libroNuevo = libroDao.save(libro);
			if(libroNuevo != null) {
				lista.add(libroNuevo);
				response.getLibroResponse().setLibros(lista);
				response.setMetadata("Respuesta OK", "00", "Libro creado correctamente");
			}
			else {
				log.error("Error al creaar libro");
				response.setMetadata("Respuesta no ok", "-1", "Error al crear libro");
				return  new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Error al crear libro: ", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta no Ok", "-1", "Error al crear libro");	
		} 
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}
	@Transactional
	@Override
	public ResponseEntity<LibroResponseRest> actualizarLibro(Libro libro, Long id) {
		log.info("Iniciar método actualizarLibro()");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> lista = new ArrayList<>();
		try {
			Optional<Libro> libroAActualizar = libroDao.findById(id);
			if(libroAActualizar.isPresent()) {
				libroAActualizar.get().setNombre(libro.getNombre());
				libroAActualizar.get().setAutor(libro.getAutor());
				libroAActualizar.get().setCategoria(libro.getCategoria());
				libroAActualizar.get().setSinopsis(libro.getSinopsis());
				libroAActualizar.get().setEditorial(libro.getEditorial());
				Libro libroActualizado = libroDao.save(libroAActualizar.get());
				if(libroActualizado != null) {
					response.setMetadata("Respuesta OK", "00", "Libro actualizado correctamente");
					lista.add(libroActualizado);
					response.getLibroResponse().setLibros(lista);
				}
				else {
					log.error("Error en Actualizar el libro");
					response.setMetadata("Respuesta no ok", "-1", "Libro no actualizado");
					return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
				}	
			}
			else {
				log.error("Error en actualizar libro");
				response.setMetadata("Respuesta no ok", "-1", "Libro no actualizado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error en actualizar libro", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta no Ok", "-1", "Libro no actualizado");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}
	@Transactional
	@Override
	public ResponseEntity<LibroResponseRest> eliminarLibro(Long id) {
		log.info("Inicio del método eliminar Libro");
		LibroResponseRest response = new LibroResponseRest();
		try {
			libroDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa, libro eliminado");
		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("Respuesta no ok", "-1", "Libro no eliminado");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}

}
