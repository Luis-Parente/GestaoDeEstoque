CREATE TABLE tb_produto
(
    uuid               UUID PRIMARY KEY,
    nome               VARCHAR(255)   NOT NULL,
    categoria          VARCHAR(50),
    descricao          TEXT,
    preco              NUMERIC(10, 2) NOT NULL,
    preco_com_desconto NUMERIC(10, 2),
    quantidade         INTEGER,
    desconto           NUMERIC(10, 2)
);