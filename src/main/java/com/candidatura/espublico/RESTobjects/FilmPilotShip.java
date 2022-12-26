package com.candidatura.espublico.RESTobjects;

import lombok.Data;

@Data
public class FilmPilotShip {

    private String title;
    private String pilotName;
    private String shipName;

    public FilmPilotShip() {
    }
    public FilmPilotShip(String title, String pilotName, String shipName){
        this.title= title;
        this.pilotName= pilotName;
        this.shipName= shipName;
    }

    public String getTitle() {
        return title;
    }

    public String getPilotName() {
        return pilotName;
    }

    public String getShipName() {
        return shipName;
    }
}
