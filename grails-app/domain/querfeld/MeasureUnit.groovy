package querfeld

class MeasureUnit {

    String name
    String shortname
    String description

    static constraints = {
        name blank: false
        shortname blank: false
    }

    String toString() {
        shortname
    }
}
