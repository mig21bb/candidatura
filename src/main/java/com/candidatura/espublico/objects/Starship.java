package com.candidatura.espublico.objects;

import com.candidatura.espublico.entities.StarshipEntity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Starship {

    private Integer starshipId;
    private String name;
    private String model;
    private String manufacturer;
    private String cost_in_credits;
    private Integer length;
    private Integer max_atmosphering_speed;
    private String crew;
    private String passengers;
    private Long cargo_capacity;
    private String consumables;
    private BigDecimal hyperdrive_rating;
    private Integer MGLT;
    private String starship_class;
    private Date created;
    private Date edited;
    private String url;
    private String[] pilots;
    private String[] films;

    public Starship() {}
    public Starship(StarshipEntity ent){
    	this.starshipId=ent.getStarshipId();
    	this.name=ent.getName();
    	this.model=ent.getModel();
    	this.manufacturer=ent.getManufacturer();
    	this.cost_in_credits=ent.getCostInCredits();
    	this.length=ent.getLength();
    	this.max_atmosphering_speed=ent.getMaxAtmospheringSpeed();
    	this.crew=ent.getCrew();
    	this.passengers=ent.getPassengers();
    	this.cargo_capacity=ent.getCargoCapacity();
    	this.consumables=ent.getConsumables();
    	this.hyperdrive_rating=ent.getHiperdriverRating();
    	this.MGLT=ent.getMglt();
    	this.starship_class=ent.getStarshipClass();
    	this.created=ent.getCreated();
    	this.edited=ent.getEdited();
    	this.url=ent.getUrl();
    }


}
