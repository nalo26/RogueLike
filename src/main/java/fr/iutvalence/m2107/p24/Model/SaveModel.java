package fr.iutvalence.m2107.p24.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class SaveModel {

    Map<String, RoomModel> rooms;

    @JsonProperty("player")
    PlayerModel playerModel;
}
