INSERT INTO USER(name, email, password) VALUES('Aluno', 'aluno@email.com', '$2a$10$bkPyweNpCSdMAIYt8KesjulOmtD86CYp9ysrl9yddJq86lUs9cs1i');

INSERT INTO COURSE(name, category) VALUES('Spring Boot', 'Programação');
INSERT INTO COURSE(name, category) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id) VALUES('Dúvida', 'Erro ao criar projeto', '2020-05-05 18:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id) VALUES('Dúvida 2', 'Projeto não compila', '2020-05-05 19:00:00', 'NOT_ANSWERED', 1, 1);
INSERT INTO TOPIC(title, message, creation_date, status, author_id, course_id) VALUES('Dúvida 3', 'Tag HTML', '2020-05-05 20:00:00', 'NOT_ANSWERED', 1, 2);