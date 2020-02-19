# DIC
Dependencies-Injector-Container

### Arborescence

Dans le dossier DIC se trouve le conteneur/injecteur de dépendence ainsi notre annotation personnalisée.
Dans le dossier test on retrouve les tests unitaires ainsi que des classes basiques permettant d'utiliser toutes les fonctionnalités du conteneur.

### Explications

Le conteneur fonctionne à 100% avec un système d'annotations. Chaque annotation a un nom de clé permettant de récupérer la valeur enregistrée associée.
On peut annoter les champs, les constructeurs (et leurs paramètres) et les setters. On peut aussi annoter une classe dans le cadre d'une injection d'interface.
Le conteneur permet d'enregistrer une string (la valeur de l'annotation) avec une valeur (objet instancié). Puis on peut créer un objet en utilisant au choix une instanciation par construteur, champs ou setters.

Le fonctionnement de l'instanciation par le conteneur est le suivant: 
Si la classe est primitive
  On retourne une instanciation par défaut arbitraire (ex: int->0, bool->false...)  
Sinon
  Si la classe est annotée 
    On regarde si une classe qui l'implémente a été enregistrée pour cette classe, dans ce cas nous retournons cette classe
  Sinon 
    On continue avec la classe
  On cherche les champs (setters, constructeur) annotés, puis récupère la valeur de l'annotation et cherche une valeur enregistrée. 
  Si elle existe
    On retourne cette valeur
  Sinon
    On appelle récursivement la même méthode d'instanciation.
On retourne l'objet ainsi créé

### Utilisation

Il faut annoter les champs, constructeurs, etc que l'on souhaite injecter. On leur associe une clé.
Ensuite on crée le conteneur et on enregistre la clé avec la valeur que l'on souhaite obtenir.

Exemple:
````
Container container = new Container();

container.register("AccountManagement.amount", 1000.0);

AccountManagement accMan = (AccountManagement) container.getInstanceInjectFields(AccountManagement.class);
// Account management: { amount = 1000.0 }

container.register("Bank.postalCode", 63000);
container.register("Bank.accountManagement", accMan);
container.register("Bank.name", "Crédit Agricool");

CreditAgricool cATest = (CreditAgricool) container.getInstanceInjectSetters(CreditAgricool.class);
// CreditAgricool: { name = Crédit Agricool, Account management: { amount = 1000.0 }, 63000 }
````


Attention: Nous n'avons pas pris en compte une mauvaise utilisation du conteneur (ex: enregistrer un String pour un champ qui est un int).
