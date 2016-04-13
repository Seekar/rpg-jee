


-- Pour que sqlplus gère les accents correctement, il faut le lancer ainsi :
-- NLS_LANG=FRENCH_FRANCE.UTF8 sqlplus login@ensioracle1

-- Drop everything
drop table Paragraphe;
drop table Episode;
drop table Joue;
drop table Participe;
drop table Aventure cascade constraints;
drop table Personnage cascade constraints;
drop table Biographie;
drop table Joueur;
drop table Univers;

drop sequence av_seq;
drop sequence bio_seq;
drop sequence joueur_seq;
drop sequence univers_seq;
drop sequence pers_seq;
drop sequence para_seq;
drop sequence epi_seq;


-- Create fresh sequences
create sequence av_seq;
create sequence bio_seq;
create sequence joueur_seq;
create sequence univers_seq;
create sequence pers_seq;
create sequence para_seq;
create sequence epi_seq;


-- Create database
create table Aventure (
    id int default av_seq.nextval not null,
    aDate varchar(70) not null,
    events varchar(4000),
    finie int default 0 not null,
    lieu varchar(70) not null,
    situation varchar(4000) not null,
    titre varchar(70) not null,
    mj_id int not null,
    univers_id int not null,
    primary key (id)
);

create table Biographie (
    id int default bio_seq.nextval not null,
    texte varchar(4000) not null,
    primary key (id)
);

create table Episode (
    id int default epi_seq.nextval not null,
    eDate integer not null,
    valide int default 0 not null,
    aventure_id int,
    biographie_id int not null,
    mj_id int,
    primary key (id)
);

create table Participe (
    aventure_id int not null,
    personnage_id int not null,
    primary key (aventure_id, personnage_id)
);

create table Joueur (
    id int default joueur_seq.nextval not null,
    pseudo varchar(30) unique not null,
    pwd char(32) not null,
    primary key (id)
);

create table Paragraphe (
    id int default para_seq.nextval not null,
    secret int default 1 not null,
    texte varchar(4000) not null,
    episode_id int not null,
    primary key (id)
);

create table Personnage (
    id int default pers_seq.nextval not null,
    naissance varchar(70) not null,
    nom varchar(70) not null,
    portrait varchar(4000),
    profession varchar(70) not null,
    valide int default 0 not null,
    biographie_id int,
    joueur_id int not null,
    mj_id int,
    transfert_id int,
    univers_id int not null,
    validateur_id int,
    primary key (id)
);

create table Univers (
    id int default univers_seq.nextval not null,
    nom varchar(70) not null,
    primary key (id)
);

alter table Aventure
    add constraint FK_Aventure_Joueur
    foreign key (mj_id)
    references Joueur;

alter table Aventure 
    add constraint FK_Aventure_Univers 
    foreign key (univers_id) 
    references Univers;

alter table Episode 
    add constraint FK_Episode_Joueur 
    foreign key (mj_id) 
    references Joueur;

alter table Episode 
    add constraint FK_Episode_Biographie 
    foreign key (biographie_id) 
    references Biographie;

alter table Episode 
    add constraint FK_Episode_Aventure 
    foreign key (aventure_id) 
    references Aventure;

alter table Participe 
    add constraint FK_Participe_Aventure 
    foreign key (aventure_id) 
    references Aventure
    on delete cascade;

alter table Participe
    add constraint FK_Participe_Personnage 
    foreign key (personnage_id) 
    references Personnage
    on delete cascade;

alter table Paragraphe
    add constraint FK_Paragraphe_Episode 
    foreign key (episode_id) 
    references Episode
    on delete cascade;

alter table Personnage 
    add constraint FK_Personnage_Validation
    foreign key (validateur_id) 
    references Joueur;

alter table Personnage 
    add constraint FK_Personnage_Transfert
    foreign key (transfert_id) 
    references Joueur;

alter table Personnage 
    add constraint FK_Personnage_Meneur 
    foreign key (mj_id) 
    references Joueur;

alter table Personnage 
    add constraint FK_Personnage_Univers 
    foreign key (univers_id) 
    references Univers;

alter table Personnage 
    add constraint FK_Personnage_Biographie 
    foreign key (biographie_id) 
    references Biographie 
    on delete cascade;

alter table Personnage 
    add constraint FK_Personnage_Joueur 
    foreign key (joueur_id) 
    references Joueur;




insert into Univers (nom) values ('Final Fantasy');
insert into Univers (nom) values ('PokeLand');
insert into Univers (nom) values ('Ghost Rider');
insert into Univers (nom) values ('X-Men');


-- hash générable avec la commande suivante :
-- echo -n password | md5sum

-- pass : max71Lord (hash md5)
insert into Joueur (pseudo, pwd)
    values ('Max', '0e87b8eadc71d22a1a41a53ae30774d4');

-- pass : Vasto42 (hash md5)
insert into Joueur (pseudo, pwd)
    values ('Clara', '436bc2b3b94cdc207e8a08763aa07e6f');

-- pass : admin17Lord (hash md5)
insert into Joueur (pseudo, pwd)
    values ('admin', 'd61ff73ffc50cec9c63180fdb7af0b7e');

-- pass : james007tb (hash md5)
insert into Joueur (pseudo, pwd)
    values ('James', 'ea262e6e612acd24c49c050f66f04607');



commit;


