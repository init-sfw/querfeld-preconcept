package querfeld

class Location {

    String city
    String state

    // derived properties, set by a service
    double latitude
    double longitude

    String toString() { "$city,$state" }

    static constraints = {
        city blank: false
        state blank: false
        latitude min: -90d, max: 90d
        longitude()>
    }
}
