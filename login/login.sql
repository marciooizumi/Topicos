select * from usuario;
select * from mensagem;
select * from login;
select * from log;

CREATE OR REPLACE FUNCTION funcLogSalvar() RETURNS TRIGGER AS
$$	
	DECLARE ID INTEGER; NOME VARCHAR(20);
	BEGIN
		

		SELECT INTO ID idusuario_login FROM login WHERE id_login = (SELECT MAX(id_login) FROM login); 

		SELECT INTO NOME nomeusuario_login FROM login WHERE id_login = (SELECT MAX(id_login) FROM login);
		
		INSERT INTO log values (nextval('seq_log'), CURRENT_TIMESTAMP, NEW.idmensagem, ID, 1, 'localhost', 'Mensagem', NOME, 'INCLUSÃO');
		RETURN NEW; 

	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER gatLogSalvar AFTER insert ON mensagem
FOR EACH ROW EXECUTE PROCEDURE funcLogSalvar();


CREATE OR REPLACE FUNCTION funcLogEditar() RETURNS TRIGGER AS
$$	
	DECLARE ID INTEGER; NOME VARCHAR(20);
	BEGIN
		

		SELECT INTO ID idusuario_login FROM login WHERE id_login = (SELECT MAX(id_login) FROM login); 

		SELECT INTO NOME nomeusuario_login FROM login WHERE id_login = (SELECT MAX(id_login) FROM login);
		
		INSERT INTO log values (nextval('seq_log'), CURRENT_TIMESTAMP, NEW.idmensagem, ID, 1, 'localhost', 'Mensagem', NOME, 'EDIÇÃO');
		RETURN NEW; 

	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER gatLogEditar AFTER update ON mensagem
FOR EACH ROW EXECUTE PROCEDURE funcLogEditar();


CREATE OR REPLACE FUNCTION funcLogExcluir() RETURNS TRIGGER AS
$$	
	DECLARE ID INTEGER; NOME VARCHAR(20);
	BEGIN
		

		SELECT INTO ID idusuario_login FROM login WHERE id_login = (SELECT MAX(id_login) FROM login); 

		SELECT INTO NOME nomeusuario_login FROM login WHERE id_login = (SELECT MAX(id_login) FROM login);
		
		INSERT INTO log values (nextval('seq_log'), CURRENT_TIMESTAMP, OLD.idmensagem, ID, 1, 'localhost', 'Mensagem', NOME, 'EXCLUSÃO');
		RETURN NEW; 

	END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER gatLogExcluir AFTER delete ON mensagem
FOR EACH ROW EXECUTE PROCEDURE funcLogExcluir();

delete from log;
delete from login;
delete from mensagem;
delete from usuario;


--SELECT nomeusuario_login FROM login WHERE idusuario_login = (SELECT MAX(idusuario_login) FROM login);;
drop trigger gatLogSalvar on mensagem;
drop trigger gatLogEditar on mensagem;
drop trigger gatLogExcluir on mensagem;
