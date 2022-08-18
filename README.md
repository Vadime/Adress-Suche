# Adress-Suche
Normalisierung und Vervollständigung einer Adresse
Konvertierung von Adressdaten in beliebige Zielformate.

Die Datenbank ist zu finden im resource directory als database.json.
## Adressen lokal Konvertieren
```
public static void main(String[] args) {
  Converter converter = new Converter();
  Addresse adresse = converter.convert(args[0]);
  System.out.println(adresse.toString());
}
```
## Adresse als server backaend umwandeln
```
public static void main(String[] args) {
  Server server = new Server();
  server.connect();
}
```
auf einem anderen Rechner die lokale IP Adresse des Servers aufrufen mit:
```
telnet [IP-Adresse] [Specified Port]
```
The specified port is set to 9991. 
