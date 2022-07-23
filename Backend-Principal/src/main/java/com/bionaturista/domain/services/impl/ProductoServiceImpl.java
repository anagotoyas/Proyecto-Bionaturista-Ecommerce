package com.bionaturista.domain.services.impl;

import com.bionaturista.application.dto.categoria.CategoriaDto;
import com.bionaturista.application.dto.categoria.RespuestaCategoriaEntity;
import com.bionaturista.application.dto.producto.ProductoDto;
import com.bionaturista.application.dto.producto.RespuestaProductoEntity;
import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.domain.entities.Categoria;
import com.bionaturista.domain.entities.Producto;
import com.bionaturista.domain.repositories.ProductoRepository;
import com.bionaturista.domain.services.ProductoService;
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
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    protected final Log logger = LogFactory.getLog(getClass());
    @Value("${service.producto}")
    String serviceProducto;

    @Override
    public Producto crearProducto(ProductoDto producto) throws InterruptedException {
        Producto producty = new Producto();
        producty.setNombreProducto(producto.getNombreProducto());
        producty.setImagenProducto(producto.getImagenProducto());
        producty.setDescripcionProducto(producto.getDescripcionProducto());
        producty.setPrecioProducto(producto.getPrecioProducto());
        producty.setStockProducto(producto.getStockProducto());
        producty.setCategoria(producto.getCategoria());
        producty.setCompuesto(producto.getCompuesto());

        Producto productnew = productoRepository.save(producty);

        System.out.println(productnew);

        HttpEntity<Producto> entity = new HttpEntity<>(productnew);
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<RespuestaProductoEntity> response = restTemplate.exchange(serviceProducto, HttpMethod.POST, entity,
                new ParameterizedTypeReference<RespuestaProductoEntity>() {
                });
        System.out.println(productnew);
        RespuestaProductoEntity respuesta = new RespuestaProductoEntity();

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return productnew;
        }

        else {
            logger.error("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
        }
    }

    @Override
    public Producto modificarProducto(ProductoDto producto) throws InterruptedException {
        Producto producty = new Producto();
        producty.setIdProducto(producto.getIdProducto());
        producty.setNombreProducto(producto.getNombreProducto());
        producty.setImagenProducto(producto.getImagenProducto());
        producty.setDescripcionProducto(producto.getDescripcionProducto());
        producty.setPrecioProducto(producto.getPrecioProducto());
        producty.setStockProducto(producto.getStockProducto());
        producty.setCategoria(producto.getCategoria());
        producty.setCompuesto(producto.getCompuesto());


        Producto productoupdate = productoRepository.save(producty);

        HttpEntity<Producto> entity = new HttpEntity<>(productoupdate);
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<RespuestaProductoEntity> response = restTemplate.exchange(serviceProducto, HttpMethod.PUT, entity,
                new ParameterizedTypeReference<RespuestaProductoEntity>() {
                });

        RespuestaProductoEntity respuesta = new RespuestaProductoEntity();

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return productoupdate;
        }

        else {
            logger.error("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
        }
    }

    @Override
    public Respuesta eliminarProducto(Integer idProducto) throws InterruptedException {
        Respuesta respuesta = new Respuesta();

        if(productoRepository.existsById(idProducto))
        {
            productoRepository.deleteById(idProducto);
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("101");
            respuesta.setMensaje("Producto eliminado.");
            return respuesta;
        }
        else
        {
            logger.error("No se pudo eliminar el producto o no existe");
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:");
        }
    }

    @Override
    public List<Producto> listarProducto() throws InterruptedException {
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<List<ProductoDto>> response = restTemplate.exchange(serviceProducto,HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ProductoDto>>() {
                });

        List<Producto> lista = new ArrayList<>();
        Producto producto;

        if(response.getStatusCode()==HttpStatus.OK)

        {
            for (ProductoDto item : response.getBody()) {
                producto = new Producto();
                producto.setIdProducto(item.getIdProducto());
                producto.setNombreProducto(item.getNombreProducto());
                producto.setImagenProducto(item.getImagenProducto());
                producto.setDescripcionProducto(item.getDescripcionProducto());
                producto.setPrecioProducto(item.getPrecioProducto());
                producto.setStockProducto(item.getStockProducto());
                producto.setCategoria(item.getCategoria());
                producto.setCompuesto(item.getCompuesto());
                lista.add(producto);

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
    public Producto obtenerProductoPorIdProducto(Integer idProducto) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<ProductoDto>> response=restTemplate.exchange(serviceProducto,HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ProductoDto>>() {
                });

        List<Producto> lista = new ArrayList<>();
        Producto producto = new Producto();

        if(response.getStatusCode()==HttpStatus.OK)

        {
            for (ProductoDto item : response.getBody()) {

                if (idProducto.equals(item.getIdProducto()))	{
                    producto.setIdProducto(item.getIdProducto());
                    producto.setNombreProducto(item.getNombreProducto());
                    producto.setImagenProducto(item.getImagenProducto());
                    producto.setDescripcionProducto(item.getDescripcionProducto());
                    producto.setPrecioProducto(item.getPrecioProducto());
                    producto.setStockProducto(item.getStockProducto());
                    producto.setCategoria(item.getCategoria());
                    producto.setCompuesto(item.getCompuesto());

                }

            }
            return producto;
        }
        else
        {
            logger.error("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
            throw new InterruptedException("Error en la respuesta del servicio invocado. Code:" + response.getStatusCode());
        }
    }

    @Override
    public Long countProductos() throws InterruptedException {
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombreProducto) throws InterruptedException {
        List<Producto> productoN = productoRepository.buscarProductoPorNombre(nombreProducto);
        return productoN;
    }
}
