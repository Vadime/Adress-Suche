package de.novi.database;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Document {

    public final List<Collection> collections;
    public final Map<String, Object> data;
    public final String uid;

    public Document(String uid) {
        this.uid = uid;
        this.collections = new LinkedList<>();
        this.data = new HashMap<>();
    }

    public Document add(Collection collection) {
        collections.add(collection);
        return this;
    }

    public Document remove(Collection collection) {
        collections.remove(collection);
        return this;

    }

    public void add(String name, Object doc) {
        data.put(name, doc);
    }

    public void remove(String doc) {
        data.remove(doc);
    }

    public Collection collection(String name) {
        for (Collection col : collections) {
            if (col.uid.equals(name)) {
                return col;
            }
        }
        return null;
    }


}
