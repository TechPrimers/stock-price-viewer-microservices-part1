package com.techprimers.stock.dbservice.resource;

import com.techprimers.stock.dbservice.exception.QuoteNotFoundException;
import com.techprimers.stock.dbservice.model.Quote;
import com.techprimers.stock.dbservice.model.Quotes;
import com.techprimers.stock.dbservice.repository.QuotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
@RequiredArgsConstructor
public class DbServiceResource {

    private final QuotesRepository quotesRepository;

    @GetMapping("/")
    public List<String> getQuotes() {
        return quotesRepository.findAll()
                .stream()
                .map(Quote::getQuoteStr)
                .collect(Collectors.toList());
    }

    @GetMapping("/{username}")
    public List<String> getQuotesByUsername(@PathVariable("username") final String username) {
        return getQuotesByUserName(username);
    }

    @PostMapping("/add")
    public Quote add(@RequestBody final Quote quote) {
        return quotesRepository.save(quote);
    }

    @PostMapping("/add-quotes-by-username")
    public List<String> addQuotes(@RequestBody final Quotes quotes) {
        quotes.getQuoteList()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quotesRepository::save);

        return getQuotesByUserName(quotes.getUserName());
    }

    @PostMapping("/delete/{username}")
    public List<String> deleteByUsername(@PathVariable("username") final String username) {
        List<Quote> quotes = quotesRepository.findByUserName(username);
        quotesRepository.delete(quotes);

        return getQuotesByUserName(username);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable("id") final Integer id) {
        Quote quote = quotesRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException("Quote Not Found with id : " + id));
        quotesRepository.delete(quote);
        return true;
    }

    private List<String> getQuotesByUserName(String username) {
        List<String> allUsers = quotesRepository.getAllUsers();
        // return all quotes by username or empty list for no such username
        if (allUsers.contains(username))
            return quotesRepository.findByUserName(username)
                    .stream()
                    .map(Quote::getQuoteStr)
                    .collect(Collectors.toList());
        else
            return new ArrayList<>();
    }

}
