Tool UML : https://genmymodel.com

Design CSS : [Bootstrap Twitter](http://getbootstrap.com/) [[documentation]](http://getbootstrap.com/)


###Problèmes BDD
#### Relation Aventure-Perso-Joueur
- Pour un transfert, il faudrait changer le joueur içi aussi et pas que dans Personnage
- Après chaque partie finie, il faudrait supprimer la relation Jouer
- Personnage pourrait aussi remplir le role de la relation Jouer pour simplifier




Todo :

- maquette/vues à finir pour visualiser les fonctionnalités comprises facilement (équivalence Usecase)
- acteurs, diagramme de cas d'utilisations et description de ces cas d'utilisations, illustrées par des diagrammes de séquence système pertinents

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
