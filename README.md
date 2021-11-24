# Rogue Like Game

"Rogue Like" game, based on the idea of exploring an "infinite" dungeon by fighting monsters.
In order to finish the game, you have to collect the 4 keys that are in the dungeon.
Once this is done, you can unlock the Boss's room, and kill it.

The world is pre-generated before the player appears in it.
It is a maze of different rectangular rooms connected to each other.
The player appears on the map in the center room, with coordinates 0, 0.

Each room contains monsters (zombies, skeletons, slimes), each with different attributes such as their hit points, their attack level, their color (for slimes only).
In addition, each room contains potions (life, poison, speed) that can be consumed by the player.

The monsters have an artificial intelligence allowing them to move around the room and hit the player.
If they are killed, they drop experience points.

The player can move, change room, and attack.
He can also increase his level by collecting the experience that the monsters drop.
He will then sees his attacks and hit points being improved.

The goal of this project is to create a graphical application allowing to fully play the game.
The game will be developed in Java, using the object-oriented programming method.
