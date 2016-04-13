/*
 * Equipe 14 :
 *
 * ABOUBACAR Salim, DEMETS Jules-Eugène, GOUTTEFARDE Léo, REY Simon
 *
 * Projet ACVL / Web
 */


Comment déployer l'application ?


1) Installation Oracle
======================

a/ Connexion fournie
--------------------

Rien à faire (déjà installée).

Identifiants Oracle
Nom d'utilisateur : reysi
Mot de passe      : simon


b/ Connexion personnalisée
--------------------------

D'abord il faut changer le nom d'utilisateur et le mot de passe d'accès à la base Oracle au sein du fichier projet/src/main/webapp/META-INF/context.xml

Ensuite, il faut également exécuter le script install_bd.sql
Pour cela, il faut d'abord lancer sqlplus via la commande suivante (permet la bonne gestion des accents) :
NLS_LANG=FRENCH_FRANCE.UTF8 sqlplus login@ensioracle1

Il suffit alors d'exécuter le script install_bd.sql fourni.



2) Compilation / déploiement Tomcat
===================================

a/ En ligne de commande
-----------------------

Exécuter les commandes suivantes depuis le dossier projet :

~/apache-tomcat-8.0.30/bin/startup.sh
mvn tomcat:deploy
# (ou mvn tomcat:redeploy si déjà déployé une première fois)


b/ Avec Netbeans
----------------

Ouvrir le dossier projet dans netbeans, compiler puis déployer à l'aide de la flèche verte.



3) Lancement de l'application
=============================

Pour finir, il suffit de se rendre à l'adresse suivante dans un navigateur : http://localhost:8080/projet

A partir de là, on peut se connecter au sein de l'application en cliquant sur Login avec les joueurs fournis suivants :


Identifiant  : admin
Mot de passe : admin17Lord

Identifiant  : Clara
Mot de passe : Vasto42

Identifiant  : James
Mot de passe : james007tb

Identifiant  : Max
Mot de passe : max71Lord


