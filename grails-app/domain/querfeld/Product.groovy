package querfeld

class Product {

    String description
    BigDecimal suggestedPrice
    BigDecimal farmerPrice
    BigDecimal qty
    BigDecimal minQty
    String thumbnailImage
    String fullsizeImage
    Date publishedDate
    Date dueDate

    Farmer farmer
    ProductGrouping group
    ProductUgliness ugliness
    ProductType type
    MeasureUnit mUnit


    static constraints = {
        farmer nullable: false
        group nullable: false
        description nullable: true
        description size:0..600
        type nullable: true
        mUnit nullable: true
        suggestedPrice min: 0.1
        farmerPrice nullable: true
        qty nullable: true
        thumbnailImage nullable: true
        fullsizeImage nullable: true
        publishedDate nullable: true
        dueDate nullable: true
    }

    String toString() {
        "$group.name - $type.name - $ugliness.name"
    }
}
