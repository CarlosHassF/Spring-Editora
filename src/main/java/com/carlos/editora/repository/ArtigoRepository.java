package com.carlos.editora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlos.editora.entity.ArtigoEntity;

import java.util.List;

public interface ArtigoRepository extends JpaRepository<ArtigoEntity, Long> {

    List<ArtigoEntity> findByAutorId(Long autorId);

    List<ArtigoEntity> findByIdioma(String idioma);

    List<ArtigoEntity> findByTituloContainingIgnoreCase(String trecho);

    List<ArtigoEntity> findByTituloContainingIgnoreCaseOrResumoContainingIgnoreCase(String resumo, String titulo);

    List<ArtigoEntity> findByPublicado(Boolean publicado);

    List<ArtigoEntity> findByPublicadoTrue();

}