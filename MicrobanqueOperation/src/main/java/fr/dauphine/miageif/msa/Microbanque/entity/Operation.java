package fr.dauphine.miageif.msa.Microbanque.entity;

import fr.dauphine.miageif.msa.Microbanque.utils.OperationType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Operation implements Serializable {
    @Id
    private int id;

    private String type;

    private String iban_source;
    private String iban_destination;
    private double montant;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    public Operation(){}

    public Operation(int id, String type, String iban_source, String iban_destination, double montant, Date date) {
        this.id = id;
        this.type = type;
        this.iban_source = iban_source;
        this.iban_destination = iban_destination;
        this.montant = montant;
        this.date = date;
    }

    public Operation(int id, String type, String iban_source, double montant, Date date) {
        this.id = id;
        this.type = type;
        this.iban_source = iban_source;
        this.montant = montant;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIban_source() {
        return iban_source;
    }

    public void setIban_source(String iban_source) {
        this.iban_source = iban_source;
    }

    public String getIban_destination() {
        return iban_destination;
    }

    public void setIban_destination(String iban_destination) {
        this.iban_destination = iban_destination;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
