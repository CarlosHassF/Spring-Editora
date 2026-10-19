package com.carlos.editora.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "artigos")
public class ArtigoEntity {
    public ArtigoEntity() {
    }


    public ArtigoEntity(String titulo, String resumo, String idioma, AutorEntity autor, LocalDate dataPublicacao) {
    this.titulo = titulo;
    this.resumo = resumo;
    this.idioma = idioma;
    this.autor = autor;
    this.dataPublicacao = dataPublicacao;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_autor")
    @JsonIgnoreProperties("artigos")
    private AutorEntity autor;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false, length = 200)
    private String resumo;

    private LocalDate dataPublicacao;

    @Column(nullable = false, length = 20)
    private String idioma;

    @Column(name = "is_publicado" , nullable = false)
    private Boolean publicado = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getPublicado() { return publicado; }
    public void setPublicado(Boolean publicado) { this.publicado = publicado; }

    public AutorEntity getAutor() { return autor; }
    public void setAutor(AutorEntity autor) { this.autor = autor; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getResumo() { return resumo; }
    public void setResumo(String resumo) { this.resumo = resumo; }

    public void setIdioma(String idioma) {this.idioma = idioma;}
    public String getIdioma() {return idioma;}



    public void setDataPublicacao(LocalDate dataPublicacao) {this.dataPublicacao = dataPublicacao;}
    public LocalDate getDataPublicacao() {return dataPublicacao;}
}

