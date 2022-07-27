package de.novi;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */

    Map<String, Map<String, String>> populateTestData() {
        Map<String, Map<String, String>> validAdressMaps = new HashMap<>();
        validAdressMaps.put("Deutschland Thüringen Apolda 99510 Fichtestraße 33a", new HashMap<String, String>() {
            {
                put(AdressDatabase.COUNTRIES, "Deutschland");
                put(AdressDatabase.PROVINCES, "Thüringen");
                put(AdressDatabase.CITIES, "Apolda");
                put(AdressDatabase.POSTALCODES, "99510");
                put(AdressDatabase.STREETS, "Fichtestraße");
                put(AdressDatabase.HOUSES, "33a");
            }
        });
        validAdressMaps.put("Deutschland Hessen Frankfurt am Main 60486 Theodor-Heuss-Allee 1",
                new HashMap<String, String>() {
                    {
                        put(AdressDatabase.COUNTRIES, "Deutschland");
                        put(AdressDatabase.PROVINCES, "Hessen");
                        put(AdressDatabase.CITIES, "Frankfurt am Main");
                        put(AdressDatabase.POSTALCODES, "60486");
                        put(AdressDatabase.STREETS, "Theodor-Heuss-Allee");
                        put(AdressDatabase.HOUSES, "1");
                    }
                });
        // validAdressMaps.put("Frankfurt am Main 60486 Theodor-Heuss-Allee 1",
        // new HashMap<String, String>() {
        // {
        // put(AdressDatabase.COUNTRIES, "Deutschland");
        // put(AdressDatabase.PROVINCES, "Hessen");
        // put(AdressDatabase.CITIES, "Frankfurt am Main");
        // put(AdressDatabase.POSTALCODES, "60486");
        // put(AdressDatabase.STREETS, "Theodor-Heuss-Allee");
        // put(AdressDatabase.HOUSES, "1");
        // }
        // });
        // validAdressMaps.put("Apolda 99510 Fichtestraße 33a",
        // new HashMap<String, String>() {
        // {
        // put(AdressDatabase.COUNTRIES, "Deutschland");
        // put(AdressDatabase.PROVINCES, "Thüringen");
        // put(AdressDatabase.CITIES, "Apolda");
        // put(AdressDatabase.POSTALCODES, "99510");
        // put(AdressDatabase.STREETS, "Fichtestraße");
        // put(AdressDatabase.HOUSES, "33a");
        // }
        // });
        // validAdressMaps.put("60486 Theodor-Heuss-Allee 1",
        // new HashMap<String, String>() {
        // {
        // put(AdressDatabase.COUNTRIES, "Deutschland");
        // put(AdressDatabase.PROVINCES, "Hessen");
        // put(AdressDatabase.CITIES, "Frankfurt am Main");
        // put(AdressDatabase.POSTALCODES, "60486");
        // put(AdressDatabase.STREETS, "Theodor-Heuss-Allee");
        // put(AdressDatabase.HOUSES, "1");
        // }
        // });
        // validAdressMaps.put("99510 Fichtestraße 33a",
        // new HashMap<String, String>() {
        // {
        // put(AdressDatabase.COUNTRIES, "Deutschland");
        // put(AdressDatabase.PROVINCES, "Thüringen");
        // put(AdressDatabase.CITIES, "Apolda");
        // put(AdressDatabase.POSTALCODES, "99510");
        // put(AdressDatabase.STREETS, "Fichtestraße");
        // put(AdressDatabase.HOUSES, "33a");
        // }
        // });
        // validAdressMaps.put("Theodor-Heuss-Allee 1",
        // new HashMap<String, String>() {
        // {
        // put(AdressDatabase.COUNTRIES, "Deutschland");
        // put(AdressDatabase.PROVINCES, "Hessen");
        // put(AdressDatabase.CITIES, "Frankfurt am Main");
        // put(AdressDatabase.POSTALCODES, "60486");
        // put(AdressDatabase.STREETS, "Theodor-Heuss-Allee");
        // put(AdressDatabase.HOUSES, "1");
        // }
        // });
        // validAdressMaps.put("Fichtestraße 33a",
        // new HashMap<String, String>() {
        // {
        // put(AdressDatabase.COUNTRIES, "Deutschland");
        // put(AdressDatabase.PROVINCES, "Thüringen");
        // put(AdressDatabase.CITIES, "Apolda");
        // put(AdressDatabase.POSTALCODES, "99510");
        // put(AdressDatabase.STREETS, "Fichtestraße");
        // put(AdressDatabase.HOUSES, "33a");
        // }
        // });
        // validAdressMaps.put("1",
        // new HashMap<String, String>() {
        // {
        // put(AdressDatabase.COUNTRIES, "Deutschland");
        // put(AdressDatabase.PROVINCES, "Hessen");
        // put(AdressDatabase.CITIES, "Frankfurt am Main");
        // put(AdressDatabase.POSTALCODES, "60486");
        // put(AdressDatabase.STREETS, "Theodor-Heuss-Allee");
        // put(AdressDatabase.HOUSES, "1");
        // }
        // });
        // validAdressMaps.put("33a",
        // new HashMap<String, String>() {
        // {
        // put(AdressDatabase.COUNTRIES, "Deutschland");
        // put(AdressDatabase.PROVINCES, "Thüringen");
        // put(AdressDatabase.CITIES, "Apolda");
        // put(AdressDatabase.POSTALCODES, "99510");
        // put(AdressDatabase.STREETS, "Fichtestraße");
        // put(AdressDatabase.HOUSES, "33a");
        // }
        // });
        // validAdressMaps.put("Fichtestraße 33 a",
        // new HashMap<String, String>() {
        // {
        // put(AdressDatabase.COUNTRIES, "Deutschland");
        // put(AdressDatabase.PROVINCES, "Thüringen");
        // put(AdressDatabase.CITIES, "Apolda");
        // put(AdressDatabase.POSTALCODES, "99510");
        // put(AdressDatabase.STREETS, "Fichtestraße");
        // put(AdressDatabase.HOUSES, "33a");
        // }
        // });
        // validAdressMaps.put("99510 Fichtestraße 33a",
        // new HashMap<String, String>() {
        // {
        // put(AdressDatabase.COUNTRIES, "Deutschland");
        // put(AdressDatabase.PROVINCES, "Thüringen");
        // put(AdressDatabase.CITIES, "Apolda");
        // put(AdressDatabase.POSTALCODES, "99510");
        // put(AdressDatabase.STREETS, "Fichtestraße");
        // put(AdressDatabase.HOUSES, "33a");
        // }
        // });
        return validAdressMaps;
    }

    @Test
    public void sortAdressTest() {
        Map<String, Map<String, String>> testData = populateTestData();
        Iterator<String> i = testData.keySet().iterator();
        while (i.hasNext()) {
            String str = i.next();
            Map<String, String> adress = Adresszeilenumformer.resolveAdress(str);
            assertEquals(testData.get(str), adress);
        }
    }

}
