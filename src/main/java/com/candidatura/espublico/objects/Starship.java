package com.candidatura.espublico.objects;

import com.candidatura.espublico.entities.StarshipEntity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Starship {

    private int starshipId;
    private String name;
    private String model;
    private String manufacturer;
    private String costInCredits;
    private int length;
    private int maxAtmospheringSpeed;
    private int minCrew;
    private int maxCrew;
    private int passengers;
    private long cargoCapacity;
    private String consumables;
    private BigDecimal hiperdriverRating;
    private int mglt;
    private String starshipClass;
    private Date created;
    private Date edited;
    private String url;

    public Starship() {}
    public Starship(StarshipEntity ent){
    	this.starshipId=ent.getStarshipId();
    	this.name=ent.getName();
    	this.model=ent.getModel();
    	this.manufacturer=ent.getManufacturer();
    	this.costInCredits=ent.getCostInCredits();
    	this.length=ent.getLength();
    	this.maxAtmospheringSpeed=ent.getMaxAtmospheringSpeed();
    	this.minCrew=ent.getMinCrew();
    	this.maxCrew=ent.getMaxCrew();
    	this.passengers=ent.getPassengers();
    	this.cargoCapacity=ent.getCargoCapacity();
    	this.consumables=ent.getConsumables();
    	this.hiperdriverRating=ent.getHiperdriverRating();
    	this.mglt=ent.getMglt();
    	this.starshipClass=ent.getStarshipClass();
    	this.created=ent.getCreated();
    	this.edited=ent.getEdited();
    	this.url=ent.getUrl();
    }


}
