package fr.iutvalence.m2107.p24.Model;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.Slot;
import fr.iutvalence.m2107.p24.items.ItemsList;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PlayerModel {
    Direction watchingAt;
    float damage;
    float health;
    Position roomPosition;
    Position playerPosition;
    List<Slot> inventory;
    Direction playerDirection;
}
