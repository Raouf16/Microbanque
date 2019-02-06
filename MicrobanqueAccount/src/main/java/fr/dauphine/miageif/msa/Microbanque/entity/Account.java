package fr.dauphine.miageif.msa.Microbanque.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Account implements Serializable {
    @Id
    private int id;
    private String iban;
    private String type;
    private float interet;
    private String frais_tenu_compte;

    public Account(){}

    public Account(int id) {
        this.id = id;
    }

    public Account(int id, String iban, String type, float interet, String frais_tenu_compte) {
        this.id = id;
        this.iban = iban;
        this.type = type;
        this.interet = interet;
        this.frais_tenu_compte = frais_tenu_compte;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getInteret() {
        return interet;
    }

    public void setInteret(float interet) {
        this.interet = interet;
    }

    public String getFrais_tenu_compte() {
        return frais_tenu_compte;
    }

    public void setFrais_tenu_compte(String frais_tenu_compte) {
        this.frais_tenu_compte = frais_tenu_compte;
    }

}
