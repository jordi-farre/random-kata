package codingdojo

import codingdojo.ModelObject
import codingdojo.Cart
import codingdojo.DeliveryInformation
import codingdojo.LocationService
import codingdojo.StoreEvent

/**
 * Ticket to In-store event, eg makeover, eyeshadow masterclass
 * or beauty product launch evening reception
 */
class StoreEvent(name: String, location: Store?) : Item(name, "EVENT", 0) {
    protected var location: Store? = null
    fun setLocation1(locationStore: Store?) {
        location = locationStore
        location!!.addStoreEvent(this)
    }

    override fun toString(): String {
        return "StoreEvent{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}'
    }

    init {
        setLocation1(location)
    }
}