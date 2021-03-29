package codingdojo

import codingdojo.ModelObject
import codingdojo.Cart
import codingdojo.DeliveryInformation
import codingdojo.LocationService
import codingdojo.StoreEvent
import java.lang.UnsupportedOperationException
import java.util.HashMap

/**
 * Represents a physical Store where you can go and buy
 * products and attend events.
 */
class Store(private val name: String, private val droneDelivery: Boolean) : ModelObject {
    private val itemsInStock: MutableMap<String, Item> = HashMap()
    fun addStockedItems(vararg items: Item) {
        for (item in items) {
            itemsInStock[item.name] = item
        }
    }

    fun addStoreEvent(storeEvent: StoreEvent) {
        itemsInStock[storeEvent.name] = storeEvent
    }

    fun removeStockedItems(vararg items: Item) {
        for (item in items) {
            itemsInStock.remove(item.name)
        }
    }

    fun hasItem(item: Item): Boolean {
        return itemsInStock.containsKey(item.name)
    }

    fun getItem(name: String): Item? {
        return itemsInStock[name]
    }

    fun hasDroneDelivery(): Boolean {
        return droneDelivery
    }

    override fun toString(): String {
        return "Store{" +
                "name='" + name + "\', " +
                "droneDelivery=" + droneDelivery +
                '}'
    }

    override fun saveToDatabase() {
        throw UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test")
    }
}