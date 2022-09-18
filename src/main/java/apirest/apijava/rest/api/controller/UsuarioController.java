package apirest.apijava.rest.api.controller;

import apirest.apijava.rest.api.model.UsuarioModel;
import apirest.apijava.rest.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // Consulta usuário através do código
    @GetMapping(path = "/api/usuario/{codigo}")
    public ResponseEntity consultar(@PathVariable("codigo") Integer codigo){

        return repository.findById(codigo)

                // Caso encontre o código, retorna os dados do usuário
                .map(record -> ResponseEntity.ok().body(record))

                // Caso não encontre, retorna um "não encontrado"
                .orElse(ResponseEntity.notFound().build());
    }

    // Método para salvar com método post
    @PostMapping(path = "/api/usuario/salvar")
    // Dados do usuário são enviados no corpo da requisição
    public UsuarioModel salvar(@RequestBody UsuarioModel usuario){

        return repository.save(usuario);
    }
}
