package com.manavs.productMicroservice.models.db_models;

import javax.persistence.*;

@Entity
public class QueueMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
