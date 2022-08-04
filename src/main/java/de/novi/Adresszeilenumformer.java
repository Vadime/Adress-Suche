package de.novi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

    public static void findDocumentInCollectionWithAdressToken(
            int iteration,
            String[] collectionUids,
            Map<String, Document> sortedAdress,
            List<String> unsortedAdress,
            List<Document> prevList) {
        if (iteration >= collectionUids.length - 1)
            return;

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
                    for (int i = 0; i < iteration; i++) {

                    }

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

    public static Adress[] toAdress(String input) {

        List<String> unsortedAdress = splitAdressString(input);

        String[] colletionUids = { AdressDatabase.COUNTRIES, AdressDatabase.PROVINCES, AdressDatabase.CITIES,
                AdressDatabase.POSTALCODES,
                AdressDatabase.STREETS, AdressDatabase.HOUSES };

        Map<String, Document> map = new HashMap<>();

        for (int i = 0; i < 5; i++) {
            findDocumentInCollectionWithAdressToken(0, colletionUids, map, unsortedAdress,
                    AdressDatabase.instance.docs);
            unsortedAdress = new LinkedList<>();
            for (int j = 0; j < map.size(); j++) {
                unsortedAdress.add(((Entry<String, Document>) (map.entrySet().toArray()[j])).getValue().uid);
            }
        }

        Adress[] adress = new Adress[1];

        for (int i = 0; i < adress.length; i++) {
            Document country = map.get(AdressDatabase.COUNTRIES);
            String countryName = null;
            if (country != null)
                countryName = country.uid;

            Document province = map.get(AdressDatabase.PROVINCES);
            String provinceName = null;
            if (province != null)
                provinceName = province.uid;

            Document city = map.get(AdressDatabase.CITIES);
            String cityName = null;
            if (city != null)
                cityName = city.uid;

            Document postalCode = map.get(AdressDatabase.POSTALCODES);
            String postalCodeName = null;
            if (postalCode != null)
                postalCodeName = postalCode.uid;

            Document street = map.get(AdressDatabase.STREETS);
            String streetName = null;
            if (street != null)
                streetName = street.uid;

            Document house = map.get(AdressDatabase.HOUSES);
            String houseName = null;
            if (house != null)
                houseName = house.uid;

            adress[i] = new Adress(countryName, provinceName,
                    cityName, postalCodeName, streetName, houseName);

        }
        return adress;

    }

    private static boolean foundTocken(String token, List<String> unsortedAdress) {
        for (int i = 0; i < unsortedAdress.size(); i++) { // loop through every token in adress
            StringBuilder completeToken = new StringBuilder();
            for (int j = i; j < unsortedAdress.size(); j++) { // loop foreward to find tokens with more than one word
                completeToken.append(unsortedAdress.get(j) + " ");
                String str = completeToken.toString().trim();
                if (str.replace(" ", "").contains(token.replace(" ", ""))) {
                    unsortedAdress.remove(token); // token found in database -> delete token from adress
                    return true;
                }
            }
        }
        return false;
    }

}
