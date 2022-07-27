package de.novi;

public class Regex {
    
    public static void main(String[] args) {
        String sprache = "[bc]*(ab)*[bc]*";
        while (true) {
            String wort = System.console().readLine("Wort aus der der Sprache \"" + sprache + "\" testen: ");
            if (wort.contains("exit")) {
                break;
            }
            if (wort.matches(sprache)) {
                System.out.println("\"" + wort + "\" liegt in der Sprache.");
            } else {
                System.out.println("\"" + wort + "\" liegt nicht in der Sprache.");
            }
        }
        System.out.println("Program gestoppt");
    }

}
