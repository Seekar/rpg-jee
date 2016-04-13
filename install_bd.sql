


-- Pour que sqlplus gère les accents correctement, il faut le lancer ainsi :
-- NLS_LANG=FRENCH_FRANCE.UTF8 sqlplus login@ensioracle1

-- D'abord on paramètre sqlplus
set sqlblanklines on;
set define off;


-- Tout effacer
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


-- Création des séquences
create sequence av_seq;
create sequence bio_seq;
create sequence joueur_seq;
create sequence univers_seq;
create sequence pers_seq;
create sequence para_seq;
create sequence epi_seq;


-- Création de la bdd
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



insert into AVENTURE (ID,ADATE,EVENTS,FINIE,LIEU,SITUATION,TITRE,MJ_ID,UNIVERS_ID) values ('6','1998','Après de nombreuses péripéties, Pikachu l''emporte !','1','Plateau Indigo','La ligue Indigo est la ligue Pokémon officielle de la région de Kanto. Elle est parfois nommée Ligue de Kanto ou plus simplement encore Ligue Pokémon.
Cette ligue est officiellement constituée de huit arènes, d''un conseil des 4 et d''un Maître à sa tête.','Ligue Indigo','1','2');
insert into AVENTURE (ID,ADATE,EVENTS,FINIE,LIEU,SITUATION,TITRE,MJ_ID,UNIVERS_ID) values ('7','2011','Alors que Shaw atteint Cuba, les mutants accompagnés de Moira MacTaggert s''envolent à bord d''un avion furtif vers la ligne de blocus. Une fois que le Hurleur a localisé le sous-marin de Shaw, Erik le ramène à la surface. Les affrontements entre mutants commencent, dépassant les soldats présents, tandis qu''Erik infiltre le bâtiment et retrouve son tortionnaire, après s''être emparé de son casque et malgré les demandes de Xavier, il le tue avec la pièce de monnaie nazie qu''il avait conservée.','1','New York','Avant que les mutants n’aient révélé leur existence au monde, et avant que Charles Xavier et Erik Lehnsherr ne deviennent le Professeur X et Magneto, ils n’étaient encore que deux jeunes hommes découvrant leurs pouvoirs pour la première fois. Avant de devenir les pires ennemis, ils étaient encore amis, et travaillaient avec d’autres mutants pour empêcher la destruction du monde, l’Armageddon. Au cours de cette opération, le conflit naissant entre les deux hommes s’accentua, et la guerre éternelle entre la Confrérie de Magneto et les X-Men du Professeur X éclata …','Le Commencement','3','4');
insert into AVENTURE (ID,ADATE,EVENTS,FINIE,LIEU,SITUATION,TITRE,MJ_ID,UNIVERS_ID) values ('8','2006','Magnéto, secondé par Pyro et Mystique, recrute quant à lui de nombreux mutants dans sa confrérie afin de lutter contre la firme qui produit les vaccins anti-mutants. Il délivre notamment le Fléau et l''Homme-Multiple et s''entoure de mutants tels que Callisto, Psylocke, Spyke ou encore Arclight. Mais Mystique, touchée par un pistolet à aiguille, se fait inoculer le vaccin. Elle perd ses pouvoirs et Magnéto l''abandonne.','1','New York','Dans le chapitre final de la trilogie X-Men, les mutants affrontent un choix historique et leur plus grand combat... Un "traitement" leur permet désormais d''échapper à ce qu''ils sont. Pour la première fois, ils ont le choix : conserver ce qui fait leur caractère unique mais leur vaut la défiance et la méfiance de l''humanité, ou bien abandonner leurs pouvoirs et devenir des humains à part entière.
Les points de vue opposés des leaders des mutants, Charles Xavier, qui prêche la tolérance, et Magneto, qui croit à la survie des plus adaptés, sont plus que jamais incompatibles et vont déclencher la plus acharnée des batailles.','L''Affrontement final','3','4');
insert into AVENTURE (ID,ADATE,EVENTS,FINIE,LIEU,SITUATION,TITRE,MJ_ID,UNIVERS_ID) values ('9','2013',null,'0','New York','Wolverine, le personnage le plus emblématique de l’univers des X-Men, est entraîné dans une aventure ultime au cœur du Japon contemporain. Plongé dans un monde qu’il ne connaît pas, il doit faire face au seul ennemi de son envergure, dans une bataille à la vie à la mort. Vulnérable pour la première fois et poussé au bout de ses limites physiques et émotionnelles, Wolverine affrontera non seulement l’acier mortel du samouraï mais aussi les questions liées à sa propre immortalité.','Le Combat de l''immortel','3','4');
insert into AVENTURE (ID,ADATE,EVENTS,FINIE,LIEU,SITUATION,TITRE,MJ_ID,UNIVERS_ID) values ('10','1998',null,'0','L''île Neuve','Une équipe scientifique oeuvre dans le plus grand secret à la creation du Pokémon le plus puissant de tous les temps. Ayant découvert en Amazonie un cheveu fossilisé du Pokémon Mew, les généticiens établissent le code ADN de ce monstre de légende, dont ils fabriquent un clone: Mewtwo. Mais le colosse, sitôt éveillé, se retourne contre ses inventeurs et les massacre ...','Mewtwo contre-attaque','3','2');
insert into AVENTURE (ID,ADATE,EVENTS,FINIE,LIEU,SITUATION,TITRE,MJ_ID,UNIVERS_ID) values ('1','2012',null,'0','New York','Huit ans après les événements de San Venganza, Johnny Blaze alias Ghost Rider, isolé en Roumanie, tente de se séparer de sa malédiction. Un jour il reçoit la visite de Moreau, un homme lui demandant de sauver Danny, un enfant capturé par Roarke (Le Diable) dans le but de ressusciter son fils Satan.

Il lui propose en échange de purifier son âme afin de renvoyer le démon qui est en lui en enfer. Une proposition que Johnny accepte, mais les choses vont se compliquer lorsque les hommes de Roarke les poursuivent...','L''Esprit de vengeance','1','3');
insert into AVENTURE (ID,ADATE,EVENTS,FINIE,LIEU,SITUATION,TITRE,MJ_ID,UNIVERS_ID) values ('2','16 février 2007',null,'0','San Venganza','Pour sauver son père victime d''un accident, le cascadeur Johnny Blaze a vendu son âme au diable. Mais ce marché a un prix qu''il ne tarde pas à découvrir : humain la journée, il devient le Rider la nuit, un chasseur de primes traquant les âmes échappées de l''enfer.
Lorsque Roxanne est menacée, Johnny décide d''utiliser ses pouvoirs pour se retourner contre son Maître.','Ghost Rider','1','3');
insert into AVENTURE (ID,ADATE,EVENTS,FINIE,LIEU,SITUATION,TITRE,MJ_ID,UNIVERS_ID) values ('3','21 novembre 2013',null,'0','Cocoon','L''histoire de Lightning Returns se déroule 500 ans après Final Fantasy XIII-2 dans un monde baptisé Nova Chrysalia, composé de quatre régions totalement nouvelles. Selon les créateurs, le scénario de cette conclusion est directement inspiré de l''horloge de la fin du monde1.

Bhunivelze est un dieu ayant confié à Lightning la mission de sauver le plus d''âmes possible en 13 jours, pour les guider vers le nouveau monde qu''il compte mettre en place. Si elle échoue, le monde sera détruit. Elle sera assistée par Hope Estheim, un ami de longue date qui l''aidera à effectuer sa mission. Au cours de sa route sur les différents continents dans lequel elle voyagera, elle rencontrera ses compagnons et sera parfois obligée de les affronter pour les faire revenir à la raison, après qu''ils auront abandonné tout espoir de vivre dans un monde serein depuis les événements de XIII-2. Tout au long du voyage, sa mission sera perturbée par l''étrange Lumina qui semble en savoir beaucoup sur elle et sa sœur...','Lightning Returns','2','1');
insert into AVENTURE (ID,ADATE,EVENTS,FINIE,LIEU,SITUATION,TITRE,MJ_ID,UNIVERS_ID) values ('4','24 mars 2008',null,'0','Nibelheim','Crisis Core se déroule sept ans avant le commencement de Final Fantasy VII, et englobe les événements qui ont eu lieu juste avant le début. Son héros est Zack Fair, qui est un personnage secondaire de Final Fantasy VII et le précédent propriétaire de l''Épée Broyeuse avant Cloud Strife. Il est membre du SOLDAT, travaillant pour la Shinra sous les ordres de Lazard Deusericus. Le jeu raconte l''histoire de la bataille de Zack contre le vil première classe du SOLDAT, Génésis Rhapsodos, et les autres produits pervers du Projet Jenova.','Crisis Core','2','1');
insert into AVENTURE (ID,ADATE,EVENTS,FINIE,LIEU,SITUATION,TITRE,MJ_ID,UNIVERS_ID) values ('5','Il y a 1000 ans','Sin (Jecht) est vaincu par Tidus \& co, suivi de l''ensemble des chimères tour à tour possédées par Yu Yevon jusqu''à finalement son extinction à lui aussi.','1','Zanarkand','Il y a mille ans, une terrible guerre faisait rage entre deux puissantes villes-machine, Zanarkand et Bevelle. Au paroxysme de ce conflit, une entité dévastatrice du nom de Sin fit son apparition et réduisit Zanarkand à néant pour punir les hommes de leur vanité. Effrayés par cette menace inattendue, les habitants du reste de Spira se réunirent autour de la religion. Ainsi naquit Yevon, le culte de la culpabilité. Depuis mille ans, des Invokeurs se succèdent pour effectuer un pèlerinage ayant pour but d''anéantir Sin... mais seulement pour une courte durée : la Félicité. Jusqu''à présent, seules cinq Félicités ont eu été apportées. La dernière en date est celle de Braska, il y a dix ans. Mais déjà Sin est de retour. Et c''est sa fille, Yuna, qui se décide à offrir au reste de Spira cet période de calme, espérant que cette fois-ci sera la dernière : l''éternelle Félicité. Mais Sin disparaîtra-t-il un jour ? Et surtout, d''où vient ce mystérieux jeune homme nommé Tidus ?','Final Fantasy X','2','1');

alter sequence seq_cache nocache;
alter sequence av_seq increment by 11;


insert into BIOGRAPHIE (ID,TEXTE) values ('8','On sait très peu de choses sur Mew. Il est dit qu''autrefois, ils étaient nombreux, mais ils auraient tous disparus sauf 1. Ce Pokémon mythique peut apprendre toutes les capacités, c''est pour cela que certains pensent qu''il est l''ancêtre de tous les Pokémon. Pacifique, il passe son temps à jouer et à s''amuser.');
insert into BIOGRAPHIE (ID,TEXTE) values ('9','Mewtwo est un Pokémon créé par manipulation génétique. Cependant, bien que les connaissances scientifiques des humains aient réussi à créer son corps, elles n''ont pas pu doter Mewtwo d''un cœur sensible.');
insert into BIOGRAPHIE (ID,TEXTE) values ('10','Dracolosse est un Pokémon bipède ressemblant à un dragon à la peau orange.
Il est grand, assez costaud, a des yeux de couleur vert kaki, et il possède deux petites ailes bleues-vertes dans le dos lui permettant de voler. Il possède une corne et deux antennes blanches sur sa tête, 3 griffes sur ses mains et ses pieds et une queue.');
insert into BIOGRAPHIE (ID,TEXTE) values ('11','Wolverine est un personnage de Marvel Comics, super-héros et membre des X-Men. Son vrai nom est James Howlett, mais ayant oublié son passé il se fait appeller Logan.
C''est un ancien agent du gouvernement canadien et un super-héros connu sous le nom de Wolverine.
Wolverine est un mutant au squelette en adamantium, un métal indestructible. Il possède des griffes rétractiles également recouvertes du même métal, mais qui font partie intégrante de son squelette. En effet, le projet Arme X mené par le gouvernement canadien a fait de lui un soldat hyper-efficace en recouvrant son squelette et ses griffes en adamantium. Mais l''expérience est si traumatisante que sa mémoire est altérée et qu''il ne se souvient plus de son passé.');
insert into BIOGRAPHIE (ID,TEXTE) values ('12','Magnéto est le premier adversaire des X-Men d''origine. Il tente dès le début de la série de s''emparer de l''arme atomique, mais est arrêté par les X-Men.

Fondateur de la Confrérie des Mauvais Mutants, il fut à la tête des mutants de la Terre sauvage, et aussi leader suprême des Acolytes. Il dirigea un temps les Nouveaux Mutants, et fut un allié des X-Men, lorsque Charles Xavier partit dans l''espace.

Il fut accusé de crimes contre l''humanité par les Nations unies, puis tenta de s''allier au Club des Damnés, pour former une alliance contre les forces anti-mutantes. Cependant, la mort du Nouveau Mutant Douglas Ramsey alias Cypher, puis celle, apparente, des X-Men à Dallas, lui fit prendre ses distances par rapport à la vie super-héroïque. Magnéto s''exila en Terre sauvage. Cet exil prit fin lorsque, traqué par des soldats russes et les mutés de la Terre Sauvage, il dut s''échapper en tuant Zaladane, une autre mutante maîtrisant le magnétisme et qui s''était emparée de ses pouvoirs. Il eut durant cette période une liaison avec Malicia, qu''il guérit de son dédoublement de personnalité.

Magnéto s''exila à nouveau, cette fois dans l''espace. Une bande de mutants appelés les Acolytes troubla sa retraite en tentant d''échapper aux policiers chargés de les arrêter. Magnéto prit le parti des siens, et redevînt à cette occasion le leader d''une équipe méta-humaine. Blessé mais dopé par les pouvoirs de l''acolyte Fabian Cortez, Magnéto fut vaincu par les X-Men sur sa propre base spatiale, l''Astéroïde M. À cette occasion, il blessa gravement Wolverine en retirant l''adamantium de ses os. Cet acte horrifia Xavier, qui avait pris part à l''expédition, et il déconnecta la psyché de Magnéto. Cette attaque psychique provoqua l''émergence de l''entité Onslaught, faite du mélange de leurs deux psychés. Lors de la chute de la base, Magnéto ne dut sa survie qu''au X-Man Colossus qui avait infiltré son camp.');
insert into BIOGRAPHIE (ID,TEXTE) values ('2','Jeune femme solitaire et mystérieuse, elle est une ancienne membre de la Garde civile de Cocoon. Elle a renoncé à son vrai nom : Claire Farron.');
insert into BIOGRAPHIE (ID,TEXTE) values ('3','Johnny Blaze est le fils d''un cascadeur, Barton Blaze. À la mort de ses parents, Johnny est recueilli par Crash Simpson, un ami de son père qui a une fille, Roxanne Simpson.

Un jour, traumatisé par la mort de sa mère adoptive à cause de sa moto, il fait croire à Crash qu''il ne remontera jamais plus sur un deux-roues, mais il continue à s''entrainer secrètement. Puis il s''associe finalement à Crash pour monter le spectacle de cascades à moto le plus spectaculaire au monde.

Mais Crash Simpson apprend qu''il est gravement malade et décide d''arrêter le spectacle. Johnny fait alors appel à Méphisto et vend son âme en échange de la guérison de son père adoptif.Johnny n''est pas le seul membre de sa famille à avoir eu affaire avec Mephisto, sa mère biologique voulut conclure un marché avec Mephisto pour sauver son fils mais il la dupa.

Lors du dernier spectacle, Crash tente un record et se tue en effectuant le saut ; Johnny le remplace et, l''exploit accompli, il accuse Méphisto de duperie car son père est mort tout de même.

« Il n''est pas mort de maladie mais accidentellement », rétorque le Démon, qui s''apprête à prendre son dû. À ce moment, Roxanne intervient et réussit à l''empêcher de voler l''âme de Johnny. Pour se venger, Méphisto insuffle l''esprit du démon Zarathos dans le corps de Johnny.');
insert into BIOGRAPHIE (ID,TEXTE) values ('4','Zadkiel était l’un des anges du Paradis qui suivit Lucifer lors de sa révolte contre Dieu. Mais, avant la fin de la guerre, Zadkiel trahit Lucifer et, rejoignant les forces du Paradis, aida à chasser celui-ci aux Enfers. Mais, pour les punir de s’être, même provisoirement, associés à Lucifer, Zadkiel et ses légions noires furent condamnés à être chassés du Paradis et chargés d’effectuer les basses besognes du Paradis. Sa mission lui imposait de combattre les incursions des forces infernales sur Terre et de garder sous contrôle la malfaisance de l’humanité, en exterminant les pires d’entre eux. Zadkiel accomplit son devoir en contrôlant les esprits de la vengeance, manipulant ceux-ci afin de leur dissimuler leurs propres origines, pour dissimuler le rôle du Paradis dans leurs activités. Pour ce faire, Zadkiel employa un mélange de manipulations de leurs souvenirs et de contraintes pour les amener à adopter une apparence soit démoniaque, soit liée à d’autres cultes religieux. Au fil des siècles, il en vint à mépriser l’humanité en général, et plus seulement les pécheurs. Finalement, il devint convaincu qu’il serait un meilleur souverain pour le Paradis que Dieu lui-même et organisa une seconde rébellion contre l’ordre céleste, cherchant à s’emparer du trône du Paradis, bien déterminé à commencer son règne par l’extermination totale de l’humanité. Sachant que sa puissance n’était pas suffisante, il commença à recruter des fidèles sur Terre afin de le servir sur le plan des mortels et de combattre ensuite pour lui après leur mort.');
insert into BIOGRAPHIE (ID,TEXTE) values ('5','Pikachu est un petit Pokémon potelé qui ressemble à un rongeur. Il est couvert de fourrure jaune. Ses oreilles sont pointues et leurs bouts sont noirs. Il a une petite bouche, des yeux marron et deux cercles rouges sur les joues. Il y a des poches sous ses joues qui génèrent de l''électricité. Ses bras sont courts, avec cinq doigts chacun, et ses pieds possèdent trois orteils. Il a deux stries marron sur le dos, et sa queue est en forme d''éclair avec un peu de fourrure marron à la base.. Il est classé comme un quadrupède, mais il est connu pour se tenir et même marcher sur ses pattes arrières.

Le dessin animé montre que Pikachu voyage parfois en groupes. Il lève sa queue pour vérifier les environs, qui lorsqu''elle trouve quelque chose, se charge l''électricité. Comme il vit dans les régions boisées, Pikachu est connu pour griller les Baies qu''il mange avec de l''électricité pour les rendre plus tendres. On voit dans Electric Tale of Pikachu que Pikachu est capable de manger voire de détruire des cabines téléphoniques, des câbles ou autres installations électriques.

Pikachu est capable de lâcher des décharges d''électricité à la puissance variante. Pikachu est connu pour générer l''énergie dans les glandes situées sous ses joues, et doit la faire sortir pour éviter des complications. Il est aussi capable de relâcher de l''énergie de sa queue, de la recharger en la plantant dans la terre, ou encore même d''aider à recharger un camarade avec des coups d''électricité. Pikachu peut aussi s''électriser lui-même pour utiliser son attaque signature, Électacle. Quand il est menacé, il relâche l''énergie de ses joues pour créer de l''électricité, et un groupe de Pikachu peut créer de véritables orages. Il est le plus souvent trouvé dans les forêts, et le signe qu''un Pikachu est passé par là est une tâche d''herbe brûlée.');
insert into BIOGRAPHIE (ID,TEXTE) values ('6','L''extraordinaire puissance de Sin précipite Tidus, champion de l''équipe de blitzball des "Zanarkand Abes", dans ce qui restera la plus grande aventure de sa vie. Sin l''emmène dans une contrée éloignée, une contrée tellement lointaine qu''aucun retour ne semble possible. Loin de chez lui et des siens, Tidus s''éprend de la magnifique Yuna tout en entretenant une haine féroce envers son père Jecht (lui aussi une star de blitzball).');
insert into BIOGRAPHIE (ID,TEXTE) values ('7','Le nom de Yuna vient du japonais signifiant "hibiscus". Cette même fleur se retrouve sur la robe du personnage. Yuna est une jeune femme honnête et emplie de compassion. Voulant faire le bien autour d''elle, elle est consciente qu''elle seule peut arrêter Sin. Elle considère qu''il est donc de sa responsabilité de l''arrêter à n''importe quel prix, y compris sa propre vie. Elle en vient à négliger son propre bonheur pour sauver et aider autrui. Elle suit les préceptes de Yevon mais sa rencontre avec Tidus la rend plus ouverte et plus critique.');

alter sequence seq_cache nocache;
alter sequence bio_seq increment by 13;


insert into EPISODE (ID,EDATE,VALIDE,AVENTURE_ID,BIOGRAPHIE_ID,MJ_ID) values ('2','77','1','6','5',null);
insert into EPISODE (ID,EDATE,VALIDE,AVENTURE_ID,BIOGRAPHIE_ID,MJ_ID) values ('3','0','1','6','5',null);
insert into EPISODE (ID,EDATE,VALIDE,AVENTURE_ID,BIOGRAPHIE_ID,MJ_ID) values ('4','0','1',null,'11',null);
insert into EPISODE (ID,EDATE,VALIDE,AVENTURE_ID,BIOGRAPHIE_ID,MJ_ID) values ('5','7','1','8','11',null);
insert into EPISODE (ID,EDATE,VALIDE,AVENTURE_ID,BIOGRAPHIE_ID,MJ_ID) values ('7','777','1','5','7',null);
insert into EPISODE (ID,EDATE,VALIDE,AVENTURE_ID,BIOGRAPHIE_ID,MJ_ID) values ('10','0','1','7','12',null);
insert into EPISODE (ID,EDATE,VALIDE,AVENTURE_ID,BIOGRAPHIE_ID,MJ_ID) values ('11','777','1',null,'8',null);
insert into EPISODE (ID,EDATE,VALIDE,AVENTURE_ID,BIOGRAPHIE_ID,MJ_ID) values ('1','1','0',null,'3',null);
insert into EPISODE (ID,EDATE,VALIDE,AVENTURE_ID,BIOGRAPHIE_ID,MJ_ID) values ('6','0','1',null,'2',null);
insert into EPISODE (ID,EDATE,VALIDE,AVENTURE_ID,BIOGRAPHIE_ID,MJ_ID) values ('8','0','1',null,'9',null);

alter sequence seq_cache nocache;
alter sequence epi_seq increment by 12;


insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('2','0','Sacha gagne les 8 badges de la région et s''inscrit à la ligue. Malencontreusement, il échoue face à Richie, car Dracaufeu ne veut pas combattre Sparky, le Pikachu de Richie.','2');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('3','0','Pikachu est découvert dès le premier épisode, adoptant un caractère exécrable. Ne prenant pas goût à son nouveau dresseur, il l''ignora constamment, et lorsque Sacha échoua dans la capture d''un Roucool, Pikachu se moqua de lui. Toutefois, d''ici la fin de l''épisode, il finira par accorder sa confiance à Sacha, celui-ci l''ayant protégé d''une attaque par une horde de Piafabec et entraîné au Centre Pokémon. Depuis, ils sont devenus inséparables.','3');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('4','1','Pikachu refuse de rentrer dans sa Poké Ball et d''évoluer en Raichu. Au fil des épisodes, il arrivera à battre un Pokémon de type Roche/Sol avec une attaque électrique, s''opposera à la domination de Mewtwo, et électrifiera deux jeunes membres de la Team Rocket à trop de reprises pour qu''elles puissent être comptées. Il est aussi profondément altruiste, comme le montre le premier film ainsi que le mini-film des frères Pichu, extrêmement dévoué et est un bon combattant. Il semble peu exigeant pour la nourriture mais dans certains épisodes tels que Sombreville et le second épisode de la 7ème saison, on voit que Pikachu apprécie fortement le ketchup. On peut aussi voir dans plusieurs épisodes, qu''il est excellent imitateur.','3');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('5','1','À cause de la nature de ses pouvoirs, on ignore son âge exact ; mais il aurait apparemment pris part à la Guerre de Sécession et aurait donc dans les 200 ans.','4');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('6','0','En 1943, Logan travailla pour une mystérieuse organisation. Il était chargé de conduire des prisonniers politiques japonais dans une caverne secrète où des scientifiques américains les utilisaient comme cobayes. Véritable cerbère, il avait pour mission d''éliminer leur famille, tuer tout intrus ou toute personne cherchant à s''enfuir.','4');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('7','0','Il est recueilli par James et Heather Hudson pour former la Division Alpha, un groupe de super-héros canadien. Alors qu''il est pressenti pour devenir leader du groupe, il choisit de le quitter (en fait, il est amoureux de Heather mais découvre que c''est Hudson qui lui a greffé son squelette d''adamantium) pour rejoindre les X-Men, recruté par le Professeur Xavier. Une forte hostilité naît entre Hudson et lui à cause de cette décision. Le gouvernement canadien tente de le faire ramener de force depuis les États-Unis. Après l''échec de ces tentatives, Hudson et Wolverine font la paix.','4');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('8','0','Un gigantesque combat éclate car les mutants de Magnéto donnent l''assaut alors que les militaires défendent l''île. Iceberg triomphe de Pyro, Kitty Pryde sauve Jimmy du Fléau, et Wolverine finit par réussir à distraire Magnéto le temps que le Fauve lui injecte l''antidote, ce qui lui retire tous ses pouvoirs. Cependant, le Phénix n''est pas vaincu et détruit tout autour d''elle. Wolverine comprend alors que seul lui peut l''arrêter. Il s''efforce de s''approcher de Jean Grey, mutilé par sa puissance psychique, mais grâce à sa mutation et à son squelette en adamantium il parvient à arriver jusqu''à elle. Jean regagne momentanément le contrôle et supplie Wolverine de la tuer, ce qu''il fait le cœur brisé.','5');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('11','0','Yuna refuse finalement le combat final face à Sin quand elle apprend les derniers secrets au sujet de l''Eglise, de la religion et pire encore de Yevon lui-même. Elle choisit alors de trouver un autre moyen d''éradiquer définitivement Sin et se tourne alors vers les Al Bheds. Elle rompt à ce moment complètement avec la religion dont elle était, en tant qu''Invokeur, une représentante puisqu''elle s''allie à ses pires ennemis. Avec l''aide des machines, le groupe parvient à entrer dans le corps du monstre qui terrorise Spira depuis mille ans.','7');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('12','1','Après une succession de plusieurs terribles combats, ils réussissent enfin à éliminer Yevon. À cet instant, on assiste à une scène déchirante car si Yuna ne meurt finalement pas, c''est Tidus qui disparaît. N''étant qu''un rêve des priants de Zanarkand, il ne peut plus continuer de vivre sans eux.','7');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('15','0','Magneto est persécuté dans un camp nazi.','10');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('16','0','Ses pouvoirs de contrôle du métal son observés par des dirigeants, qui tentent de l''utiliser.','10');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('17','1','Pour l''aider à contrôler ses pouvoirs, les nazis tuent sa mère. Dans un excès de fureur, Magneto obtient finalement le contrôle de ses pouvoirs.','10');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('18','0','Mew fait face à Mewtwo dans un combat sans merci.','11');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('19','1','On dit que Mew possède le code génétique de tous les autres Pokémon. Il peut se rendre invisible à sa guise, ce qui lui permet de ne pas se faire remarquer quand il s''approche des gens.','11');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('1','0','Depuis ce jour, Johnny partage son corps avec ce démon qui, la nuit, le transforme en squelette enflammé luttant contre la violence urbaine, chevauchant une moto aux roues de feu et armé d''une chaine mystique, sous l''identité de Ghost Rider.','1');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('9','0','Lightning est le personnage principal du jeu Final Fantasy XIII. ','6');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('10','1','« Le tout n''est pas de savoir si l''on peut le faire, mais si l''on doit le faire. »
— Lightning','6');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('13','0','Création de Mewtwo par clonage : il détruit le laboratoire de ses créateurs ...','8');
insert into PARAGRAPHE (ID,SECRET,TEXTE,EPISODE_ID) values ('14','1','Mewtwo est furieux, mais Giovanni parvient à le faire travailler pour lui.','8');

alter sequence seq_cache nocache;
alter sequence para_seq increment by 20;


insert into PERSONNAGE (ID,NAISSANCE,NOM,PORTRAIT,PROFESSION,VALIDE,BIOGRAPHIE_ID,JOUEUR_ID,MJ_ID,TRANSFERT_ID,UNIVERS_ID,VALIDATEUR_ID) values ('8','Origine de l''univers','Mew','http://vignette3.wikia.nocookie.net/pokemon/images/b/b7/151Mew_OS_anime_6.png/revision/20150101100555','Pokémon légendaire','1','8','4','3',null,'2',null);
insert into PERSONNAGE (ID,NAISSANCE,NOM,PORTRAIT,PROFESSION,VALIDE,BIOGRAPHIE_ID,JOUEUR_ID,MJ_ID,TRANSFERT_ID,UNIVERS_ID,VALIDATEUR_ID) values ('9','666','Mewtwo','http://2.bp.blogspot.com/_ceAOK0k7QiY/TKXhWptYU7I/AAAAAAAAAAM/XMkxC9FHH9Q/s1600/Mewtwo_is_Epic_by_lord_phillock.jpg','Menace planétaire','1','9','1','3',null,'2','3');
insert into PERSONNAGE (ID,NAISSANCE,NOM,PORTRAIT,PROFESSION,VALIDE,BIOGRAPHIE_ID,JOUEUR_ID,MJ_ID,TRANSFERT_ID,UNIVERS_ID,VALIDATEUR_ID) values ('10','777','Dracolosse','http://www.pokepedia.fr/images/8/87/Dracolosse-RFVF.png','Pokémon Dragon','1','10','3','1',null,'2','1');
insert into PERSONNAGE (ID,NAISSANCE,NOM,PORTRAIT,PROFESSION,VALIDE,BIOGRAPHIE_ID,JOUEUR_ID,MJ_ID,TRANSFERT_ID,UNIVERS_ID,VALIDATEUR_ID) values ('11','1980','Wolverine','http://screenrant.com/wp-content/uploads/Wolverine-3-Starring-Hugh-Jackman-Production-Start-Date.jpg','Immortel','1','11','2','3',null,'4','3');
insert into PERSONNAGE (ID,NAISSANCE,NOM,PORTRAIT,PROFESSION,VALIDE,BIOGRAPHIE_ID,JOUEUR_ID,MJ_ID,TRANSFERT_ID,UNIVERS_ID,VALIDATEUR_ID) values ('12','1926','Magneto','http://screenrant.com/wp-content/uploads/Michael-Fassbender-Magneto-X-Men-Days-of-Future-Past.jpg','Terroriste','1','12','4','3',null,'4','3');
insert into PERSONNAGE (ID,NAISSANCE,NOM,PORTRAIT,PROFESSION,VALIDE,BIOGRAPHIE_ID,JOUEUR_ID,MJ_ID,TRANSFERT_ID,UNIVERS_ID,VALIDATEUR_ID) values ('2','1991','Lightning','http://www.dsogaming.com/wp-content/uploads/2014/09/Final-Fantasy-XIII-feature-672x372.jpg','Garde civile de Cocoon','1','2','1','2',null,'1','2');
insert into PERSONNAGE (ID,NAISSANCE,NOM,PORTRAIT,PROFESSION,VALIDE,BIOGRAPHIE_ID,JOUEUR_ID,MJ_ID,TRANSFERT_ID,UNIVERS_ID,VALIDATEUR_ID) values ('3','7 Janvier 1964','Johnny Blaze','http://www.critique-film.fr/wp-content/uploads/2012/01/nicolas-cage-ghost-rider-spirit-of-vengeance-banni%C3%A8re.jpg','Rider','1','3','4','1',null,'3',null);
insert into PERSONNAGE (ID,NAISSANCE,NOM,PORTRAIT,PROFESSION,VALIDE,BIOGRAPHIE_ID,JOUEUR_ID,MJ_ID,TRANSFERT_ID,UNIVERS_ID,VALIDATEUR_ID) values ('4','666','Ghost Rider','http://cdn.collider.com/wp-content/uploads/Ghost-Roder-2image-ghost-rider-spirit-of-vengeance-31.jpg','Rider','1','4','3','1',null,'3','1');
insert into PERSONNAGE (ID,NAISSANCE,NOM,PORTRAIT,PROFESSION,VALIDE,BIOGRAPHIE_ID,JOUEUR_ID,MJ_ID,TRANSFERT_ID,UNIVERS_ID,VALIDATEUR_ID) values ('5','27 février 1996','Pikachu','http://gamegeex.blogomancer.com/files/gamegeex/images/headerimages/1443_new-pikachu-game-is-in-the-works.png','Pokémon de type Électrique','1','5','2','3',null,'2',null);
insert into PERSONNAGE (ID,NAISSANCE,NOM,PORTRAIT,PROFESSION,VALIDE,BIOGRAPHIE_ID,JOUEUR_ID,MJ_ID,TRANSFERT_ID,UNIVERS_ID,VALIDATEUR_ID) values ('6','Il y a 17 ans','Tidus','http://static.giantbomb.com/uploads/original/0/1872/232985-final_fantasy_x_tidus.jpg','Joueur de Blitzball','1','6','4','2',null,'1',null);
insert into PERSONNAGE (ID,NAISSANCE,NOM,PORTRAIT,PROFESSION,VALIDE,BIOGRAPHIE_ID,JOUEUR_ID,MJ_ID,TRANSFERT_ID,UNIVERS_ID,VALIDATEUR_ID) values ('7','Il y a 17 ans','Yuna','http://www.finalfantasy.net/wp-content/uploads/2013/08/310.jpg','Invokeur','1','7','3','2',null,'1',null);

alter sequence seq_cache nocache;
alter sequence pers_seq increment by 13;


insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('2','3');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('2','4');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('3','2');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('5','6');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('5','7');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('6','5');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('6','10');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('7','11');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('7','12');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('8','11');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('8','12');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('9','11');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('9','12');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('10','5');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('10','8');
insert into PARTICIPE (AVENTURE_ID,PERSONNAGE_ID) values ('10','9');



commit;


