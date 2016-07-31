package querfeld

class Location {

    String city

    double latitude
    double longitude

    String toString() { "$city" }

    static constraints = {
        city blank: false
        latitude min: -90d, max: 90d
        longitude()
    }
}
