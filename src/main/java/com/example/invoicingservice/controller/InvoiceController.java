package com.example.invoicingservice.controller;

import com.example.invoicingservice.model.Invoice;
import com.example.invoicingservice.model.InvoiceItem;
import com.example.invoicingservice.model.Item;
import com.example.invoicingservice.model.UpdateItem;
import com.example.invoicingservice.repository.InvoiceRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private InvoiceRepository repository;

    public InvoiceController(InvoiceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Invoice getByOrderId(@PathVariable int id) {
        Invoice inv = repository.getById(id);
        if (inv != null) {
            return inv;
        } else {
            throw new IllegalStateException("invoice with this id was not found.");
        }
    }

    @GetMapping
    public List<Invoice> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public int create(@Valid @RequestBody Invoice invoice) {
        return repository.create(invoice);
    }

    @PutMapping("/{id}")
    public void update(@Valid @RequestBody UpdateItem itemDetails, @PathVariable int id) {
        repository.update(itemDetails, id);
    }

}
