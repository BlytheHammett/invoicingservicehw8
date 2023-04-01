package com.example.invoicingservice.repository;

import com.example.invoicingservice.model.Invoice;
import com.example.invoicingservice.model.InvoiceItem;
import com.example.invoicingservice.model.Item;
import com.example.invoicingservice.model.UpdateItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvoiceRepository {

    List<Invoice> invoices = new ArrayList<>();

    public List<Invoice> findAll() {
        return invoices;
    }

    public Invoice getById(int id) {
        Invoice inv = invoices.stream().filter(x -> x.getOrderId() == id).findAny().orElse(null);
        return inv;
    }

    public int create(Invoice invoice) {
        int id = invoices.size() + 1;
        invoice.setOrderId(id);
        invoices.add(invoice);
        return id;
    }

    public void update(UpdateItem itemDetails, int id) {
        Invoice invoice = getById(id);
        List<InvoiceItem> invoiceItems = invoice.getInvoiceItem();
        InvoiceItem items = invoiceItems.get(0);
        items.getItems().get(itemDetails.getItemId() - 1).setShippingStatus(itemDetails.getStatus());
    }

}
