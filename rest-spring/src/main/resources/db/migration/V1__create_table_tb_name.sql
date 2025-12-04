CREATE TABLE tb_name (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_day DATE,
    address VARCHAR(100) NOT NULL,
    gender CHAR(1) NOT NULL
);