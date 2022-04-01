CREATE TABLE tb_curso (
	id BIGINT generated by default as identity,
	cod VARCHAR(30) NOT NULL,
	nome VARCHAR(30) NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE tb_materia (
    id BIGINT generated by default as identity,
    cod VARCHAR(6) NOT NULL,
    freq INT4,
    hrs INT4,
    nome VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE tb_curso_materias (
    curso_id BIGINT NOT NULL,
    materia_id BIGINT NOT NULL,
    
    PRIMARY KEY (curso_id, materia_id)
);

-- CONSTRAINTS

ALTER TABLE tb_curso_materias
ADD CONSTRAINT fk_materias
FOREIGN KEY (materia_id)
REFERENCES tb_materia;

ALTER TABLE tb_curso_materias
ADD CONSTRAINT fk_curso
FOREIGN KEY (curso_id)
REFERENCES tb_curso;