package codingdojo

import codingdojo.ModelObject
import codingdojo.Cart
import codingdojo.DeliveryInformation
import codingdojo.LocationService
import codingdojo.StoreEvent
import java.lang.UnsupportedOperationException
import java.util.ArrayList

/**
 * While shopping online in a Store, the Cart stores the Items you intend to buy
 */
class Cart : ModelObject {
    var items = ArrayList<Item>()
    var unavailableItems = ArrayList<Item>()
    fun getItems(): List<Item> {
        return items
    }

    fun addItem(item: Item) {
        items.add(item)
    }

    fun addItems(items: Collection<Item>) {
        this.items.addAll(items)
    }

    fun markAsUnavailable(item: Item) {
        unavailableItems.add(item)
    }

    fun weight(): Long {
        var weight = 0L
        for (item in this.items) {
            weight += item.weight
        }
        for (item in this.unavailableItems) {
            weight -= item.weight
        }
        return weight
    }

    override fun toString(): String {
        return "Cart{" +
                "items=" + displayItems(items) +
                "unavailable=" + displayItems(unavailableItems) +
                '}'
    }

    private fun displayItems(items: List<Item>): String {
        val itemDisplay = StringBuffer("\n")
        for (item in items) {
            itemDisplay.append(item.toString())
            itemDisplay.append("\n")
        }
        return itemDisplay.toString()
    }

    override fun saveToDatabase() {
        throw UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test")
    }

    fun getUnavailableItems(): Collection<Item> {
        return unavailableItems
    }
}