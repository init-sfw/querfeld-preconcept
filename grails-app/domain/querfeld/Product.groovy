package querfeld

class Product {

    String name
    String description
    BigDecimal suggestedPrice
    BigDecimal farmerPrice
    BigDecimal qty
    String thumbnailImage
    byte[] fullsizeImage

    ProductUgliness ugliness
    ProductType type
    MeasureUnit mUnit


    static constraints = {
        name blank: false
        name nullable: false
        description nullable: true
        description size:0..600
        suggestedPrice min: 0.1
        farmerPrice nullable: true
        ugliness nullable: true
        qty nullable: true
        type nullable: true
        mUnit nullable: true
        thumbnailImage nullable: true
        fullsizeImage nullable: true, maxSize: 2097152
    }

    String toString() {
        name
    }
}
