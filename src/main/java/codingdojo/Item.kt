package codingdojo

import codingdojo.ModelObject
import codingdojo.Cart
import codingdojo.DeliveryInformation
import codingdojo.LocationService
import codingdojo.StoreEvent
import java.lang.UnsupportedOperationException

/**
 * Items are for sale in a Store (or the central warehouse) and can be put in a Cart
 */
open class Item(
    var name: String, var type: String, // in grams
    var weight: Long
) : ModelObject {

    override fun toString(): String {
        return "Item{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                '}'
    }

    override fun saveToDatabase() {
        throw UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test")
    }
}