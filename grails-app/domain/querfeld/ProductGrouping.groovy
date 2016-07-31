package querfeld

class ProductGrouping {

    String name
    String description
    String icon

    static hasMany = [uglinessList: ProductUgliness, productTypeList: ProductType, units: MeasureUnit]

    static constraints = {
        name nullable: false
        description nullable: true
        icon nullable: false
        uglinessList nullable: false
        productTypeList nullable: false
        units nullable: false
    }
}
