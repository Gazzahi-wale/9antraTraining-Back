package com.esprit.springjwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatefeedback() {
        return datefeedback;
    }

    public void setDatefeedback(Date datefeedback) {
        this.datefeedback = datefeedback;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String description ;
    private Date datefeedback ;

    public Feedback(Long id, String description, Date datefeedback) {
        this.id = id;
        this.description = description;
        this.datefeedback = datefeedback;
    }
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="Feedback")
    private Set<Motsinterdit> motsInterdits;
}
