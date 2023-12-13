package com.example.vmApp.controller;


//import com.example.vmApp.model.errorResponse;
import com.example.vmApp.model.Item;
import com.example.vmApp.model.VendItemRequest;
import com.example.vmApp.service.VendingMachineService;
import io.swagger.annotations.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/vending-machine")
public class VendingMachineController {

    private final VendingMachineService vendingMachineService;

    @Autowired
    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @GetMapping("/total-items")
    @ApiOperation(value = "get total items", tags = {"GET TOTAL ITEMS"})
    public int getTotalItems() {
        List<Item> item = vendingMachineService.getItem();
        return item.size();
    }


    @GetMapping("/get-item")
    @ApiOperation(value = "get items", tags = {"GET ITEMS"})
    public List<Item> getItem() {
        List<Item> item = vendingMachineService.getItem();
        return item;
    }

    @PostMapping("/initialize")
    @ApiOperation(value = "initialize items", tags = {"INITIALIZE ITEMS"})
    public void initializeVendingMachine(@RequestBody List<Item> itemList) {
        vendingMachineService.initializeVendingMachine(itemList);
    }

    //    @PostMapping("/add-change/{changeAmount}")
//    @ApiOperation(value = "add change",tags = {"ADD CHANGE"})
//    public ResponseEntity<String> addChange(@PathVariable double changeAmount) {
//        double result = vendingMachineService.addChange(changeAmount);
//        return ResponseEntity.ok("Change added successfully!! \nCurrent total change : " + result);
//    }
//    @PutMapping("/vend-item/{itemId}/{amount}/{quantity}")
//    @ApiOperation(value = "vend item",tags = {"VEND ITEM"})
//    public ResponseEntity<String> vendItem(@PathVariable int itemId, @PathVariable double amount,@PathVariable int quantity) throws ItemOutOfStockException, InsufficientBalanceException, ItemNotFoundException {
//        String result = vendingMachineService.vendItem(itemId, amount,quantity);
//        return ResponseEntity.ok(" " +result);
//
//    }
    @PostMapping("/add-change")
    @ApiOperation(value = "Add change", tags = {"ADD CHANGE"})
    public ResponseEntity<String> addChange(@RequestBody Map<Double, Integer> denominations) {
        double result = vendingMachineService.addChange(denominations);
        return ResponseEntity.ok("Change added successfully!! \nCurrent total change : " + result);
    }

    @PutMapping("/vend-items")
    @ApiOperation(value = "Vend items", tags = {"VEND ITEMS"})
    public String vendItems(@RequestBody List<VendItemRequest> vendItemRequests) {
        return vendingMachineService.vendItems(vendItemRequests);
    }




}










