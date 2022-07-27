package de.novi.database;

import java.util.LinkedList;
import java.util.List;

public class Collection {
    public final String uid;
    public final List<Document> docs;

    public Collection(String uid) {
        this.uid = uid;
        this.docs = new LinkedList<>();
    }

    public Collection add(Document doc) {
        docs.add(doc);
        return this;
    }

    public Collection remove(Document doc) {
        docs.remove(doc);
        return this;
    }

}