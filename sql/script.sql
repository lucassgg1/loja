-- postgres
CREATE TABLE usuario
(   id bigserial NOT NULL,
    nome varchar(255),    
    cpf varchar(30),
    dataNascimento date,
    email varchar(50),
    login varchar(50),   
    password varchar(50),
    ativo boolean,
    telefone varchar(15),
    endereco varchar(255),
    numEndereco varchar(5),
    complEndereco varchar(50),
    bairro varchar(50),
    cidade varchar(100),
    estado varchar(50),
    uf varchar(2),
    cep varchar(9),
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

CREATE TABLE papel
(
    id bigserial NOT NULL,
    tipo_papel varchar(50),
    CONSTRAINT papel_pkey PRIMARY KEY (id)
);



-- esta será a table INTERMEDIÁRIA entre as tables "usuario" e "papel" 
CREATE TABLE usuario_papel
(
    usuario_id bigint NOT NULL,
    papel_id bigint NOT NULL,	  
    CONSTRAINT fk_usuario_papel_papel_id FOREIGN KEY (papel_id)
        REFERENCES papel (id) ON DELETE CASCADE,
    CONSTRAINT fk_usuario_papel_usuario_id FOREIGN KEY (usuario_id)
        REFERENCES usuario (id) ON DELETE CASCADE 
);


-- primeiro adicona as tables: "papel" e "usuario_papel"
-- depois seleciona as linhas de baixo  e executa
INSERT INTO papel (tipo_papel) VALUES ('ADMIN');
INSERT INTO papel (tipo_papel) VALUES ('USER');
INSERT INTO papel (tipo_papel) VALUES ('VENDOR');
