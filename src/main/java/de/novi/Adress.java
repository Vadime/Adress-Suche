package de.novi;

import java.util.HashMap;
import java.util.Map;

public class Adress {
    
    private String country;
    private String province;
    private String city;
    private String postalCode;
    private String street;
    private String house;

    /**
     * @param country
     * @param province
     * @param city
     * @param postalCode
     * @param street
     * @param house
     */
    public Adress(String country, String province, String city, String postalCode, String street, String house) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.house = house;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s", country, province, city, postalCode, street, house);
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>() ;
        map.put(AdressDatabase.COUNTRIES, country);
        map.put(AdressDatabase.PROVINCES, province);
        map.put(AdressDatabase.CITIES, city);
        map.put(AdressDatabase.POSTALCODES, postalCode);
        map.put(AdressDatabase.STREETS, street);
        map.put(AdressDatabase.HOUSES, house);
        return map;
    }

    

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the house
     */
    public String getHouse() {
        return house;
    }

    /**
     * @param house the house to set
     */
    public void setHouse(String house) {
        this.house = house;
    }
}
