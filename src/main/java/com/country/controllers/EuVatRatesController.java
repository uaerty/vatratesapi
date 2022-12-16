package com.country.controllers;

import com.country.countrydetails.EUCountriesDataReader;
import com.country.countrydetails.EUCountryData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

/**
This class is based on REST API. Out of the CRUD operations, it performs READ operation as per the requirements as follows:

Get three EU Countries with Highest standard VAT rate
http://127.0.0.1:8080/euvatrates/highest

Get three EU Countries with Lowest standard VAT rate
http://127.0.0.1:8080/euvatrates/lowest

*/
@RestController
@RequestMapping("/euvatrates")
public class EuVatRatesController {
    /*Three lists are initialized where sortedrateslist stores the sorted EUCountryData objects,
    highrateslist stored the three EUCountryData objects with highest standard rates &
    lowrateslist stored the three EUCountryData objects with lowest standard rates*/
    List<EUCountryData> sortedrateslist,highrateslist,lowrateslist;
    //It is used to create the item with Country and Standard rate information
    JSONObject item;
    //It is used to create array of JSON items
    JSONArray array;
    @GetMapping(path="/highest")
    public String getHighestVatRates(){
        //It stores the JSON array with three EU countries that has highest standard rates and return to the method
        String highvatrates = "";
        array = new JSONArray();
        try {
            sortedrateslist = EUCountriesDataReader.getSortedVatRates();
            highrateslist =  sortedrateslist.subList(Math.max(sortedrateslist.size() - 3, 0), sortedrateslist.size());

            for(int i = 0; i < highrateslist.size(); i++){
                item = new JSONObject();
                item.put("country", highrateslist.get(i).getCountry());
                item.put("standard_rate", highrateslist.get(i).getStandard_rate());
                array.put(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        highvatrates = array.toString();
        return highvatrates;
    }

    @GetMapping(path="/lowest")
    public String getLowestVatRates(){
        //It stores the JSON array with three EU countries that has lowest standard rates and return to the method
        String lowrates = "";
        array = new JSONArray();
        try {
            sortedrateslist = EUCountriesDataReader.getSortedVatRates();
            lowrateslist = sortedrateslist.subList(0,3);
            for(int i = 0; i < lowrateslist.size(); i++){
                item = new JSONObject();
                item.put("country", lowrateslist.get(i).getCountry());
                item.put("standard_rate", lowrateslist.get(i).getStandard_rate());
                array.put(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lowrates = array.toString();
        return lowrates;
    }
}
