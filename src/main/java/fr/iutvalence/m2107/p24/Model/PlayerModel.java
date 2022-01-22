package fr.iutvalence.m2107.p24.Model;

import fr.iutvalence.m2107.p24.Direction;
import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.Slot;
import fr.iutvalence.m2107.p24.items.ItemsList;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PlayerModel {
    public Direction watchingAt;
    public float damage;
    public float health;
    public Position roomPosition;
    public Position playerPosition;
    public List<Slot> inventory;
    public Direction playerDirection;
}
