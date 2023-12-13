package com.example.vmApp.service;

import com.example.vmApp.exception.*;
import com.example.vmApp.model.Item;
import com.example.vmApp.model.VendItemRequest;
import com.example.vmApp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VendingMachineService {
    @Autowired
    ItemRepository itemRepository;

    private Map<String, Item> items = new HashMap<>();
    private final Map<String, Double> denominationValues = new HashMap<>();
    private double totalChange = 0.0;

    public void initializeVendingMachine(List<Item> itemList) {
        for (Item item : itemList) {
            itemRepository.save(item);
            items.put(item.getItemCode(), item);
        }
    }

    public int getTotalItems() {
        return items.size();
    }

    public double addChange(Map<Double, Integer> denominations) {
        for (Map.Entry<Double, Integer> entry : denominations.entrySet()) {
            double denomination = entry.getKey();
            int quantity = entry.getValue();
            totalChange += denomination * quantity;
        }
        return totalChange;
    }

    public double getTotalChange() {

        return totalChange;
    }

    public List<Item> getItem() {
        List<Item> item = itemRepository.findAll();
        return item;
    }

    public String vendItem(int itemId, double amount, int quantity) {

        if (itemRepository == null) {
            throw new IllegalStateException("Item repository is null");
        }

        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if (itemOptional.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        }

        Item item = itemOptional.get();

        if (amount < (item.getItemCost() * quantity)) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        if (item.getQuantity() <= 0) {
            throw new ItemOutOfStockException("Item out of stock");
        }


        double change = amount - (item.getItemCost() * quantity);
        if (totalChange < change) {
            throw new UnableToReturnBalanceException("Unable to return the balance");
        }

        item.setQuantity(item.getQuantity() - quantity);
        itemRepository.save(item);
        totalChange = totalChange + (item.getItemCost() * quantity);

        if (totalChange > change) {
            return "Enjoy your " + item.getItemName() + "!!!" + " Your change: " + change;
        } else {
            return "Item Vended";
        }
    }

    public String vendItems(List<VendItemRequest> vendItemRequests) {
        StringBuilder result = new StringBuilder();

        for (VendItemRequest vendItemRequest : vendItemRequests) {
            int itemId = vendItemRequest.getItemId();
            double amount = vendItemRequest.getAmount();
            int quantity = vendItemRequest.getQuantity();

            String itemResult = String.valueOf(vendItem(itemId, amount, quantity));
            result.append(itemResult).append("\n");
        }

        return result.toString();
    }

}
