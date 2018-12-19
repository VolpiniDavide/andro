package com.example.franc.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

public class Food {

    public Food (String name, String prezzo, String quantity){
        this.name = name;
        this.quantity = quantity;
        this.prezzo = prezzo;
    }

    public Food (JSONObject jsonFood){
        try {
          name = jsonFood.getString("name");
          prezzo = jsonFood.getString("price");
          availability = jsonFood.getBoolean("available");
          quantity = "0";
        } catch (JSONException e){
            e.printStackTrace();
        }

    }


    private boolean availability;

    public boolean getAvailability(){
        return availability;
    }

    private String name;

    public String getName() {
        return name;
    }



    private String prezzo;

    public String getPrezzo() {
        return prezzo;
    }



    private String quantity;

    public String getQuantity() {
        return quantity;
    }


    public void increaseQuantity(){
        int a =Integer.parseInt(quantity);
        a++;
        quantity = String.valueOf(a);
    }
    public void decreaseQuantity(){
        int a =Integer.parseInt(quantity);
        if (a>0) {
            a--;
        }
        quantity = String.valueOf(a);
    }


}
