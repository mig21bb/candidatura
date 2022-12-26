package com.candidatura.espublico.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StarshipDTO {

    private Integer starshipId;
    private String name;
    private String model;
    private String manufacturer;
    private String cost_in_credits;
    private String length;
    private String maxAtmospheringSpeed;
    private String crew;
    private String passengers;
    private String cargoCapacity;
    private String consumables;
    private String hyperdriveRating;
    private String MGLT;
    private String starshipClass;
    private Date created;
    private Date edited;
    private String url;
    private List<PeopleDTO> pilots;
    private List<FilmDTO> films;

    public StarshipDTO() {}


    public void setMGLT(String MGLT){
        this.MGLT=MGLT;
    }

    public String getMGLT(){
        return this.MGLT;
    }

    public StarshipDTO(Integer starshipId, String name, String model, String manufacturer, String cost_in_credits, String length, String maxAtmospheringSpeed, String crew, String passengers, String cargoCapacity, String consumables, String hyperdriveRating, String MGLT, String starshipClass, Date created, Date edited, String url, List<PeopleDTO> pilots, List<FilmDTO> films) {
        this.starshipId = starshipId;
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.cost_in_credits = cost_in_credits;
        this.length = length;
        this.maxAtmospheringSpeed = maxAtmospheringSpeed;
        this.crew = crew;
        this.passengers = passengers;
        this.cargoCapacity = cargoCapacity;
        this.consumables = consumables;
        this.hyperdriveRating = hyperdriveRating;
        this.MGLT = MGLT;
        this.starshipClass = starshipClass;
        this.created = created;
        this.edited = edited;
        this.url = url;
        this.pilots = pilots;
        this.films = films;
    }
}
