package com.country.countrydetails;
/**
 This class is used to represent POJO for country and Standard rate. It is the custom object stored in the List.
 */
public class EUCountryData {
    private String country;
    private int standard_rate;
    private float reduced_rate_alt;
    private Boolean super_reduced_rate;
    private float parking_rate;

    public String getCountry() {
        return country;
    }

    public int getStandard_rate() {
        return standard_rate;
    }


}
