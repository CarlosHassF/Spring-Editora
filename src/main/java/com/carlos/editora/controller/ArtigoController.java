package com.carlos.editora.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.editora.service.*;
import com.carlos.editora.entity.ArtigoEntity;



@RestController 
@RequestMapping("api/artigos")
public class ArtigoController {

    private final ArtigoService serv;

    public ArtigoController(ArtigoService serv) {
        this.serv = serv;
    }

    //BUSCA TODOS OS ARTIGOS

    @GetMapping
    public ResponseEntity<List<ArtigoEntity>> getAllArtigos(
        @RequestParam(required = false) String titulo) {
    if (titulo != null) {
        return ResponseEntity.ok(serv.buscaArtigosPorTitulo(titulo));
    }
    return ResponseEntity.ok(serv.listarTodos());
}

    @GetMapping("/publicado")
    public ResponseEntity <List<ArtigoEntity>> getAllPublicados(){
        return ResponseEntity.ok(serv.listarPublicados());
    }
    //BUSCA ARTIGO POR ID

    @GetMapping({"/{id}"})
    public ResponseEntity<ArtigoEntity> getArtigo(@PathVariable ("id") long id){
        Optional<ArtigoEntity> data = serv.procurarArtigoPorId(id);

        if(data.isPresent()){
            ArtigoEntity a = data.get();
            return new ResponseEntity<>(a, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //CRIA UM ARTIGO
    @PostMapping
        public ResponseEntity<ArtigoEntity> createArtigo(@RequestBody ArtigoEntity art) {
            return serv.criarArtigo(art)
            .map(artigo -> ResponseEntity.status(HttpStatus.CREATED).body(artigo))  // 201
            .orElse(ResponseEntity.notFound().build());                              // 404
        }

    //DELETA UM ARTIGO

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteArtigo(@PathVariable ("id") long id ){
        try {
             if(serv.deletar(id)){
                return ResponseEntity.noContent().build();
             }
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
    }

    //ATUALIZA UM ARTIGO

    @PutMapping("/{id}")
    public ResponseEntity<ArtigoEntity> atualizaArtigo(@PathVariable Long id, @RequestBody ArtigoEntity antigo){
        return serv.atualizarArtigo(id,antigo)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllArtigos() {
        try {
            serv.deletarTodos();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ArtigoEntity>> buscarArtigosPorPalavraChave(@RequestParam String[] palavras){
        return ResponseEntity.ok(serv.buscarArtigosPorPalavraChave(palavras));
    }

}
