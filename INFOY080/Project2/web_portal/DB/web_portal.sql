-- create DB with HyperSQL
-- :web_portal/DB$ java -jar ../WebContent/WEB-INF/lib/hsqldb.jar --rcFile sqltool.rc web_portal web_portal.sql

CREATE TABLE ExpertAdministrator (Username		VARCHAR(16),
								  Password		VARCHAR(16),
								  FirstName		VARCHAR(64),
								  LastName		VARCHAR(64),
								  EmailAddress	VARCHAR(64),
								  LastLogin		DATETIME,
								  PRIMARY KEY	(Username));

CREATE TABLE Operator (Username		VARCHAR(16),
					   Password		VARCHAR(16),
					   FirstName	VARCHAR(64),
					   LastName		VARCHAR(64),
					   EmailAddress	VARCHAR(64),
					   LastLogin	DATETIME,
					   PRIMARY KEY	(Username));	

CREATE TABLE ExpensiveSubscription (Username		VARCHAR(16),
									Password		VARCHAR(16),
									FirstName		VARCHAR(64),
									LastName		VARCHAR(64),
									EmailAddress	VARCHAR(64),
									LastLogin		DATETIME,
									PRIMARY KEY		(Username));
									
CREATE TABLE FreeSubscription (Username		VARCHAR(16),
							   Password		VARCHAR(16),
							   FirstName		VARCHAR(64),
							   LastName		VARCHAR(64),
							   EmailAddress	VARCHAR(64),
							   LastLogin		DATETIME,
							   PRIMARY KEY		(Username));
							   
CREATE TABLE CheapSubscription (Username		VARCHAR(16),
							    Password		VARCHAR(16),
							    FirstName		VARCHAR(64),
							    LastName		VARCHAR(64),
							    EmailAddress	VARCHAR(64),
							    LastLogin		DATETIME,
							    PRIMARY KEY		(Username));		

CREATE TABLE ExternalAdministrator (Username		VARCHAR(16),
							    Password		VARCHAR(16),
							    FirstName		VARCHAR(64),
							    LastName		VARCHAR(64),
							    EmailAddress	VARCHAR(64),
							    LastLogin		DATETIME,
							    PRIMARY KEY		(Username));
							    
CREATE TABLE RegularAdministrator (Username		VARCHAR(16),
							    Password		VARCHAR(16),
							    FirstName		VARCHAR(64),
							    LastName		VARCHAR(64),
							    EmailAddress	VARCHAR(64),
							    LastLogin		DATETIME,
							    PRIMARY KEY		(Username));

CREATE TABLE Book (DateAdded DATETIME,
				   Author VARCHAR(64),
				   ISBN INTEGER,
				   Pages INTEGER,
				   PublicationDate DATETIME,
				   Publisher VARCHAR(64),
				   Review VARCHAR(10000),
				   Summary VARCHAR(10000),
				   Title VARCHAR(250),
				   PRIMARY KEY (Title));

SET SCHEMA PUBLIC;    

INSERT INTO ExpertAdministrator		( Username   , Password , FirstName  , LastName           , EmailAddress    , LastLogin             )
			VALUES					( 'niels'    , '1234'   , 'Niels'    , 'Joncheere'        , 'niels@soft'    , '2008-02-01 14:32:00' );
INSERT INTO Operator				( Username   , Password , FirstName  , LastName           , EmailAddress    , LastLogin             )
			VALUES					( 'ragnhild' , '5678'   , 'Ragnhild' , 'Van Der Straeten' , 'ragnhild@soft' , '2008-02-01 14:32:00' );
INSERT INTO ExpensiveSubscription	( Username   , Password , FirstName  , LastName           , EmailAddress    , LastLogin             )
			VALUES					( 'bruno'    , '9012'   , 'Bruno'    , 'De Fraine'        , 'bruno@soft'    , '2008-02-01 14:32:00' );
INSERT INTO FreeSubscription		( Username   , Password , FirstName  , LastName           , EmailAddress    , LastLogin             )
			VALUES					( 'test'     , 'test'   , 'test'    , 'test'        	  , 'test@soft'     , '2008-02-01 14:32:00' );

INSERT INTO Book	(DateAdded,				Author,		ISBN,	Pages, 	PublicationDate, 		Publisher, Review,	Summary, 	Title)
			VALUES	('2008-02-01 14:32:00',	'Mrs Test',	0,		0, 		'2008-02-01 14:32:00',	'test',		'test', 'test', 	'test' );

			
COMMIT;
