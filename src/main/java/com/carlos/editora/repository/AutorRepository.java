package com.carlos.editora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlos.editora.entity.AutorEntity;


public interface AutorRepository extends JpaRepository<AutorEntity, Long> {

}