package querfeld

class Product {

    String name
    String description
    BigDecimal suggestedPrice
    BigDecimal farmerPrice
    BigDecimal qty
    String thumbnailImage
    String fullsizeImage

    ProductUgliness ugliness
    ProductType type
    MeasureUnit mUnit


    static constraints = {
        name blank: false
        name nullable: false
        suggestedPrice min: 0.1
        farmerPrice min: 0.0
        ugliness nullable: false
        qty min: 0.0
        ugliness nullable: false
        type nullable: false
        mUnit nullable: false
    }

    String toString() {
        name
    }
}
