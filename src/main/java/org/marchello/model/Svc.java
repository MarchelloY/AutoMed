package org.marchello.model;

import org.marchello.service.SvcService;
import java.io.IOException;

public class Svc {
    private String id;
    private String name;
    private String cost;

    public Svc(String name, String cost) throws IOException {
        this.id = SvcService.readIdFromFile();
        this.name = name;
        this.cost = cost;
    }

    Svc () {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
