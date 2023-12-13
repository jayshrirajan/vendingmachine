package com.example.vmApp


import com.example.vmApp.model.Item
import com.example.vmApp.repository.ItemRepository
import com.example.vmApp.service.VendingMachineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@SpringBootTest
class vmAppTest extends Specification {

    @Autowired
    def itemRepository = Mock(ItemRepository)

    @Subject
    VendingMachineService vendingMachineService = new VendingMachineService(itemRepository)


//    def "addChange"() {
//        given:
//        def VendingMachineService = new VendingMachineService()
//        def initialChange = VendingMachineService.getTotalChange()
//
//        when:
//        VendingMachineService.addChange(10.0)
//
//        then:
//        def updatedChange = VendingMachineService.getTotalChange()
//        updatedChange == initialChange + 10.0
//        //System.out.println("InitialChange : " + initialChange)
//       // System.out.println("After adding the changeAmount : " + updatedChange)
//
//
//    }
    def "should return zero total change initially"() {
        when:
        double result = vendingMachineService.getTotalChange()

        then:
        result == 0.0
    }


    @Unroll
    def "addChange method with denominations"() {
        when:
        def result = new VendingMachineService().addChange(denominations)

        then:
        result == expectedTotal

        where: "The denominations and expected total are defined in a table"
        denominations                         | expectedTotal
        [(1 as double): 5,(2 as double): 5]   | 15.0

    }



    def "getTotalItems"() {
        given:
        def VendingMachineService = new VendingMachineService()

        when:
        int totalItems = VendingMachineService.getTotalItems()

        then:
        totalItems == 0
        //System.out.println("GetTotalItems :" + totalItems);
    }


    def "initialize item"() {
        given:
        def idItem = 1
        def itemName = "Soda"
        def itemCost = 2.0.doubleValue()
        def itemCode = "A"
        def quantity = 5


        when:
        def item = new Item(itemCode: "A", itemName: "Soda", itemCost: 2.0, idItem: 1, quantity: 5)

        then:
        item.idItem == idItem
        item.itemName == itemName
        item.itemCost == itemCost
        item.itemCode == itemCode
        item.quantity == quantity
    }

    def "should throw ItemOutOfStockException when item is out of stock"() {
        given:
        def itemRepository = Mock(ItemRepository)
        itemRepository.findById(1) >> Optional.of(new Item(idItem: 1, quantity: 0, itemCost: 2.0))
        def vendingMachineService = new VendingMachineService(itemRepository)

        when:
        def result = vendingMachineService.vendItem(1, 10.0, 1)

        then:
        result == "Selected Item is out of Stock in the Vending Machine."
    }

    def "vendItem should handle ItemNotFoundException"() {
        given:
        VendingMachineService vendingMachine = new VendingMachineService(itemRepository)
        int itemId = 12
        double amount = 3.0
        int quantity = 1

        when:
        def result = { vendingMachine.vendItem(itemId, amount, quantity) }

        then:
        result() == "The selected item is not available in the vending machine."
    }

    def "should throw Insufficient Balance exception when amount is less than required for #quantity item(s)"() {
        given:
        def itemRepository = Mock(ItemRepository)
        itemRepository.findById(1) >> Optional.of(new Item(idItem: 1, quantity: 5, itemCost: 2.0))
        def vendingMachineService = new VendingMachineService(itemRepository)

        when:
        def result = vendingMachineService.vendItem(1, amount, quantity)

        then:
        result == "Insufficient balance to purchase the item."

        where:
        amount | quantity
        2.0    | 5

    }


    def "Item Vended Successfully"() {
        given:
        def itemRepository = Mock(ItemRepository)
        itemRepository.findById(1) >> Optional.of(new Item(idItem: 1, quantity: 1, itemCost: 2.0, itemName: "Soda"))
        def vendingMachineService = new VendingMachineService(itemRepository)

        when:
        def result = vendingMachineService.vendItem(1, amount, quantity)

        then:
        result == "Enjoy your Soda!!! Your change: 0.0"

        where:
        amount | quantity | name
        2.0    | 1        | "Soda"

    }


}











































