package com.country.countrydetails;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

/**
 This class is the reader of EU Rates API. It uses Jackson library to retrieve and process the country and standard rate
 data as per the requirements
 */
public class EUCountriesDataReader {
    /*
     This method is used to return the rates list from the EU Rates API
     */
    public static ArrayList<EUCountryData>  getVatRates() throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(WRITE_DATES_AS_TIMESTAMPS)
                .enable(ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        //It retrieves the JSON data from EU Rates API
        Map<String,?> jsonData = mapper.readValue(new URL("https://euvatrates.com/rates.json"), Map.class);
        //It filters the rates JSON from JSON data
        Map<String,?> ratesData = (Map<String, ?>) jsonData.get("rates");
        //As per the requirement, we further need the values (not keys) to process the rates JSON. Therefore, ratesData.values()
        //has been called. Also, values rates JSON is stored as string using Jackson library.
        String respData = mapper.writeValueAsString(ratesData.values());
        //ArrayList with EUCountryData objects has been created. EUCountryData Object is required to retrieve Country and
       // Standard rate information. Here, TypeReference class is called using Jackson library. This step is required
        // otherwise casting LinkedHashMap error occurs
        ArrayList<EUCountryData> ratesList = mapper.readValue(respData, new TypeReference<ArrayList<EUCountryData>>() {});
        return ratesList;
    }
    /* This method is used to return the sorted rates list from the EU Rates API */
    public static ArrayList<EUCountryData>  getSortedVatRates() throws IOException {
        //Stream API has been used to sort the list created in getVatRates()
        ArrayList<EUCountryData> sortedratesList = (ArrayList<EUCountryData>) getVatRates().stream()
                .sorted((a,b)-> (a.getStandard_rate() - b.getStandard_rate()))
                .collect(Collectors.toList());
        return sortedratesList;

    }
}
