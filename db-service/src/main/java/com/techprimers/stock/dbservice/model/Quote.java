package com.techprimers.stock.dbservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quotes", catalog = "test")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "quote")
    private String quoteStr;

    public Quote(String userName, String quoteStr) {
        this.userName = userName;
        this.quoteStr = quoteStr;
    }
}
