package de.novi;

import java.util.Map;

public class App {

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.console().printf("%s", "Unsortierte und unvollständige Adresszeile: ");
            String str = System.console().readLine();
            if (str.equals("exit")) {
                run = false;
            }
            Map<String, String> adress = Adresszeilenumformer.resolveAdress(str);
            System.console().printf("%s\n", adress.toString());
        }
    }

}
