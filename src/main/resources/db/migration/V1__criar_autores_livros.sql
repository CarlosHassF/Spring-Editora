
create table autores(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    nacionalidade VARCHAR(15),
    biografia TEXT
);

create table artigos(

    id BIGSERIAL PRIMARY KEY,
    id_autor BIGINT NOT NULL REFERENCES autores(id),
    titulo VARCHAR(50) NOT NULL,
    resumo VARCHAR(200) NOT NULL,
    data_publicacao DATE,
    idioma VARCHAR(20) NOT NULL,
    is_publicado BOOLEAN


);