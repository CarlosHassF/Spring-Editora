package com.carlos.editora.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.carlos.editora.entity.ArtigoEntity;
import com.carlos.editora.entity.AutorEntity;
import com.carlos.editora.service.AutorService;

import java.util.List;
@RestController 
@RequestMapping("api/autores")
public class AutorController {
    private final AutorService serv;

    public AutorController(AutorService serv){
        this.serv = serv;
    }
    @PostMapping
        public ResponseEntity<AutorEntity> createAutor(@RequestBody AutorEntity aut){
            try{
                AutorEntity autor = serv.criarAutor(aut);
                return ResponseEntity.status(HttpStatus.CREATED).body(autor);
            }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    @GetMapping ("/{id}")
    public ResponseEntity<AutorEntity> getAutor(@PathVariable ("id") Long id){
        return serv.buscarAutorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    //ATUALIZA UM autor

    @PutMapping("/{id}")
    public ResponseEntity<AutorEntity> atualizaAutor(@PathVariable Long id, @RequestBody AutorEntity antigo){
        return serv.atualizarAutor(id,antigo)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    
    }

    @GetMapping ("{id}/artigos")
    public ResponseEntity<List<ArtigoEntity>> buscarArtigosPorAutor(@PathVariable ("id") long id){
        return serv.buscarArtigosPorAutor(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity <List<AutorEntity>> getAllAutores(){
        return ResponseEntity.ok(serv.listarTodosAutor());
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteAutor(@PathVariable ("id") long id ){
        try {
             if(serv.deletarAutor(id)){
                return ResponseEntity.noContent().build();
             }
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
    }



}
