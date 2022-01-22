package fr.iutvalence.m2107.p24.Model;

import fr.iutvalence.m2107.p24.Position;
import fr.iutvalence.m2107.p24.entities.Mob;
import fr.iutvalence.m2107.p24.items.Item;
import fr.iutvalence.m2107.p24.ressources.Images;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class RoomModel {

    public List<Mob> mobs;
    public Map<Position, Images> decors;
    public List<Item> items;
    public Position position;
    public boolean visited;
    public String connections;

}
