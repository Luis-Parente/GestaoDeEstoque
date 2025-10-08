CREATE TABLE tb_usuario
(
    uuid            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome            VARCHAR(255)        NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    senha           VARCHAR(255)        NOT NULL,
    nivel_de_acesso VARCHAR(50)         NOT NULL
);