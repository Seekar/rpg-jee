Appli en ligne à l'adresse : http://rpg-723.rhcloud.com

Design CSS : [Bootstrap Twitter [documentation]](http://getbootstrap.com/)

Tool UML : https://genmymodel.com


### Identifiants Oracle

Dans le dossier src/main/webapp/META-INF, copier le template context.xml.template vers context.xml et remplacer XXXX par les identifiants Oracle

### Répartition

[Description des fonctionnalités](https://github.com/leogouttefarde/rpg/blob/master/todo.txt)

L'idéal serait d'avoir 1 personne sur les parties, 1-2 personne(s) sur les épisodes + paragraphes et 1 personne sur les personnages à priori

### Vues JSP

- Utilisation de template(s) 'tag' JSP pour factoriser les vues
- Utilisation de JSTL pour conditionner les vues etc


### BDD

"Un épisode peut être lié à une aventure. Lors de la création d’un épisode, l’application propose la liste des aventures auxquelles le personnage a participé pour permettre ce lien."

=> Donc il faut conserver l'historique des aventure de chaque personnage


### ACVL :

- acteurs, diagramme de cas d'utilisations et description de ces cas d'utilisations, illustrées par des diagrammes de séquence système pertinents (voir l'ensemble des fonctionnalités décrites dans todo.txt)



Ensuite :

- doc conception : à rédiger après implém java pour éviter les changements

- rédaction readme.txt
- rédaction manuel utilisateur
- rédaction bilan
