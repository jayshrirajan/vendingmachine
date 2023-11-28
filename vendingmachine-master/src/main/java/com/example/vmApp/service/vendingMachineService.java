package com.example.vmApp.service;

import com.example.vmApp.exception.*;
import com.example.vmApp.model.item;
import com.example.vmApp.model.vendItemRequest;
import com.example.vmApp.repository.ItemRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class vendingMachineService {
    @Autowired
    ItemRepository itemRepository;


    public vendingMachineService(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;


    }
    public vendingMachineService() {
        denominationValues.put("Two", 2.0);

    }

    private Map<String, item> items = new HashMap<>();

    private final Map<String, Double> denominationValues = new HashMap<>();
    private double totalChange = 0.0;

    public void initializeVendingMachine(List<item> itemList) {
        for (com.example.vmApp.model.item item : itemList) {
            itemRepository.save(item);
            items.put(item.getItemCode(), item);
        }
    }

    public int getTotalItems() {

        return items.size();
    }

//    public double addChange(double change) {
//
//        totalChange += change;
//        return totalChange;
//    }
//
//
//    public double getTotalChange() {
//
//        return totalChange;
//    }



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

//    public List<item> getItem() {
//
//        List<item> item = itemRepository.findAll();
//        return item;
//
//
//    }
public List<item> getItem() {

    if (itemRepository != null) {
        throw new itemNotFoundException();
    }


    List<item> items = itemRepository.findAll();
    return items;
}


    public String vendItem(int itemId, double amount, int quantity) {
        if (itemRepository == null) {
            throw new IllegalStateException("Item repository is null");

        }

        Optional<item> itemOptional = itemRepository.findById(itemId);

        if ( itemOptional.isEmpty()) {
//            String msg = new itemNotFoundException().getMessage();
//            return msg;
            throw new itemNotFoundException();

        }


        item item = itemOptional.get();


        if (item.getQuantity() <= 0) {
            throw new itemOutOfStockException();
//            String msg = new itemOutOfStockException().getMessage();
//            return msg;
        }

        if (amount < (item.getItemCost() * quantity)) {
            throw new insufficientBalanceException();
//            String msg = new insufficientBalanceException().getMessage();
//            return msg;

        }

        double change = amount - (item.getItemCost() * quantity);
        if (totalChange < change) {
            throw new unableToReturnBalanceException();
//            String msg = new unableToReturnBalanceException().getMessage();
//            return msg;
        }

        item.setQuantity(item.getQuantity() - quantity);
        itemRepository.save(item);
        totalChange = totalChange + (item.getItemCost() * quantity);

        if (totalChange > change) {
            return "Enjoy your " + item.getItemName() + "!!!" + " Your change: " + change;
        } else {
            return "Item Vended...";

        }

    }
    public String vendItems(List<vendItemRequest> vendItemRequests) throws itemOutOfStockException, insufficientBalanceException, itemNotFoundException {
        StringBuilder result = new StringBuilder();

        for (vendItemRequest vendItemRequest : vendItemRequests) {
            int itemId = vendItemRequest.getItemId();
            double amount = vendItemRequest.getAmount();
            int quantity = vendItemRequest.getQuantity();

            try {
                String itemResult = vendItem(itemId, amount, quantity);
                result.append(itemResult).append("\n");
            } catch (itemOutOfStockException | insufficientBalanceException | itemNotFoundException e) {
                result.append(e.getMessage()).append("\n");
            }
        }

        return result.toString();

    }


}








