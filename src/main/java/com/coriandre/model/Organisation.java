package com.coriandre.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Organisation {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String address;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy="organisation")
    private List<Event> events;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
}

