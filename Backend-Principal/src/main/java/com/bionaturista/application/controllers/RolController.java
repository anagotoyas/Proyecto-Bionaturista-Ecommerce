package com.bionaturista.application.controllers;

import com.bionaturista.application.dto.respuestas.Respuesta;
import com.bionaturista.application.dto.rol.RespuestaListRol;
import com.bionaturista.application.dto.rol.RespuetaRol;
import com.bionaturista.application.dto.usuario.RespuestaUsuario;
import com.bionaturista.domain.entities.Rol;
import com.bionaturista.domain.entities.Usuario;
import com.bionaturista.domain.services.RolService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<RespuetaRol>> crearRol(@Valid @RequestBody Rol rol) {


        RespuetaRol respuesta = new RespuetaRol();

        try {
            Rol rolNew = rolService.crearRol(rol);

            respuesta.setMensaje("success");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");
            respuesta.setData(rolNew);

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (Exception e){

            respuesta.setMensaje("failed");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public ResponseEntity<WrapperResponse<RespuetaRol>> modificarRol(@Valid @RequestBody Rol rol){

        RespuetaRol respuesta = new RespuetaRol();

        try {
            Rol rolUpdate = rolService.modificarRol(rol);

            respuesta.setMensaje("success");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");
            respuesta.setData(rolUpdate);

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (Exception e){

            respuesta.setMensaje("failed");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{idRol}")
    public ResponseEntity<WrapperResponse<Respuesta>> eliminarCompuesto(@PathVariable("idRol") Integer idRol){


        Respuesta respuesta = new Respuesta();

        try {

            rolService.eliminarRol(idRol);

            respuesta.setMensaje("success");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (Exception e){

            respuesta.setMensaje("failed");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{idRol}")
    public ResponseEntity<WrapperResponse<RespuetaRol>> obtenerRolPorIdRol(@PathVariable("idRol") Integer idRol) {


        RespuetaRol respuesta = new RespuetaRol();

        try {
            Rol rol = rolService.obtenerRolPorIdRol(idRol);

            respuesta.setMensaje("success");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");
            respuesta.setData(rol);

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (Exception e){

            respuesta.setMensaje("failed");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping
    public ResponseEntity<WrapperResponse<RespuestaListRol>> listarRol(){

        RespuestaListRol respuesta = new RespuestaListRol();

        try {
            List<Rol> roles = rolService.listarRol();

            respuesta.setMensaje("success");
            respuesta.setSatisfactorio(true);
            respuesta.setCodigo("200");
            respuesta.setData(roles);

            return new WrapperResponse<>(true, "success", respuesta).createResponse(HttpStatus.OK);

        }catch (Exception e){

            respuesta.setMensaje("failed");
            respuesta.setSatisfactorio(false);
            respuesta.setCodigo("400");

            return new WrapperResponse<>(false, "failed", respuesta).createResponse(HttpStatus.BAD_REQUEST);
        }

    }

}

