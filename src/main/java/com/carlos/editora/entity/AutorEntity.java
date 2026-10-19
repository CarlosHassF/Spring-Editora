package com.carlos.editora.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name="autores")
public class AutorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "autor")
    @JsonIgnoreProperties("autor")
    private List<ArtigoEntity> artigos;

    @Column(nullable = false, length = 30)
    private String nome;

    @Column (nullable = false, length = 30, unique=true)
    private String email;

    @Column (nullable = true, length = 15)
    private String nacionalidade;

    @Column (columnDefinition = "TEXT")
    private String biografia;

    public void setArtigos(List<ArtigoEntity> artigos) {this.artigos =artigos;}
    public List<ArtigoEntity> getArtigos() {return artigos;}

    public void setBiografia(String biografia) {this.biografia = biografia;}
    public String getBiografia() {return biografia;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public void setNome(String nome) {this.nome = nome;}
    public String getNome() {return nome;}

    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return email;}

    public void setNacionalidade(String nacionalidade) {this.nacionalidade = nacionalidade;}
    public String getNacionalidade() {return nacionalidade;}
}
