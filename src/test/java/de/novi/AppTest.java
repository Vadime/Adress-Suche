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

        Map<String, String> populateTestData() {
                Map<String, String> validAdressMaps = new HashMap<>();
                validAdressMaps.put("33a/Deutschland%Fichtestraße Thüringen Apolda;99510",
                                "Deutschland, Thüringen, Apolda, 99510, Fichtestraße, 33a");

                validAdressMaps.put("Fichtestraße 33 a",
                                "Deutschland, Thüringen, Apolda, 99510, Fichtestraße, 33a");

                validAdressMaps.put("Frankfurt am Main Theodor-Heuss-Allee, 1",
                                "Deutschland, Hessen, Frankfurt am Main, 60486, Theodor-Heuss-Allee, 1");

                validAdressMaps.put("Apolda Fichtestraße 33",
                                "Deutschland, Thüringen, Apolda, 99510, Fichtestraße, null");

                return validAdressMaps;
        }

        @Test
        public void sortAdressTest() {
                Map<String, String> testData = populateTestData();
                Iterator<String> i = testData.keySet().iterator();
                while (i.hasNext()) {
                        long startTime = System.currentTimeMillis();
                        String str = i.next();
                        Adress adress = null;
                        adress = Adresszeilenumformer.toAdress(str)[0];
                        assertEquals(testData.get(str), adress.toString());
                        long endTime = System.currentTimeMillis();
                        System.out.println(testData.get(str) + " ; " + adress.toString() + " ; berechnet in "
                                        + (endTime - startTime) + " milisekunden");
                }
        }

}
