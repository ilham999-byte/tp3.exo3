# tp3.exo3
# Application de Gestion de Projet

Ce projet est une application de gestion de projets permettant d'imputer le temps passé dans un projet sur son coût global. Il permet de gérer les projets, les tâches, et les employés associés aux projets.

## Objectifs

L'application offre des fonctionnalités permettant de gérer les entités projet, tâche, employé, ainsi que les relations entre ces entités.

## Instructions de développement

### A. Couche Persistance

1. **Classes Entités :**  
   Développer les classes entités dans le package `ma.projet.classes`.

2. **Configuration Hibernate :**  
   Créer le fichier de configuration `hibernate.cfg.xml` dans le package `ma.projet.config`.

3. **Utilitaire Hibernate :**  
   Créer la classe `HibernateUtil` dans le package `ma.projet.util` pour gérer la session `SessionFactory`.

### B. Couche Service

1. **Interface Générique IDao :**  
   Créer une interface générique `IDao` dans le package `ma.projet.dao`.

2. **Classes Services :**  
   Implémenter les classes de services suivantes dans le package `ma.projet.service` :
   - `ProjetService`
   - `TacheService`
   - `EmployeService`
   - `EmployeTacheService`

3. **Méthodes Spécifiques :**  
   - Afficher la liste des tâches réalisées par un employé dans `EmployeService`.
   - Afficher la liste des projets gérés par un employé dans `EmployeService`.
   - Afficher la liste des tâches planifiées pour un projet dans `ProjetService`.
   - Afficher la liste des tâches réalisées dans un projet spécifique dans `ProjetService`.
     Exemple :
     ```
     Projet : 4 | Nom : Gestion de stock | Date de début : 14 Janvier 2013
     Liste des tâches :
     Num | Nom         | Date Début Réelle | Date Fin Réelle
     12  | Analyse     | 10/02/2013        | 20/02/2013
     13  | Conception  | 10/03/2013        | 15/03/2013
     14  | Développement | 10/04/2013      | 25/04/2013
     ```
   - Afficher la liste des tâches dont le prix est supérieur à 1000 DH dans `TacheService` (en utilisant une requête nommée).
   - Afficher la liste des tâches réalisées entre deux dates dans `TacheService`.

## Scénarios de test

Des programmes de tests sont créés pour valider les fonctionnalités suivantes :
- Afficher la liste des tâches réalisées par un employé.
- Afficher la liste des projets gérés par un employé.
- Afficher la liste des tâches planifiées pour un projet.
- Afficher la liste des tâches réalisées dans un projet.
- Afficher la liste des tâches dont le prix dépasse 1000 DH.
- Afficher la liste des tâches réalisées entre deux dates.

## Technologies Utilisées

- Java
- Hibernate-JPA
- MySQL
- NetBeans IDE
