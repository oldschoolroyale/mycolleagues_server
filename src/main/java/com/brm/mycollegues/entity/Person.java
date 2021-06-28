package com.brm.mycollegues.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
public class Person implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String name;
    private String avatar;
    private Boolean is_online;
    private Long work_start;
    private Long last_visit;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "month_id", referencedColumnName = "id")
    private Month month;
}