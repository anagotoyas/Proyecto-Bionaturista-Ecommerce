package com.bionaturista.domain.services.impl;

import com.bionaturista.application.dto.categoria.CategoriaDto;
import com.bionaturista.application.dto.categoria.RespuestaCategoriaEntity;
import com.bionaturista.application.dto.compuesto.CompuestoDto;
import com.bionaturista.application.dto.compuesto.RespuestaCompuestoEntity;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Categoria;
import com.bionaturista.domain.entities.Compuesto;
import com.bionaturista.domain.repositories.CompuestoRepository;
import com.bionaturista.domain.services.CompuestoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompuestoServiceImpl implements CompuestoService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    CompuestoRepository compuestoRepository;

    @Value("${service.compuesto}")
    String serviceCompuesto;

    @Override
    public Compuesto crearCompuesto(CompuestoDto compuesto) throws InterruptedException {

        Compuesto compuesto1 = new Compuesto();
        compuesto1.setNombreCompuesto(compuesto.getNombreCompuesto());

        Compuesto compuestonew = compuestoRepository.save(compuesto1);

        HttpEntity<Compuesto> entity = new HttpEntity<>(compuestonew);
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<RespuestaCompuestoEntity> response = restTemplate.exchange(serviceCompuesto, HttpMethod.POST, entity,
                new ParameterizedTypeReference<RespuestaCompuestoEntity>() {
                });

        RespuestaCompuestoEntity respuesta = new RespuestaCompuestoEntity();

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return compuestonew;
        }

        else {
            logger.error("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
        }
    }

    @Override
    public Compuesto modificarCompuesto(CompuestoDto compuesto) throws InterruptedException {

        Compuesto compuesto1 = new Compuesto();
        compuesto1.setIdCompuesto(compuesto.getIdCompuesto());
        compuesto1.setNombreCompuesto(compuesto.getNombreCompuesto());

        Compuesto compuestoupdate = compuestoRepository.save(compuesto1);

        HttpEntity<Compuesto> entity = new HttpEntity<>(compuestoupdate);
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<RespuestaCategoriaEntity> response = restTemplate.exchange(serviceCompuesto, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<RespuestaCategoriaEntity>() {
                });

        RespuestaCompuestoEntity respuesta = new RespuestaCompuestoEntity();

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return compuestoupdate;
        }

        else {
            logger.error("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
        }

    }

    @Override
    public Respuesta eliminarCompuesto(Integer idCompuesto) throws InterruptedException {

        Respuesta respuesta = new Respuesta();

        if(compuestoRepository.existsById(idCompuesto))
        {
            compuestoRepository.deleteById(idCompuesto);
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("101");
            respuesta.setMensaje("Compuesto eliminado.");
            return respuesta;
        }
        else
        {
            logger.error("No se pudo eliminar el compuesto o no existe");
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:");
        }
    }

    @Override
    public List<Compuesto> getAllCompuestos() {
        return compuestoRepository.findAll();
    }

    @Override
    public List<Compuesto> listarCompuesto() throws InterruptedException {
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<List<CompuestoDto>> response = restTemplate.exchange(serviceCompuesto, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CompuestoDto>>() {
                });

        List<Compuesto> lista = new ArrayList<>();
        Compuesto compuesto;

        if(response.getStatusCode()== HttpStatus.OK)

        {
            for (CompuestoDto item : response.getBody()) {
                compuesto = new Compuesto();
                compuesto.setIdCompuesto(item.getIdCompuesto());
                compuesto.setNombreCompuesto(item.getNombreCompuesto());
                lista.add(compuesto);

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
    public Compuesto obtenerCompuestoPorIdCompuesto(Integer idCompuesto) throws InterruptedException {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<CompuestoDto>> response=restTemplate.exchange(serviceCompuesto,HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CompuestoDto>>() {
                });

        Compuesto compuesto = new Compuesto();

        if(response.getStatusCode()==HttpStatus.OK)

        {
            for (CompuestoDto item : response.getBody()) {

                if (idCompuesto.equals(item.getIdCompuesto()))	{
                    compuesto.setIdCompuesto(item.getIdCompuesto());
                    compuesto.setNombreCompuesto(item.getNombreCompuesto());

                }

            }
            return compuesto;
        }
        else
        {
            logger.error("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
        }
    }
}
