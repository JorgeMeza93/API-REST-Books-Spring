package com.company.books.backend.service;

import java.util.List;

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
	private ICategoriaDao catergoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public CategoriaResponseRest buscarCategorias() {
		log.info("Inicio del método buscarCategorias()");
		CategoriaResponseRest response = new CategoriaResponseRest();
		try {
			List<Categoria> categoria = (List<Categoria>) catergoriaDao.findAll();
			response.getCategoriaResponse().setCategorias(categoria);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitoss");
		} catch (Exception e) {
			response.setMetadata("Respuesta no ok", "-1", "Respuesta Incorrecta");
			log.error("Error al consultar categorías", e.getMessage());
			e.getStackTrace();
		}
		return response; // devuelve 200
	}

}
