package com.example.franc.myapplication;

public class Food {

    private String name;

    public String getName() {
        return name;
    }

    public String getPrezzo() {
        return prezzo;
    }

    private String prezzo;

    private String quantity;

    public String getQuantity() {
        return quantity;
    }

    public Food (String name, String prezzo, String quantity){
        this.name = name;
        this.quantity = quantity;
        this.prezzo = prezzo;
    }
}
