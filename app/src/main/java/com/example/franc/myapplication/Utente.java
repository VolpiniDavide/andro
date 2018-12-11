package com.example.franc.myapplication;

import java.util.ArrayList;

public class Utente {

    private String mail;
    private String password;
    private String numero;

    public Utente(){}

    public Utente ( String mail, String password, String numero ){
        this.mail=mail;
        this.numero=numero;
        this.password=password;
    }

    public final static ArrayList<Utente> utenti = new ArrayList<>();

    public static void aggiungiUtente(Utente u){
        utenti.add(u);
    }

    public static boolean trovaUtente (String mail) {
            int a = 0;
            for ( Utente i : utenti){
            if ( i.getMail()== mail){
                a=1;
            }
            else a=0;
        }
        if (a == 1)
            return true;
        else return false;

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
