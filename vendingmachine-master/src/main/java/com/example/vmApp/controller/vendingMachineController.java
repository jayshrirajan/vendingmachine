package com.example.vmApp.controller;


import com.example.vmApp.exception.*;
//import com.example.vmApp.model.errorResponse;
import com.example.vmApp.model.item;
import com.example.vmApp.model.vendItemRequest;
import com.example.vmApp.service.vendingMachineService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/vending-machine")
public class vendingMachineController {

    //@Autowired
//    private vendingMachineService VendingMachineService;
    private final vendingMachineService vendingMachineService;

    @Autowired
    public vendingMachineController(vendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }


    @GetMapping("/total-items")
    @ApiOperation(value = "get total items", tags = {"GET TOTAL ITEMS"})
    public int getTotalItems() {
        List<item> item = vendingMachineService.getItem();
        return item.size();
    }

    @GetMapping("/get-item")
    @ApiOperation(value = "get items", tags = {"GET ITEMS"})
    public List<item> getItem() {
        List<item> item = vendingMachineService.getItem();
        return item;
    }

    @PostMapping("/initialize")
    @ApiOperation(value = "initialize items", tags = {"INITIALIZE ITEMS"})
    public void initializeVendingMachine(@RequestBody List<item> itemList) {
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
    public ResponseEntity<String> vendItems(@RequestBody List<vendItemRequest> vendItemRequests) throws itemOutOfStockException, insufficientBalanceException, itemNotFoundException {
        String result = vendingMachineService.vendItems(vendItemRequests);
        return ResponseEntity.ok(" " + result);
    }

//    @PutMapping("/vend-items")
//    @ApiOperation(value = "Vend items", tags = {"VEND ITEMS"})
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Successful operation", response = errorResponse.class),
//            @ApiResponse(code = 400, message = "Bad request", response = errorResponse.class),
//            @ApiResponse(code = 404, message = "Item not found", response = errorResponse.class),
//            @ApiResponse(code = 500, message = "Internal Server Error", response = errorResponse.class)
//    })
//    public ResponseEntity<errorResponse> vendItems(@RequestBody List<vendItemRequest> vendItemRequests) {
//        try {
//            String result = vendingMachineService.vendItems(vendItemRequests);
//            return ResponseEntity.ok(new errorResponse(result, HttpStatus.OK.value()));
//        } catch (itemOutOfStockException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new errorResponse("Insufficient balance or item out of stock", HttpStatus.BAD_REQUEST.value()));
//        } catch (itemNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new errorResponse("Item not found", HttpStatus.NOT_FOUND.value()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new errorResponse("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR.value()));
//        }
//    }
//
//    @ApiModel(description = "Request to vend items")
//    public static class VendItemRequest {
//        // Define your request model
//    }

}










