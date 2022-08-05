package de.novi;

/*
 * Commandline Application, to test adresses interactivly
 */
public class App {

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            System.console().printf("%s", "Unsortierte und unvollständige Adresszeile: ");
            String str = System.console().readLine();
            if (str.equals("exit"))
                run = false;
            Adress adress = Adresszeilenumformer.toAdress(str)[0];
            System.console().printf("%s\n", adress.toString());
        }
    }

}
