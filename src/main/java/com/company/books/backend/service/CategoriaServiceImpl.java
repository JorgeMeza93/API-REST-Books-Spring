package com.company.books.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
	
	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
		log.info("Inicio del método buscarCategorias()");
		CategoriaResponseRest response = new CategoriaResponseRest();
		try {
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			response.getCategoriaResponse().setCategorias(categoria);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitoss");
		} catch (Exception e) {
			response.setMetadata("Respuesta no ok", "-1", "Error al consultar categorías");
			log.error("Error al consultar categorías", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
		log.info("Inicio de método buscarPorId");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		try {
			Optional<Categoria> categoria = categoriaDao.findById(id);
			if(categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategorias(list);
			}
			else {
				log.error("Error en consultar categoria");
				response.setMetadata("Respuesta no OK", "-1", "Categoría no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error en consultar categoria");
			response.setMetadata("Respuesta no OK", "-1", "Error al consultar categoría");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
		log.info("Inicio de método crear()");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		try {
			Categoria categoriaCreada = categoriaDao.save(categoria);
			if(categoriaCreada != null) {
				list.add(categoriaCreada);
				response.getCategoriaResponse().setCategorias(list);
			}
			else {
				log.error("Error en crear la categoria");
				response.setMetadata("Respuesta no OK", "-1", "Categoria no creada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Error en crear una categoria");
			response.setMetadata("Respuesta no OK", "-1", "Error al crear categoría");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Transactional
	@Override
	public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
		log.info("Inicio del método actualizar");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		try {
			Optional<Categoria> categoriaAActualizar = categoriaDao.findById(id);
			if(categoriaAActualizar.isPresent() ) {
				categoriaAActualizar.get().setNombre(categoria.getNombre());
				categoriaAActualizar.get().setDescripcion(categoria.getDescripcion());
				Categoria categoriaActualizada = categoriaDao.save(categoriaAActualizar.get());
				if( categoriaActualizada != null) {
					response.setMetadata("Respuesta OK", "00", "Categoria Actualizada");
					list.add(categoriaActualizada);
					response.getCategoriaResponse().setCategorias(list);
				}
				else {
					log.error(("Error en actualizar categoria"));
					response.setMetadata("Respuesta no OK", "00", "Categoria no actualizada");
					return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
					
				}
			}
			else {
				log.error(("Error en actualizar categoria"));
				response.setMetadata("Respuesta no OK", "00", "Categoria no actualizada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error en actualizar categoria", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta no OK", "00", "Categoria no actualizada");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta Ok", "00", "Categoria Actualizada");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

}
