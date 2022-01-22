package fr.iutvalence.m2107.p24.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.Map;
@AllArgsConstructor
public class SaveModel {

    @JsonProperty("rooms")
    Map<String, RoomModel> rooms;

    @JsonProperty("player")
    PlayerModel playerModel;


}
