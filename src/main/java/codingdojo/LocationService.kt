package codingdojo

import codingdojo.ModelObject
import codingdojo.Cart
import codingdojo.DeliveryInformation
import codingdojo.LocationService
import codingdojo.StoreEvent
import java.lang.UnsupportedOperationException

/**
 * The LocationService can tell you if a delivery address is within delivery range
 * of a particular Store. This is a placeholder implementation.
 */
class LocationService : ModelObject {
    fun isWithinDeliveryRange(store: Store?, deliveryAddress: String?): Boolean {
        return "NEARBY" == deliveryAddress
    }

    override fun toString(): String {
        return "LocationService"
    }

    override fun saveToDatabase() {
        throw UnsupportedOperationException("missing from this exercise - shouldn't be called from a unit test")
    }
}