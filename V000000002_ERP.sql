-- Atualização para a Versão 2 do banco de dados ERP
-- 2010-08-25 22h37

 -- /INICIO/ Correção de tipo de dado na tabela cliente
	ALTER TABLE cliente DROP COLUMN modified_at;
	ALTER TABLE cliente ADD COLUMN modified_at timestamp;
 -- /FIM/
	
	
 -- /INICIO/ STRING_NOT_EMPTY(texto VARCHAR) --> Retorna .T. se a string NÃO estiver vazia!!!
	CREATE OR REPLACE FUNCTION STRING_NOT_EMPTY(texto VARCHAR)
		RETURNS BOOLEAN AS
	$$
	BEGIN
		RETURN lpad(btrim(texto), 50) <> lpad(' ', 50) AND substring(texto, 1, 1) <> ' ';
	END
	$$
	LANGUAGE plpgsql;
 -- /FIM/
 	
	
 -- /INICIO/ Criação de Domínios
	CREATE DOMAIN dm_codigo
        AS character(6)
        NOT NULL;
 -- /FIM/

 -- /INICIO/ 
	CREATE DOMAIN dm_codigo3
		AS character(3)
		NOT NULL;
 -- /FIM/
	
 -- /INICIO/ 
	CREATE DOMAIN dm_date
		AS DATE
		NOT NULL
		DEFAULT CURRENT_DATE;
 -- /FIM/
		
-- /INICIO/ 
	CREATE DOMAIN dm_flag_0
		AS integer
		NOT NULL
		DEFAULT 0;
 -- /FIM/
		
-- /INICIO/ 
	CREATE DOMAIN dm_flag_1
		AS integer
		NOT NULL
		DEFAULT 1;
 -- /FIM/
		
-- /INICIO/ 
	CREATE DOMAIN dm_time
		AS TIME
		NOT NULL
		DEFAULT CURRENT_TIME;
 -- /FIM/
		
-- /INICIO/ 
	CREATE DOMAIN dm_id
		AS integer
		NOT NULL;
 -- /FIM/
		
-- /INICIO/ 
	CREATE DOMAIN dm_nome
		AS character varying(60)
		NOT NULL;
 -- /FIM/

-- /INICIO/ 
	CREATE DOMAIN dm_timestamp
		AS TIMESTAMP
		NOT NULL
		DEFAULT CURRENT_TIMESTAMP;
 -- /FIM/
		
-- /INICIO/ 
	CREATE DOMAIN dm_user_db
		AS VARCHAR(60)
		NOT NULL
		DEFAULT user;
 -- /FIM/
		

 -- /INICIO/ 
 -- Seqüência para versão do banco de dados ERP
	CREATE SEQUENCE sq_versao_db
		INCREMENT 1
		MINVALUE 1
		MAXVALUE 9223372036854775807
		START 1
		CACHE 1;
 -- /FIM/

 -- /INICIO/ 
 -- Seqüência para versão do banco de dados ERP
	CREATE SEQUENCE sq_usuario
		INCREMENT 1000
		MINVALUE 1001
		MAXVALUE 9223372036854775807
		START 1001
		CACHE 1;
 -- /FIM/

 -- /INICIO/ 
 -- Seqüência para versão do banco de dados ERP
	CREATE SEQUENCE sq_usuario_sistema_logado
		INCREMENT 1000
		MINVALUE 1001
		MAXVALUE 9223372036854775807
		START 1001
		CACHE 1;
 -- /FIM/

 -- /INICIO/ 
 -- Seqüência para a tabela empresa
	CREATE SEQUENCE sq_empresa
		INCREMENT 1000
		MINVALUE 1001
		MAXVALUE 9223372036854775807
		START 1001
		CACHE 1;
 -- /FIM/

 -- /INICIO/ 
 -- Cria a tabela de versao_db para controle de versão do banco de dados
	CREATE TABLE versao_db
	(
		id_versao_db dm_id DEFAULT NEXTVAL('sq_versao_db'),
		versao INTEGER NOT NULL,
		created_at dm_timestamp,
		created_by dm_nome CHECK (STRING_NOT_EMPTY(created_by)),
		created_on dm_nome, 
		created_user_db dm_user_db,
		modified_at TIMESTAMP,
		modified_by varchar(60),
		modified_on varchar(60), 
		modified_user_db varchar(60),
		CONSTRAINT id_versao_db_pk PRIMARY KEY (id_versao_db)
	) ;
 -- /FIM/
		

 -- /INICIO/ 
 -- Cria a tabela de usuários
	CREATE TABLE usuario
	(
		id_usuario dm_id DEFAULT NEXTVAL('sq_usuario'),
		nome dm_nome,
		senha VARCHAR(15),
		usuario_bd dm_nome,
		fg_acesso dm_flag_0,
		fg_incluir dm_flag_1,
		fg_alterar dm_flag_1,
		fg_excluir dm_flag_1,
		fg_consultar dm_flag_1,
		fg_processar dm_flag_1,
		fg_relatorio dm_flag_1,
		fg_ativo dm_flag_1,
		created_at dm_timestamp,
		created_by dm_nome CHECK (STRING_NOT_EMPTY(created_by)),
		created_on dm_nome, 
		created_user_db dm_user_db,
		modified_at TIMESTAMP,
		modified_by varchar(60),
		modified_on varchar(60), 
		modified_user_db varchar(60),
		CONSTRAINT id_usuario_pk PRIMARY KEY (id_usuario)
	) ;
 -- /FIM/
		
 -- /INICIO/ 
	-- Cria tabela usuario_sistema_logado para usuários logados no sistema
	-- ao abrir um programa e um usuário fazer login no mesmo e se conectar ao BD, o sistema gravará o id_usuario e a sessão a que está conectado
	CREATE TABLE usuario_sistema_logado
	( 
		id_usuario_sistema_logado dm_id DEFAULT NEXTVAL('sq_usuario_sistema_logado'),
		id_usuario dm_id,
		sessao VARCHAR(250) NOT NULL,
		inicio dm_timestamp, 
		fim  TIMESTAMP,
		fg_ativo dm_flag_1,
		created_at dm_timestamp,
		created_by dm_nome CHECK (STRING_NOT_EMPTY(created_by)),
		created_on dm_nome, 
		created_user_db dm_user_db,
		modified_at TIMESTAMP,
		modified_by varchar(60),
		modified_on varchar(60), 
		modified_user_db varchar(60),
		CONSTRAINT id_usuario_sistema_logado_pk PRIMARY KEY (id_usuario_sistema_logado),
		CONSTRAINT id_usuario_fk FOREIGN KEY (id_usuario)
			REFERENCES usuario (id_usuario) MATCH SIMPLE
			ON UPDATE RESTRICT ON DELETE RESTRICT,
		CONSTRAINT sessao_ck CHECK (STRING_NOT_EMPTY(sessao))
	) ;
 -- /FIM/
	
	  
 -- /INICIO/ 
	-- Adição de novos campos para auditoria na tabela cor
	ALTER TABLE cor ADD COLUMN created_at dm_timestamp;
	ALTER TABLE cor ADD COLUMN created_by dm_nome DEFAULT 'INDEFINIDO'  CHECK (STRING_NOT_EMPTY(created_by));
	ALTER TABLE cor ADD COLUMN created_on dm_nome DEFAULT 'INDEFINIDO'  CHECK (STRING_NOT_EMPTY(created_on));
	ALTER TABLE cor ADD COLUMN created_user_db dm_user_db;
	ALTER TABLE cor ADD COLUMN modified_at TIMESTAMP;
	ALTER TABLE cor ADD COLUMN modified_by varchar(60);
	ALTER TABLE cor ADD COLUMN modified_on varchar(60); 
	ALTER TABLE cor ADD COLUMN modified_user_db varchar(60);
 -- /FIM/


 -- /INICIO/ 
	-- Adição de novos campos para auditoria na tabela cliente
	ALTER TABLE cliente DROP COLUMN created_at;
	ALTER TABLE cliente ADD COLUMN created_at dm_timestamp;
	ALTER TABLE cliente ADD COLUMN created_by dm_nome DEFAULT 'INDEFINIDO'  CHECK (STRING_NOT_EMPTY(created_by));
	ALTER TABLE cliente ADD COLUMN created_on dm_nome DEFAULT 'INDEFINIDO'  CHECK (STRING_NOT_EMPTY(created_on));
	ALTER TABLE cliente ADD COLUMN created_user_db dm_user_db;
	ALTER TABLE cliente ADD COLUMN modified_by varchar(60);
	ALTER TABLE cliente ADD COLUMN modified_on varchar(60); 
	ALTER TABLE cliente ADD COLUMN modified_user_db varchar(60);
 -- /FIM/


 -- /INICIO/ 
	-- Adição de novos campos para auditoria na tabela rota
    ALTER TABLE rota DROP COLUMN created_at;
	ALTER TABLE rota ADD COLUMN created_at dm_timestamp;
	ALTER TABLE rota ADD COLUMN created_by dm_nome DEFAULT 'INDEFINIDO'  CHECK (STRING_NOT_EMPTY(created_by));
	ALTER TABLE rota ADD COLUMN created_on dm_nome DEFAULT 'INDEFINIDO'  CHECK (STRING_NOT_EMPTY(created_on));
	ALTER TABLE rota ADD COLUMN created_user_db dm_user_db;
    ALTER TABLE rota DROP COLUMN modified_at;
	ALTER TABLE rota ADD COLUMN modified_at TIMESTAMP;
	ALTER TABLE rota ADD COLUMN modified_by varchar(60);
	ALTER TABLE rota ADD COLUMN modified_on varchar(60); 
	ALTER TABLE rota ADD COLUMN modified_user_db varchar(60);
 -- /FIM/
	
 -- /INICIO/ 
	-- Adição de novos campos para auditoria na tabela telefone
    ALTER TABLE telefone DROP COLUMN created_at;
	ALTER TABLE telefone ADD COLUMN created_at dm_timestamp;
	ALTER TABLE telefone ADD COLUMN created_by dm_nome DEFAULT 'INDEFINIDO'  CHECK (STRING_NOT_EMPTY(created_by));
	ALTER TABLE telefone ADD COLUMN created_on dm_nome DEFAULT 'INDEFINIDO'  CHECK (STRING_NOT_EMPTY(created_on));
	ALTER TABLE telefone ADD COLUMN created_user_db dm_user_db;
    ALTER TABLE telefone DROP COLUMN modified_at;
	ALTER TABLE telefone ADD COLUMN modified_at TIMESTAMP;
	ALTER TABLE telefone ADD COLUMN modified_by varchar(60);
	ALTER TABLE telefone ADD COLUMN modified_on varchar(60); 
	ALTER TABLE telefone ADD COLUMN modified_user_db varchar(60);
 -- /FIM/
	
 -- /INICIO/ 
	-- Cria a tabela empresa
	CREATE TABLE empresa
	(
		id_empresa dm_id DEFAULT nextval('sq_empresa'),
		cod dm_codigo3 CHECK(STRING_NOT_EMPTY(cod)),
		nome dm_nome CHECK(STRING_NOT_EMPTY(nome)),
		fantasia character varying(60),
		created_at dm_timestamp,
		created_by dm_nome CHECK (STRING_NOT_EMPTY(created_by)),
		created_on dm_nome, 
		created_user_db dm_user_db,
		modified_at TIMESTAMP,
		modified_by varchar(60),
		modified_on varchar(60), 
		modified_user_db varchar(60),
		fg_ativo dm_flag_1,
		CONSTRAINT id_empresa_pk PRIMARY KEY (id_empresa)
	);
 -- /FIM/
	
 -- /INICIO/ 
	-- Inclusão de 1 registro na tabela empresa para dar integridade ao banco de dados
	INSERT INTO empresa (id_empresa,cod,nome,created_by,created_on) VALUES (1001, '001', 'TEMPLATE DE EMPRESA','INDEFINIDO','INDEFINIDO');
 -- /FIM/

 -- /INICIO/ 
     -- Adiciona campo descricao na tabela rota
	ALTER TABLE rota ADD COLUMN descricao dm_nome;
 -- /FIM/
	
 -- /INICIO/ 
    -- Adiciona chave extrangeira na tabela venda	
	ALTER TABLE venda
		ADD CONSTRAINT id_empresa_fk FOREIGN KEY (id_empresa)
			REFERENCES empresa (id_empresa) MATCH SIMPLE
			ON UPDATE RESTRICT ON DELETE RESTRICT;
 -- /FIM/

 -- /INICIO/ 
	-- Inclui a rota "NNN" como sendo a rota "NÃO DEFINIDA"
	START TRANSACTION;
	INSERT INTO rota (cod, descricao,created_by,created_on) VALUES ('NNN', 'NÃO DEFINIDA','INDEFINIDO','INDEFINIDO');
	COMMIT;
 -- /FIM/
	
 -- /INICIO/ 
	-- Atualiza as rotas inválidas da tabela cliente
	CREATE OR REPLACE FUNCTION TMP_CR() RETURNS INTEGER AS
	$$
		DECLARE nId_Rota rota.id_rota%TYPE;
		BEGIN
			SELECT INTO nId_Rota rota.id_rota FROM rota  WHERE cod='NNN';
			UPDATE cliente SET id_rota=nId_Rota  WHERE id_rota ISNULL OR id_rota=0;
			RETURN 1;
		END;
	$$
	LANGUAGE 'plpgsql';
	SELECT TMP_CR();
	DROP FUNCTION TMP_CR();
 -- /FIM/

	
 -- /INICIO/ 
	-- Adiciona chave extrangeira na tabela cliente
	ALTER TABLE cliente
		ADD CONSTRAINT id_rota_fk FOREIGN KEY (id_rota)
			REFERENCES rota (id_rota) MATCH SIMPLE
			ON UPDATE RESTRICT ON DELETE RESTRICT;
 -- /FIM/
			
 -- /INICIO/ 
	-- Atualiza a versão do banco de dados
    INSERT INTO versao_db 
	( versao,created_by,created_on )
	VALUES ( 1, 
			'PROCESSO DE ATUALIZAÇÃO DAS ESTRUTURAS DO BANCO DE DADOS',
			'PROCESSO DE ATUALIZAÇÃO DAS ESTRUTURAS DO BANCO DE DADOS' );
 -- /FIM/
	