package com.carlos.editora.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlos.editora.entity.ArtigoEntity;
import com.carlos.editora.entity.AutorEntity;
import com.carlos.editora.repository.ArtigoRepository;
import com.carlos.editora.repository.AutorRepository;
import java.util.List;
import java.util.Optional;
@Service 
public class AutorService {
    private final AutorRepository AuRepo;
    private final ArtigoRepository ArRepo;

    public AutorService(AutorRepository AuRepo, ArtigoRepository ArRepo){
        this.AuRepo = AuRepo;
        this.ArRepo = ArRepo;
    }

    //CRIA UM AUTOR

    public AutorEntity criarAutor(AutorEntity autor){
        return AuRepo.save(autor);
    }
    //BUSCA ARTIGOS POR AUTOR

    public Optional <List<ArtigoEntity>> buscarArtigosPorAutor(Long id){
        if(AuRepo.existsById(id)){
            return Optional.of(ArRepo.findByAutorId(id));
        }
            return Optional.empty();

    }
    //LISTA TODOS OS AUTORES
    public List <AutorEntity> listarTodosAutor(){
        return AuRepo.findAll();
    }

    public Optional<AutorEntity> buscarAutorPorId(Long id){
        return AuRepo.findById(id);
    }

    //ATUALIZA UM AUTOR
    @Transactional
    public Optional<AutorEntity> atualizarAutor(Long id, AutorEntity novoAutor){
        return AuRepo.findById(id).map(antigo->{
            antigo.setEmail(novoAutor.getEmail());
            antigo.setBiografia(novoAutor.getBiografia());
            antigo.setNacionalidade(novoAutor.getNacionalidade());
            antigo.setNome(novoAutor.getNome());
            return AuRepo.save(antigo);
        });
    }
    //DELETA UM AUTOR E SEUS ARTIGOS
    @Transactional 
    public boolean deletarAutor(Long id){
        if(AuRepo.existsById(id)){
            ArRepo.deleteAll(ArRepo.findByAutorId(id));
            AuRepo.deleteById(id);
            return true;
        }

        return false;
    }

    
}
