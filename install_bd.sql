


# drop everything
drop table Paragraphe;
drop table Episode;
drop table Participe;
drop table Aventure cascade constraints;
drop table Personnage cascade constraints;
drop table Biographie;
drop table Joueur;
drop table Univers;

drop sequence av_seq;
drop sequence bio_seq;


# Create fresh sequences
create sequence av_seq;
create sequence bio_seq;
# implem séquences à finir


# Create database
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
    valid int not null,
    aventure_id int,
    biographie_id int not null,
    primary key (id)
);

create table Joueur (
    id int not null,
    pseudo varchar(255) not null,
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
    valid int not null,
    biographie_id int,
    joueur_id int not null,
    transfert_id int,
    univers_id int not null,
    primary key (id)
);

create table Participe (
    personnages_id int not null,
    aventures_id int not null,
    primary key (personnages_id, aventures_id)
);

create table Univers (
    id int not null,
    nom varchar(255) not null,
    primary key (id)
);


alter table Aventure 
    add constraint Aventure_Joueur_FK
    foreign key (mj_id) 
    references Joueur;

alter table Aventure 
    add constraint Aventure_Univers_FK
    foreign key (univers_id) 
    references Univers 
    on delete cascade;

alter table Episode 
    add constraint Episode_Biographie_FK
    foreign key (biographie_id) 
    references Biographie;

alter table Episode 
    add constraint Episode_Aventure_FK 
    foreign key (aventure_id) 
    references Aventure;

alter table Paragraphe 
    add constraint Paragraphe_Episode_FK
    foreign key (episode_id) 
    references Episode;

alter table Personnage 
    add constraint Personnage_Transfert_FK
    foreign key (transfert_id) 
    references Joueur;

alter table Personnage 
    add constraint Personnage_Univers_FK
    foreign key (univers_id) 
    references Univers 
    on delete cascade;

alter table Personnage 
    add constraint Personnage_Biographie_FK
    foreign key (biographie_id) 
    references Biographie;

alter table Personnage 
    add constraint Personnage_Joueur_FK
    foreign key (joueur_id) 
    references Joueur;

alter table Participe 
    add constraint Possede_Personnage_FK
    foreign key (personnages_id) 
    references Personnage;

alter table Participe 
    add constraint Possede_Aventure_FK
    foreign key (aventures_id) 
    references Aventure;


