package com.bionaturista.domain.services.impl;

import com.bionaturista.application.dto.categoria.CategoriaDto;
import com.bionaturista.application.dto.categoria.RespuestaCategoriaEntity;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Categoria;
import com.bionaturista.application.dto.categoria.RespuestaCategoria;
import com.bionaturista.domain.repositories.CategoriaRepository;
import com.bionaturista.domain.services.CategoriaService;
import com.bionaturista.validators.CategoriaValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    protected final Log logger = LogFactory.getLog(getClass());
    @Value("${service.categoria}")
    String serviceCategoria;


    @Override
    public Categoria crearCategoria(CategoriaDto categoria) throws InterruptedException {


        Categoria category = new Categoria();
        category.setNombreCategoria(categoria.getNombreCategoria());

        Categoria categorynew = categoriaRepository.save(category);

        HttpEntity<Categoria> entity = new HttpEntity<>(categorynew);
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<RespuestaCategoriaEntity> response = restTemplate.exchange(serviceCategoria, HttpMethod.POST, entity,
                new ParameterizedTypeReference<RespuestaCategoriaEntity>() {
                });

        RespuestaCategoriaEntity respuesta = new RespuestaCategoriaEntity();

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return categorynew;
        }

        else {
            logger.error("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
        }


    }

    @Override
    public Categoria modificarCategoria(CategoriaDto categoria) throws InterruptedException {

        Categoria category = new Categoria();
        category.setIdCategoria(categoria.getIdCategoria());
        category.setNombreCategoria(categoria.getNombreCategoria());

        Categoria categoryupdate = categoriaRepository.save(category);

        HttpEntity<Categoria> entity = new HttpEntity<>(categoryupdate);
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<RespuestaCategoriaEntity> response = restTemplate.exchange(serviceCategoria, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<RespuestaCategoriaEntity>() {
                });

        RespuestaCategoriaEntity respuesta = new RespuestaCategoriaEntity();

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return categoryupdate;
        }

        else {
            logger.error("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
        }
    }

    @Override
    public Respuesta eliminarCategoria(Integer idCategoria) throws InterruptedException {

        Respuesta respuesta = new Respuesta();

        if(categoriaRepository.existsById(idCategoria))
        {
            categoriaRepository.deleteById(idCategoria);
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("101");
            respuesta.setMensaje("Categoría eliminada.");
            return respuesta;
        }
        else
        {
            logger.error("No se pudo eliminar la categoria o no existe");
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:");
        }

    }

    @Override
    public List<Categoria> listarCategoria() throws InterruptedException {

        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<List<CategoriaDto>> response = restTemplate.exchange(serviceCategoria,HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CategoriaDto>>() {
                });

        List<Categoria> lista = new ArrayList<>();
        Categoria categoria;

        if(response.getStatusCode()==HttpStatus.OK)

        {
            for (CategoriaDto item : response.getBody()) {
                categoria = new Categoria();
                categoria.setIdCategoria(item.getIdCategoria());
                categoria.setNombreCategoria(item.getNombreCategoria());
                lista.add(categoria);

            }
            return lista;
        }
        else
        {
            logger.error("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
        }

    }

    @Override
    public Categoria obtenerCategoriaPorIdCategoria(Integer idCategoria) throws InterruptedException {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<CategoriaDto>> response=restTemplate.exchange(serviceCategoria,HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CategoriaDto>>() {
                });

        List<Categoria> lista = new ArrayList<>();
        Categoria categoria = new Categoria();

        if(response.getStatusCode()==HttpStatus.OK)

        {
            for (CategoriaDto item : response.getBody()) {

                if (idCategoria.equals(item.getIdCategoria()))	{
                    categoria.setIdCategoria(item.getIdCategoria());
                    categoria.setNombreCategoria(item.getNombreCategoria());

                }

            }
            return categoria;
        }
        else
        {
            logger.error("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
        }


    }

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }
}


