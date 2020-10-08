# Comment contribuer au projet

__Les instructions ci-dessous doivent être scrupuleusement suivit dans un but organisationnel__

_On ne doit jamais laisser se produire un désordre pour éviter une guerre ; car on ne l'évite jamais, on la retarde à son désavantage._ __Machiavel__

1. Créer une issue qui correspond une tâche
	- Mettre un titre
	- Décrire la tâche
1. Ajouter l'issue à la planche de kanban sur github sous __project__
	- Passer la story de TODO à in progress
	- s'assigner à la story
1. Créer une branche associé à l'issue (template fb-__NomBranche__)
  	- Travailler sur la branche
1. Une fois le travail achevé
	- Créer une pull Request -> Master et décrire brievement l'issue
	- Demander la review d'un des membres du groupe
1. Quand la PR est validé
	- effectuer un Merge (en résolvant les conflit éventuels en local)
	- supprimer la branche
	- Déplacer la story sous __Done__

## Les tests unitaires (Unit Test)

__Chaque développeur se doit d'implémenter des tests unitaire qu'il juge approprié__

Lors d'ajout de code/fonction, rajouter sous ``src/test/<cheminfichuer>`` la classe appropriée aux test. Utiliser les fonction d'assertion de l'API de __JUnit__ pour vérifier les conditions.

Les questions de __quoi tester__ et __comment tester__ sont à se poser avant chaque rédaction, elle n'ont pas forcément de réponse évidente.

## référence utiles
[](https://www.martinfowler.com/bliki/UnitTest.html)
