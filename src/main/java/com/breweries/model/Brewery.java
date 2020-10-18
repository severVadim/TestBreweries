package com.breweries.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Brewery {
    private Long id;
    private String name;
    @JsonProperty(value = "brewery_type")
    private String  breweryType;
    private String street;
    private String city;
    private String state;
    @JsonProperty(value = "postal_code")
    private String postalCode;
    private String country;
    private String longitude;
    private String latitude;
    private String phone;
    @JsonProperty(value = "website_url")
    private String websiteUrl;
    @JsonProperty(value = "updated_at")
    private String updatedAt;
}
