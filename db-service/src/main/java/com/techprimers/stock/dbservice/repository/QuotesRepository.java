package com.techprimers.stock.dbservice.repository;

import com.techprimers.stock.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {

    List<Quote> findByUserName(String username);

    @Query("select q.userName from Quote q")
    List<String> getAllUsers();
}
