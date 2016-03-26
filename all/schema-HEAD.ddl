
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
        valide bit not null,
        aventure_id bigint,
        biographie_id bigint not null,
        mj_id bigint not null,
        primary key (id)
    );

    create table Joue (
        id bigint not null,
        aventure_id bigint not null,
        joueur_id bigint not null,
        personnage_id bigint not null,
        primary key (id)
    );

    create table Joueur (
        id bigint not null,
        pseudo varchar(255) not null,
        pwd varchar(255) not null,
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
        portrait varchar(255),
        profession varchar(255) not null,
        valide bit not null,
        biographie_id bigint,
        joueur_id bigint not null,
        mj_id bigint,
        transfert_id bigint,
        univers_id bigint not null,
        validateur_id bigint,
        primary key (id)
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
