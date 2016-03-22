
create table Aventure (
    id bigint not null,
    aDate varchar(255) not null,
    events varchar(255) not null,
    finie bit not null,
    lieu varchar(255) not null,
    situation varchar(255) not null,
    titre varchar(255) not null,
    mj_id bigint not null,
    univers_id bigint not null,
    primary key (id)
);

create table Biographie (
    id bigint not null,
    texte varchar(255) not null,
    primary key (id)
);

create table Episode (
    id bigint not null,
    eDate integer not null,
    valid bit not null,
    aventure_id bigint,
    biographie_id bigint not null,
    primary key (id)
);

create table Joueur (
    id bigint not null,
    pseudo varchar(255) not null,
    primary key (id)
);

create table Paragraphe (
    id bigint not null,
    secret bit not null,
    texte varchar(255) not null,
    episode_id bigint not null,
    primary key (id)
);

create table Personnage (
    id bigint not null,
    naissance varchar(255) not null,
    nom varchar(255) not null,
    portrait varchar(255) not null,
    profession varchar(255) not null,
    valid bit not null,
    biographie_id bigint,
    cible_id bigint,
    joueur_id bigint not null,
    univers_id bigint not null,
    primary key (id)
);

create table Personnage_Aventure (
    personnages_id bigint not null,
    aventures_id bigint not null,
    primary key (personnages_id, aventures_id)
);

create table Univers (
    id bigint not null,
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
    references Univers 
    on delete cascade;

alter table Episode 
    add constraint FK72A55DB74088A9A 
    foreign key (biographie_id) 
    references Biographie;

alter table Episode 
    add constraint FK72A55DB619C649A 
    foreign key (aventure_id) 
    references Aventure;

alter table Paragraphe 
    add constraint FK889B40D767F703BA 
    foreign key (episode_id) 
    references Episode;

alter table Personnage 
    add constraint FK9F513EC65D6E559A 
    foreign key (univers_id) 
    references Univers 
    on delete cascade;

alter table Personnage 
    add constraint FK9F513EC610786277 
    foreign key (cible_id) 
    references Joueur;

alter table Personnage 
    add constraint FK9F513EC674088A9A 
    foreign key (biographie_id) 
    references Biographie;

alter table Personnage 
    add constraint FK9F513EC6D3CA809A 
    foreign key (joueur_id) 
    references Joueur;

alter table Personnage_Aventure 
    add constraint FK4050F68BDEA896D3 
    foreign key (personnages_id) 
    references Personnage;

alter table Personnage_Aventure 
    add constraint FK4050F68B1E9405AB 
    foreign key (aventures_id) 
    references Aventure;
