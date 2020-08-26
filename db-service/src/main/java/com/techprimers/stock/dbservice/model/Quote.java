package com.techprimers.stock.dbservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name",columnDefinition = "varchar(50) default 'anonymous'")
    private String userName;

    @NotEmpty
    @Column(name = "quote")
    private String quoteStr;

    public Quote(String userName, String quoteStr) {
        this.userName = userName;
        this.quoteStr = quoteStr;
    }
}
