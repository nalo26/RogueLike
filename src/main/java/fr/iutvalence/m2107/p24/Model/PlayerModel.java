package fr.iutvalence.m2107.p24.Model;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.items.ItemsList;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PlayerModel {
    String watchingAt;
    float damage;
    float health;
    Position roomPosition;
    Position playerPosition;
    List<ItemsList> inventory;
    Direction playerDirection;
}
