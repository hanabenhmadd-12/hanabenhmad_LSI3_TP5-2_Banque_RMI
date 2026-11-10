\# TP 5-2 : Service de banque avec RMI et JNDI



\*\*Étudiante :\*\* Hana Ben Hmad - LSI3

\*\*Matière :\*\* Développement d'applications réparties

\*\*Enseignante des TPs :\*\* Zeineb DAOUD



\## Objectif



Développer une application répartie de gestion de comptes bancaires avec Java RMI, puis remplacer l'accès direct au registre RMI par JNDI.

Un compte est défini par un code (int), un solde (double) et une date de création (Date).

Le serveur offre deux services distants : créer un compte (si le code n'existe pas déjà) et consulter un compte à partir de son code.



\## Étape 1 : Préparation de l'espace de travail



Dossier de travail `hanabenhmad\_LSI3\_Banque` avec un sous-dossier `captures`.



\## Étape 2 : Développement du serveur (RMIBanque\_Server)



\### Arborescence du projet

!\[Arborescence du serveur](captures/01\_arborescence\_serveur.png)



\### Classe Compte (package metier)

!\[Compte](captures/02\_compte.png)



\### Interface IBanque (package rmiService)

!\[IBanque](captures/03\_ibanque.png)



\### Classe BanqueImpl (package rmiService)

!\[BanqueImpl](captures/04\_banqueimpl.png)



\### Classe BanqueServer, version 1 (package rmiServer)

!\[BanqueServer v1](captures/05\_server\_v1.png)



\## Étape 3 : Développement du client (RMIBanque\_Client)



\### Génération du fichier banqueClient.jar

Le jar contient uniquement IBanque et Compte, sans BanqueImpl.

!\[Contenu du jar](captures/06\_jar.png)



\### Ajout du jar au classpath du client

!\[Classpath](captures/07\_classpath.png)



\### Classe BanqueClient, version 1 (package rmiClient)

!\[BanqueClient v1](captures/08\_client\_v1.png)



\## Étape 4 : Exécution (version 1, sans JNDI)



\### Démarrage du serveur

!\[Serveur v1](captures/09\_exec\_v1\_serveur.png)



\### Résultat du client

!\[Client v1](captures/10\_exec\_v1\_client.png)



\### Console du serveur après la requête

!\[Serveur v1 après requête](captures/11\_exec\_v1\_serveur\_requete.png)



\## Étape 5 : Utilisation de JNDI



\### Fichier jndi.properties du serveur

!\[jndi.properties serveur](captures/12\_jndi\_properties.png)



\### Fichier jndi.properties du client

!\[jndi.properties client](captures/12b\_jndi\_client.png)



\### BanqueServer, version JNDI

!\[BanqueServer JNDI](captures/13\_server\_jndi.png)



\### BanqueClient, version JNDI

!\[BanqueClient JNDI](captures/14\_client\_jndi.png)



\### Exécution du serveur JNDI

!\[Serveur JNDI](captures/15\_exec\_jndi\_serveur.png)



\### Exécution du client JNDI

!\[Client JNDI](captures/16\_exec\_jndi\_client.png)



\## Étape 6 : Dépôt sur GitHub



!\[Repository GitHub](captures/17\_github.png)



\## Synthèse

Ce TP m'a permis de mettre en œuvre une application répartie de gestion de comptes bancaires avec Java RMI, puis d'intégrer JNDI.



\*\*Architecture.\*\* Le serveur (RMIBanque\_Server) contient la classe sérialisable `Compte`, l'interface distante `IBanque`, son implémentation `BanqueImpl` et la classe `BanqueServer`. Le client (RMIBanque\_Client) ne reçoit que `IBanque` et `Compte`, via le fichier `banqueClient.jar`. `BanqueImpl` reste côté serveur : le client n'a besoin que du contrat, pas de l'implémentation.



\*\*Version 1 (RMI seul).\*\* Le serveur crée le registre RMI sur le port 1099 et enregistre le service avec `Naming`. Le client le retrouve avec `Naming.lookup()` puis appelle `creerCompte` et `getInfoCompte` comme sur un objet local. Le test a montré la création des comptes et le refus d'un code déjà existant.



\*\*Version 2 (RMI avec JNDI).\*\* Le fichier `jndi.properties` placé dans `src` (côté serveur et côté client) indique la fabrique `RegistryContextFactory` et l'URL `rmi://localhost:1099`. Le serveur utilise `InitialContext.rebind()` et le client `InitialContext.lookup()`. Le code ne dépend plus directement de `Naming` : le fournisseur d'annuaire se change dans la configuration, sans modifier le code.



\*\*Résultat.\*\* Les résultats de la version JNDI sont identiques à ceux de la version 1, ce qui confirme que la communication RMI fonctionne toujours.



\*\*Difficultés et points retenus.\*\* Il faut démarrer le serveur avant le client, l'arrêter avant de le relancer (port 1099 occupé), et ne mettre que `IBanque` et `Compte` dans le jar. Le même `Compte` doit exister des deux côtés, car l'objet est sérialisé entre le client et le serveur.

