Groupe **2.4**, composé de
Nathan Vey, Maxime Testu, Nathan Peyronnet, Quentin Charra, Clément Monello.

Jeu sans but final, le joueur apparait dans une salle carré (de coordonnées 0, 0)
comportant 4 portes sur chaque côté. À chaque nouvelle porte franchis, si la
nouvelle salle n'a pas déjà été générée, une nouvelle est donc créée, avec
certains attributs.
Les nouvelles portes garderont une logique par rapport aux autres salles (-> génération procédurale).
Chaque salle comporte des monstres (Zombies, Squelettes, Slimes), chacun
comportant différents attributs tels que leurs point de vie, le niveau
d'attaque, leur couleur (pour les slimes seulement)...
Les monstres possèdent une intelligence artificielle leur permettant d'attaquer
le joueur, et s'ils sont tués, ils lachent des butins (mana, potion, vie...)
Le joueur quant à lui, peut se déplacer, changer de salle, attaquer...