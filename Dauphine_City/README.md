# Projet_DauphineCity

Voici le Repository GitHub associé au projet Java L3 IM2D 2025 pour le groupe Alaoui, Bessard, El Fassi.

Il contient les packages :
  - equipe, qui simule une équipe municipale,
  - generer_instances, qui génère des instances,
  - sacADos, qui définit le concept de sac à dos,
  - solveur, qui permet de dire quels objets mettre dans un sac à dos. Il y a les solveurs :
      * glouton, un par ajout et un par retrait
      * hillclimbing
  - test, qui contient les tests J-Units.


Après avoir cloné le git, et l'avoir compilé, il faut exécuter le Main dans generer_instances, et lancer les tests J-Units présents dans test.

**Remarque importante pour Eclipse** : afin de pouvoir exécuter les tests J-Unit, il est nécessaire d’ajouter la bibliothèque JUnit au projet via  
**clic droit sur le projet → Build Path → Add Libraries → JUnit**.
