package com.WilsonQdop.Chamadas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends Person {
    @OneToMany(mappedBy = "customer")
    List<Called> calleds;

    public Customer () {
        calleds = new ArrayList<>();
    }

    public List<Called> getCalleds() {
        return calleds;
    }

    public void setCalleds(List<Called> calleds) {
        this.calleds = calleds;
    }
}
