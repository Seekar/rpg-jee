


-- Drop everything
drop table Paragraphe;
drop table Episode;
drop table Joue;
drop table Aventure cascade constraints;
drop table Personnage cascade constraints;
drop table Biographie;
drop table Joueur;
drop table Univers;

drop sequence av_seq;
drop sequence bio_seq;


-- Create fresh sequences
create sequence av_seq;
create sequence bio_seq;
-- implem séquences à finir


-- Create database
create table Aventure (
    id int default av_seq.nextval not null,
    aDate varchar(255) not null,
    events varchar(255) not null,
    finie int not null,
    lieu varchar(255) not null,
    situation varchar(255) not null,
    titre varchar(255) not null,
    mj_id int not null,
    univers_id int not null,
    primary key (id)
);

create table Biographie (
    id int default bio_seq.nextval not null,
    texte varchar(255) not null,
    primary key (id)
);

create table Episode (
    id int not null,
    eDate integer not null,
    valide int not null,
    aventure_id int,
    biographie_id int not null,
    mj_id int not null,
    primary key (id)
);

create table Joue (
    id int not null,
    aventure_id int not null,
    joueur_id int not null,
    personnage_id int not null,
    primary key (id)
);

create table Joueur (
    id int not null,
    pseudo varchar(255) not null,
    pwd varchar(255) not null,
    primary key (id)
);

create table Paragraphe (
    id int not null,
    secret int not null,
    texte varchar(255) not null,
    episode_id int not null,
    primary key (id)
);

create table Personnage (
    id int not null,
    naissance varchar(255) not null,
    nom varchar(255) not null,
    portrait varchar(255),
    profession varchar(255) not null,
    valide int not null,
    biographie_id int,
    joueur_id int not null,
    mj_id int,
    transfert_id int,
    univers_id int not null,
    validateur_id int,
    primary key (id)
);

create table Univers (
    id int not null,
    nom varchar(255) not null,
    primary key (id)
);

alter table Aventure 
    add constraint FK74E9C5328ACB2B0F 
    foreign key (mj_id) 
    references Joueur;

alter table Aventure 
    add constraint FK74E9C5325D6E559A 
    foreign key (univers_id) 
    references Univers;

alter table Episode 
    add constraint FK72A55DB8ACB2B0F 
    foreign key (mj_id) 
    references Joueur;

alter table Episode 
    add constraint FK72A55DB74088A9A 
    foreign key (biographie_id) 
    references Biographie;

alter table Episode 
    add constraint FK72A55DB619C649A 
    foreign key (aventure_id) 
    references Aventure;

alter table Joue 
    add constraint FK2352B5619C649A 
    foreign key (aventure_id) 
    references Aventure;

alter table Joue 
    add constraint FK2352B55F4B841A 
    foreign key (personnage_id) 
    references Personnage;

alter table Joue 
    add constraint FK2352B5D3CA809A 
    foreign key (joueur_id) 
    references Joueur;

alter table Paragraphe 
    add constraint FK889B40D767F703BA 
    foreign key (episode_id) 
    references Episode;

alter table Personnage 
    add constraint FK9F513EC65448AEB9 
    foreign key (validateur_id) 
    references Joueur;

alter table Personnage 
    add constraint FK9F513EC64E47F43 
    foreign key (transfert_id) 
    references Joueur;

alter table Personnage 
    add constraint FK9F513EC68ACB2B0F 
    foreign key (mj_id) 
    references Joueur;

alter table Personnage 
    add constraint FK9F513EC65D6E559A 
    foreign key (univers_id) 
    references Univers;

alter table Personnage 
    add constraint FK9F513EC674088A9A 
    foreign key (biographie_id) 
    references Biographie 
    on delete cascade;

alter table Personnage 
    add constraint FK9F513EC6D3CA809A 
    foreign key (joueur_id) 
    references Joueur;


