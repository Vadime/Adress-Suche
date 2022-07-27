package de.novi;

import javax.print.Doc;

import de.novi.database.Collection;
import de.novi.database.Document;

public class AdressDatabase extends Collection {
        
        // keys of the Documents for referering to them when searching
        // may implement custom Classes for each collection so that
        // i dont have to use keys, also gives me the chance on code complection
        public static final String COUNTRIES = "countries";
        public static final String PROVINCES = "provinces";
        public static final String CITIES = "cities";
        public static final String POSTALCODES = "postalCodes";
        public static final String STREETS = "streets";
        public static final String HOUSES = "houses";

        // make the class a singleton so that
        // i dont have to initialize the class all the time
        public static final AdressDatabase instance = new AdressDatabase();

        // populate all the Data manualy for now
        // later may use a geolocation service of telekom, idk
        private AdressDatabase() {
                super(COUNTRIES);
                // populate Database
                add(new Document("Deutschland")
                                .add(new Collection(PROVINCES).add(new Document("Thüringen")
                                                .add(new Collection(CITIES).add(
                                                                new Document("Apolda")
                                                                                .add(new Collection(POSTALCODES).add(
                                                                                                new Document("99510")
                                                                                                                .add(new Collection(
                                                                                                                                STREETS)
                                                                                                                                .add(
                                                                                                                                                new Document("Fichtestraße")
                                                                                                                                                                .add(new Collection(
                                                                                                                                                                                HOUSES)
                                                                                                                                                                                .add(
                                                                                                                                                                                                new Document("33a"))))))))))
                                                .add(new Document("Hessen").add(new Collection(CITIES).add(
                                                                new Document("Frankfurt am Main")
                                                                                .add(new Collection(POSTALCODES).add(
                                                                                                new Document("60486")
                                                                                                                .add(new Collection(
                                                                                                                                STREETS)
                                                                                                                                .add(
                                                                                                                                                new Document("Theodor-Heuss-Allee")
                                                                                                                                                                .add(new Collection(
                                                                                                                                                                                HOUSES)
                                                                                                                                                                                .add(

                                                                                                                                                                                                new Document("1"))))))))))

                                ));
        }

}
