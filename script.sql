CREATE TABLE tb_curso (
	id SERIAL,
	cod VARCHAR(30) NOT NULL,
	nome VARCHAR(30) NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE tb_materia (
    id SERIAL,
    cod VARCHAR(6) NOT NULL,
    freq INT4,
    hrs INT4,
    nome VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE tb_curso_materia (
    curso_id INTEGER,
    materia_id INTEGER,

    PRIMARY KEY (curso_id, materia_id)
);

-- CONSTRAINTS

ALTER TABLE tb_curso_materia
ADD CONSTRAINT fk_materia
FOREIGN KEY (materia_id)
REFERENCES tb_materia;

ALTER TABLE tb_curso_materia
ADD CONSTRAINT fk_curso
FOREIGN KEY (curso_id)
REFERENCES tb_curso;