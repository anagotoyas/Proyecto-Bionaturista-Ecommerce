package com.bionaturista.domain.services.impl;

import com.bionaturista.domain.entities.Compuesto;
import com.bionaturista.domain.repositories.CompuestoRepository;
import com.bionaturista.domain.services.CompuestoService;
import com.bionaturista.validators.CompuestoValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompuestoServiceImpl implements CompuestoService {

    private final CompuestoRepository compuestoRepository;

    public CompuestoServiceImpl(CompuestoRepository compuestoRepository){
        this.compuestoRepository=compuestoRepository;
    }


    @Override
    public Compuesto crearCompuesto(Compuesto compuesto){
        CompuestoValidator.validate(compuesto);
        return compuestoRepository.save(compuesto);
    }

    @Override
    public Compuesto modificarCompuesto(Compuesto compuesto){
        CompuestoValidator.validate(compuesto);
        return compuestoRepository.save(compuesto);
    }

    @Override
    public void eliminarCompuesto(Integer idCompuesto){
        compuestoRepository.deleteById(idCompuesto);
    }

    @Override
    public List<Compuesto> listarCompuesto(){
        return compuestoRepository.findAll();
    }

    @Override
    public Compuesto obtenerCompuestoPorIdCompuesto(Integer idCompuesto){
        return compuestoRepository.findById(idCompuesto).orElse(new Compuesto());
    }
}

