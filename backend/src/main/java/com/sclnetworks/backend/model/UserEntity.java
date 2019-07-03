package com.sclnetworks.backend.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonAutoDetect
@Table(name = "users", schema = "Link")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -5699759887677801214L;
    // 가입정보
    private int id;



    // 가입정보
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
