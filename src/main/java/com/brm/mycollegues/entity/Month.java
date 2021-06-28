package com.brm.mycollegues.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Month implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "month_number")
    private int monthNumber;
    @Column(name = "year_number")
    private int yearNumber;
    @Column(name = "worked_time")
    private Long worked_time;
}
