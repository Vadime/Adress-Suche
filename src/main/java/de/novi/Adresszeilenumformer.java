package de.novi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import de.novi.database.Document;

public class Adresszeilenumformer {

    public static List<String> splitAdressString(String input) {
        List<String> unsortedAdress = new LinkedList<>();

        for (String token : input.replaceAll("[,;:%&!§$()]", " ").split(" ")) {
            if (!token.isBlank()) {
                unsortedAdress.add(token);
            }
        }
        return unsortedAdress;
    }

    public static void findDocumentInCollectionWithAdressToken(int iteration, String[] collectionUids,
            Map<String, Document> sortedAdress, List<String> unsortedAdress,
            List<Document> prevList) {
        if (iteration >= collectionUids.length - 1) {
            return;
        }
        String uidOfCollection = collectionUids[iteration];
        String uidOfNextCollection = collectionUids[iteration + 1];

        List<Document> nextList = new LinkedList<>(); // provinzen
        boolean foundCountry = false;
        for (Document country : prevList) { // deutschland in Ländern
            if (foundTocken(country.uid, unsortedAdress)) { // wenn deutschland in Adresse steht
                foundCountry = true;
                sortedAdress.put(uidOfCollection, country);
                nextList.addAll(country.collection(uidOfNextCollection).docs); // nehme provinzen aus deutschland
                break;
            }
        }

        if (!foundCountry) { // keine Provinz im Land gefunden, deswegen suche in allen Ländern
            for (Document country : prevList) {
                nextList.addAll(country.collection(uidOfNextCollection).docs);
            }
        }

        boolean foundProvinz = false;

        // die provinzen sind ready
        for (Document province : nextList) { // thüringen in allen Provinzen
            if (foundTocken(province.uid, unsortedAdress)) { // thüringen steht in adresse
                foundProvinz = true;
                // provinz gefunden
                sortedAdress.put(uidOfNextCollection, province);

                if (!foundCountry) { // wenn land nicht gefunden wurde
                    // suche oben nach land aus der provinz

                    for (Document country : prevList) {
                        for (Document prov : country.collection(uidOfNextCollection).docs) {
                            if (province == prov) {
                                sortedAdress.put(uidOfCollection, country);
                            }
                        }
                    }

                }
                break;
            }
        }

        // if(!foundProvinz) {
        // wenn nicht provinz gefunden egal ob
        // land gefunden oder nicht -> 
        // einfach weiter machen und vielciht findest du
        // provinz in städten
        // }

        iteration++;
        findDocumentInCollectionWithAdressToken(iteration, collectionUids, sortedAdress, unsortedAdress,
                nextList);

    }

    public static Map<String, String> resolveAdress(String input) {

        List<String> unsortedAdress = splitAdressString(input);

        String[] colletionUids = { AdressDatabase.COUNTRIES, AdressDatabase.PROVINCES, AdressDatabase.CITIES,
                AdressDatabase.POSTALCODES,
                AdressDatabase.STREETS, AdressDatabase.HOUSES };

        Map<String, Document> map = new HashMap<>();

        findDocumentInCollectionWithAdressToken(0, colletionUids, map, unsortedAdress, AdressDatabase.instance.docs);

        Map<String, String> newMap = new HashMap<>();
        for (Entry<String, Document> en : map.entrySet()) {
            newMap.put(en.getKey(), en.getValue().uid);
        }
        return newMap;

        // List<Document> cityList;

        // if (adress.province != null) {
        // if (adress.country == null) {
        // // country aus Provinz finden
        // for (Document country : countryList) {
        // for (Document province : country.collection(AdressDatabase.PROVINCES).docs) {
        // if (adress.province == province) {
        // adress.country = country;
        // }
        // }
        // }
        // }
        // cityList = adress.province.collection(AdressDatabase.CITIES).docs;
        // } else {
        // cityList = new LinkedList<>();
        // for (Document province : provinceList) {
        // for (Document city : province.collection(AdressDatabase.CITIES).docs) {
        // cityList.add(city);
        // }
        // }
        // }

        // for (Document city : cityList) {
        // if (foundTocken(city.uid, unsortedAdress)) {
        // adress.city = city;
        // break;
        // }
        // }

        // if (adress.city != null) {
        // if (adress.province == null) {
        // // country aus Provinz finden
        // for (Document country : countryList) {
        // for (Document province : country.collection(AdressDatabase.PROVINCES).docs) {
        // for (Document city : province.collection(AdressDatabase.CITIES).docs) {
        // if (adress.city == city) {
        // adress.country = country;
        // adress.province = province;
        // }
        // }
        // }
        // }
        // }
        // cityList = adress.province.collection(AdressDatabase.CITIES).docs;
        // } else {
        // // country nicht gefunden also suche provincen überall
        // cityList = new LinkedList<>();
        // for (Document province : provinceList) {
        // for (Document city : province.collection(AdressDatabase.CITIES).docs) {
        // cityList.add(city);
        // }
        // }
        // }

        // for (Document city : cityList) {
        // if (foundTocken(city.uid, unsortedAdress)) {
        // adress.city = city;
        // break;
        // }
        // }

        // List<Document> postalCodeList;

        // if (adress.city != null) {
        // if (adress.province == null) {
        // // province aus Provinz finden
        // for (Document city : provinceList) {
        // for (Document postalCode : city.collection(AdressDatabase.CITIES).docs) {
        // if (adress.province == postalCode) {
        // adress.province = postalCode;
        // }
        // }
        // }
        // }
        // postalCodeList = adress.city.collection(AdressDatabase.POSTALCODES).docs;
        // } else {
        // postalCodeList = new LinkedList<>();
        // for (Document city : postalCodeList) {
        // for (Document postalCode : city.collection(AdressDatabase.POSTALCODES).docs)
        // {
        // postalCodeList.add(postalCode);
        // }
        // }
        // }

        // for (Document postalCode : postalCodeList) {
        // if (foundTocken(postalCode.uid, unsortedAdress)) {
        // adress.postalCode = postalCode;
        // break;
        // }
        // }

        // if (adress.postalCode != null) {
        // postalCodeList = adress.city.collection(AdressDatabase.POSTALCODES).docs;
        // } else {
        // // country nicht gefunden also suche provincen überall
        // postalCodeList = new LinkedList<>();
        // for (Document city : cityList) {
        // for (Document postalCode : city.collection(AdressDatabase.POSTALCODES).docs)
        // {
        // postalCodeList.add(postalCode);
        // }
        // }
        // }

        // List<Document> streetList =
        // adress.postalCode.collection(AdressDatabase.STREETS).docs;

        // for (Document street : streetList) {
        // if (foundTocken(street.uid, unsortedAdress)) {
        // adress.street = street;
        // break;
        // }
        // }

        // if (adress.street == null) {
        // // country nicht gefunden also suche provincen überall
        // }

        // List<Document> houseList =
        // adress.street.collection(AdressDatabase.HOUSES).docs;

        // for (Document house : houseList) {
        // if (foundTocken(house.uid, unsortedAdress)) {
        // adress.house = house;
        // break;
        // }
        // }

        // if (adress.house == null) {
        // // country nicht gefunden also suche provincen überall
        // }
    }

    private static String searchForToken(String token, List<String> unsortedAdress) {
        for (int i = 0; i < unsortedAdress.size(); i++) {
            StringBuilder completeToken = new StringBuilder();
            for (int j = i; j < unsortedAdress.size(); j++) {
                completeToken.append(unsortedAdress.get(j) + " ");
                String str = completeToken.toString().trim();
                if (str.contains(token)) {
                    unsortedAdress.remove(token);
                    return token;
                }
            }
        }
        return null;
    }

    private static boolean foundTocken(String token, List<String> unsortedAdress) {
        for (int i = 0; i < unsortedAdress.size(); i++) {
            StringBuilder completeToken = new StringBuilder();
            for (int j = i; j < unsortedAdress.size(); j++) {
                completeToken.append(unsortedAdress.get(j) + " ");
                String str = completeToken.toString().trim();
                if (str.contains(token)) {
                    unsortedAdress.remove(token);
                    return true;
                }
            }
        }
        return false;
    }

}
