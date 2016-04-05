


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

alter table Participe 
    add constraint FK2352B5619C649A 
    foreign key (aventure_id) 
    references Aventure;

alter table Participe
    add constraint FK2352B55F4B841A 
    foreign key (personnage_id) 
    references Personnage;

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



-- problèmes avec les caractères spéciaux sur sqlplus
insert into Univers (nom) values ('La Guerre des etoiles');
insert into Univers (nom) values ('Les Caraibes au temps des pirates');

insert into BIOGRAPHIE (ID, TEXTE) 
    values (DEFAULT,
'Pikachu est un petit Pokemon potele qui ressemble a un rongeur. Il est couvert de fourrure jaune. Ses oreilles sont pointues et leurs bouts sont noirs. Il a une petite bouche, des yeux marron et deux cercles rouges sur les joues. Il y a des poches sous ses joues qui generent de l''electricite. Ses bras sont courts, avec cinq doigts chacun, et ses pieds possedent trois orteils. Il a deux stries marron sur le dos, et sa queue est en forme d''eclair avec un peu de fourrure marron a la base.. Il est classe comme un quadrupede, mais il est connu pour se tenir et meme marcher sur ses pattes arrieres.' || chr(13) || '' || chr(13) || 'Le dessin anime montre que Pikachu voyage parfois en groupes. Il leve sa queue pour verifier les environs, qui lorsqu''elle trouve quelque chose, se charge l''electricite. Comme il vit dans les regions boisees, Pikachu est connu pour griller les Baies qu''il mange avec de l''electricite pour les rendre plus tendres. On voit dans Electric Tale of Pikachu que Pikachu est capable de manger voire de detruire des cabines telephoniques, des cables ou autres installations electriques.' || chr(13) || '' || chr(13) || 'Pikachu est capable de lacher des decharges d''electricite a la puissance variante. Pikachu est connu pour generer l''energie dans les glandes situees sous ses joues, et doit la faire sortir pour eviter des complications. Il est aussi capable de relacher de l''energie de sa queue, de la recharger en la plantant dans la terre, ou encore meme d''aider a recharger un camarade avec des coups d''electricite. Pikachu peut aussi s''electriser lui-meme pour utiliser son attaque signature, Electacle. Quand il est menace, il relache l''energie de ses joues pour creer de l''electricite, et un groupe de Pikachu peut creer de veritables orages. Il est le plus souvent trouve dans les forets, et le signe qu''un Pikachu est passe par la est une tache d''herbe brulee.'
);

insert into PERSONNAGE (ID, NAISSANCE, NOM, PORTRAIT, PROFESSION, VALIDE, BIOGRAPHIE_ID, JOUEUR_ID, MJ_ID, TRANSFERT_ID, UNIVERS_ID, VALIDATEUR_ID) 
    values (DEFAULT, '27 février 1996', 'Pikachu',
    'http://gamegeex.blogomancer.com/files/gamegeex/images/headerimages/1443_new-pikachu-game-is-in-the-works.png',
    'Pokemon', DEFAULT, bio_seq.currval, joueur_seq.currval, NULL, NULL, univers_seq.currval, NULL);

insert into episode values (DEFAULT, 2, 1, null, bio_seq.currval, NULL);

insert into PARAGRAPHE (ID, SECRET, TEXTE, EPISODE_ID) 
    values (DEFAULT, 0, 'Paragraphe d''episode', epi_seq.currval);

INSERT INTO EPISODE (ID, EDATE, VALIDE, AVENTURE_ID, BIOGRAPHIE_ID, MJ_ID) 
    VALUES (DEFAULT, 2, DEFAULT, NULL, bio_seq.currval, NULL);

insert into aventure values (DEFAULT, 'date', NULL, DEFAULT, 'paris', 'situation', 'Aventure', joueur_seq.currval, univers_seq.currval);
insert into aventure values (DEFAULT, 'date', NULL, DEFAULT, 'paris', 'situation', 'Aventure', joueur_seq.currval, univers_seq.currval);
insert into aventure values (DEFAULT, 'date', NULL, DEFAULT, 'paris', 'situation', 'Aventure', joueur_seq.currval, univers_seq.currval);


commit;


