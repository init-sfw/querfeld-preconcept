package querfeld

class MeasureUnit {

    String name
    String shortName
    String description

    static constraints = {
        name blank: false
        shortName blank: false
    }

    String toString() {
        shortName
    }
}
