package com.carlos.editora.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.editora.entity.*;
import com.carlos.editora.repository.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;
@Service 
public class ArtigoService {
        private final ArtigoRepository ArRepo;
        private final AutorRepository AuRepo;
  

        public ArtigoService(ArtigoRepository ArRepo, AutorRepository AuRepo){
            this.ArRepo = ArRepo;
            this.AuRepo = AuRepo;
        }
        //LISTA TODOS OS ARTIGOS
        public List<ArtigoEntity> listarTodos(){
            return ArRepo.findAll();
        }
        // CRIA UM ARTIGO
        public Optional<ArtigoEntity> criarArtigo(ArtigoEntity artigo) {
        if (artigo.getAutor() == null || artigo.getAutor().getId() == null) {
            return Optional.empty();
        }
            return AuRepo.findById(artigo.getAutor().getId()).map(autor -> {
                artigo.setAutor(autor);
                return ArRepo.save(artigo);
        });     
}
        //PROCURA ARTIGOS POR ID
        public Optional<ArtigoEntity> procurarArtigoPorId(long id){
            return ArRepo.findById(id);
        }
        //ATUALIZA UM ARTIGO
        @Transactional 
        public Optional<ArtigoEntity> atualizarArtigo(long id, ArtigoEntity novoArtigo){
            return ArRepo.findById(id).map(antigo ->{
                antigo.setTitulo(novoArtigo.getTitulo());
                antigo.setDataPublicacao(novoArtigo.getDataPublicacao());
                antigo.setIdioma(novoArtigo.getIdioma());
                antigo.setResumo(novoArtigo.getResumo());
                antigo.setPublicado(novoArtigo.getPublicado());
                return ArRepo.save(antigo);
            });
        }
        //DELETA UM ARTIGO
        public boolean deletar(long id){
            if(ArRepo.existsById(id)){
                ArRepo.deleteById(id);
                return true;
            }
            return false;
        }
        //Busca Artigos por titulo
        public List<ArtigoEntity> buscaArtigosPorTitulo(String Keyword){
            return ArRepo.findByTituloContainingIgnoreCase(Keyword);
        }
        //Busca Artigos que contenham palavras chave
        public List<ArtigoEntity> buscarArtigosPorPalavraChave(String[] KeyWords){
            Set<ArtigoEntity> resultado = new LinkedHashSet<>();
            for(String KeyWord : KeyWords){
                resultado.addAll(
                    ArRepo.findByTituloContainingIgnoreCaseOrResumoContainingIgnoreCase(KeyWord,KeyWord)
                );

            }
            return new ArrayList<>(resultado);
        }
        //listar por publicado

        public List<ArtigoEntity> listarPorPublicado(Boolean publicado) {
         return ArRepo.findByPublicado(publicado);
        };

        // lista só os publicados
        public List<ArtigoEntity> listarPublicados() {
        return ArRepo.findByPublicadoTrue();
        }


        public void deletarTodos(){
            ArRepo.deleteAll();
        } 

        
}
