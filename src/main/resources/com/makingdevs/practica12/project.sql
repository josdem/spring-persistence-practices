--liquibase formatted sql

--changeset makingdevs:2
CREATE TABLE IF NOT EXISTS PROJECT(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    CODE_NAME VARCHAR(50) NOT NULL,
    DATE_CREATED TIMESTAMP NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    LAST_UPDATED TIMESTAMP NOT NULL,
    NAME VARCHAR(100) NOT NULL
);
--rollback drop table project;

--changeset makingdevs:3
INSERT INTO PROJECT(ID, CODE_NAME, DATE_CREATED, DESCRIPTION, LAST_UPDATED, NAME) VALUES
(1, 'FACTURANOT', TIMESTAMP '2014-02-12 13:31:52.366', 'Desarrollo de la app de Facturacion', TIMESTAMP '2014-02-12 13:31:52.366', 'Modulo de Facturacion'),
(2, 'VIMCHALLENGES', TIMESTAMP '2014-02-12 13:32:27.509', 'Aplicacion para desafiar a tus amigos con VIM', TIMESTAMP '2014-02-12 13:32:27.509', 'The Vim Challenges'),
(3, 'SPRING-WEB', TIMESTAMP '2014-02-12 13:33:17.968', 'Todos los temas de desarrollo web con Spring', TIMESTAMP '2014-02-12 13:33:17.968', 'Desarrollo Web con Spring'),
(4, 'AGILE-TASKBOARD', TIMESTAMP '2014-02-12 13:37:09.803', 'Un tablero de control de proyectos con historias de usuario y tareas', TIMESTAMP '2014-02-12 13:37:09.803', 'My uber taskboard');

--changeset makingdevs:4 dbms:mysql
CREATE TABLE IF NOT EXISTS PROJECT_USER(
    PROJECT_PARTICIPANTS_ID BIGINT,
    USER_ID BIGINT
);