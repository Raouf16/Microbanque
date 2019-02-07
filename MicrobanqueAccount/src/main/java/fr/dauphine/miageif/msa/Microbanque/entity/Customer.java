package fr.dauphine.miageif.msa.Microbanque.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

// Cette classe n'est pas utilisée, j'allais en faire un 3ème service "Customer" mais contrainte de temps ..
@Entity
public class Customer implements Serializable {
    @Id
    private int id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;

    public Customer(){}

    public Customer(int id, String firstname, String lastname, String phone, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
