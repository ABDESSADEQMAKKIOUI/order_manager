# OrderCraft 
est une application Java avancée conçue pour rationaliser la gestion des commandes, des clients, des articles et de l'authentification des utilisateurs pour l'entreprise ArtWood. Cette application simplifie la gestion des commandes, la gestion des clients et le suivi des stocks, améliorant ainsi l'efficacité opérationnelle.

Pour Commencer

Ces instructions vous aideront à obtenir une copie d'OrderCraft opérationnelle sur votre machine locale pour le développement et les tests. Exécuté le fichier Order.sql pour le scripte de base des données

Prérequis Pour exécuter OrderCraft, vous devez installer les logiciels suivants :

# Java JDK : 
Version 8 ou supérieure. Téléchargeable sur le site d'Oracle ou via OpenJDK. Base de données MySQL : Nécessaire pour le stockage et la gestion des données. Téléchargeable sur le site de MySQL. IntelliJ IDEA : IDE recommandé pour le développement. Disponible sur JetBrains. Installation Cloner le dépôt : Clonez le projet depuis GitHub sur votre machine locale. git clone [url-du-dépôt] Configuration de la base de données : Créez une base de données nommée artwood1 dans MySQL et exécutez les scripts SQL fournis pour configurer les tables. Configuration de l'IDE : Ouvrez le projet dans IntelliJ IDEA et assurez-vous que toutes les dépendances sont correctement configurées dans la configuration de build de votre projet. Exécution de l'Application Exécutez la classe principale de l'application depuis votre IDE pour démarrer le serveur. Accédez à l'application via le port spécifié sur votre machine locale (habituellement localhost:8080). Fonctionnalités

# Gestion des Clients :
Ajouter, mettre à jour et supprimer des informations sur les clients. Traitement des Commandes : Créer de nouvelles commandes, mettre à jour le statut des commandes et gérer les détails des commandes. Suivi des Articles : Gérer l'inventaire des articles, y compris l'ajout de nouveaux articles et la mise à jour des articles existants. Authentification des Utilisateurs : Système sécurisé de connexion et d'inscription des utilisateurs. Construit Avec

## sprng : Pour la logique backend. 
## MySQL : Comme base de données pour stocker toutes les données. 
## thymeleaf  : Pour le frontend et la gestion des requêtes.
# instalation
créer base de données mysql (order)
édité les configuaton 
![Capture d’écran 2023-12-29 142322](https://github.com/ABDESSADEQMAKKIOUI/order_manager/assets/95092583/093b25df-d1da-4b2d-9a7f-6ab64f4df965)
#Diagramme
![Use Case Diagram1](https://github.com/ABDESSADEQMAKKIOUI/order_manager/assets/95092583/ebe47927-73f4-4842-ba2e-3165650f98f2)
![Class Diagram1](https://github.com/ABDESSADEQMAKKIOUI/order_manager/assets/95092583/cb02d0f6-0fdd-4250-8bfd-ad17043dce23)


