Appli en ligne à l'adresse : http://rpg-723.rhcloud.com

Design CSS : [Bootstrap Twitter [documentation]](http://getbootstrap.com/)

Tool UML : https://genmymodel.com


# Identifiants Oracle

Dans le dossier src/main/webapp/META-INF, copier le template context.xml.template vers context.xml et remplacer XXXX par les identifiants Oracle



### BDD

"Un épisode peut être lié à une aventure. Lors de la création d’un épisode, l’application propose la liste des aventures auxquelles le personnage a participé pour permettre ce lien."

=> Donc il faut conserver l'historique des aventure de chaque personnage




Todo :

- acteurs, diagramme de cas d'utilisations et description de ces cas d'utilisations, illustrées par des diagrammes de séquence système pertinents (voir l'ensemble des fonctionnalités décrites dans todo.txt)
- définir l'ensemble des fonctions de contrôleur + servlets + chaque vue


Code JEE : conception archi

1 servlet + 1 url par contrôleur
	plusieurs sous-contrôleurs par fichier selon un paramètre GET

Organisation des vues en plsrs dossiers correspondant aux contrôleurs
Utilisation des templates 'tag' JSP pour factoriser les vues


Ensuite :

- doc conception : à rédiger après implém java pour éviter les changements

- rédaction readme.txt
- rédaction manuel utilisateur
- rédaction bilan
