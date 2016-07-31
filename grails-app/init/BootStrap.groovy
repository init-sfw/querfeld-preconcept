import querfeld.Location
import querfeld.MeasureUnit
import querfeld.ProductGrouping
import querfeld.ProductType
import querfeld.ProductUgliness

class BootStrap {

    def init = { servletContext ->
        // Measure units
        def mu1 = new MeasureUnit()
        mu1.name = "Kilogram"
        mu1.shortName = "Kg"
        mu1.description = "Measure unit representing one Kilogram"
        def mu2 = new MeasureUnit()
        mu2.name = "Unit"
        mu2.shortName = "Unt"
        mu2.description = "Measure unit representing one Unit of a product that can be easily countable"

        mu1.save(failOnError: true)
        mu2.save(failOnError: true)

        // Location
        def loc1 = new Location()
        loc1.latitude = 52.492566
        loc1.longitude = 13.427360
        loc1.city = "Berlin"
        loc1.save(failOnError: true)

        // Ugliness
        def ugl1 = new ProductUgliness()
        ugl1.name = "Too big"
        ugl1.description = "The product is too big for conventional markets"

        def ugl2 = new ProductUgliness()
        ugl2.name = "Deform"
        ugl2.description = "The product has an unconventional shape for the conventional market"

        def ugl3 = new ProductUgliness()
        ugl3.name = "Surplus"
        ugl3.description = "The harvest/production of the product exceeded the current market demand and needs a sales channel"

        ugl1.save(failOnError: true)
        ugl2.save(failOnError: true)
        ugl3.save(failOnError: true)

        // Product Type
        def pt1 = new ProductType()
        pt1.name = "Alicante"
        pt1.description = "Red medium-sized, standard Alicante tomato"

        def pt2 = new ProductType()
        pt2.name = "Roma"
        pt2.description = "Red medium-sized, plum shaped Roma Tomato"

        def pt3 = new ProductType()
        pt3.name = "Santorini"
        pt3.description = "Red small-sized, pear shaped Santorini tomato"

        // Product Grouping
        def group = new ProductGrouping()
        group.uglinessList = new HashSet()
        group.productTypeList = new HashSet()
        group.units = new HashSet()
        group.name = "Tomato"
        group.description = "Category for Tomatos"
        group.icon = "URL to some image"
        group.uglinessList.add(ugl1)
        group.uglinessList.add(ugl2)
        group.uglinessList.add(ugl3)
        group.productTypeList.add(pt1)
        group.productTypeList.add(pt2)
        group.productTypeList.add(pt3)
        group.units.add(mu1)
        group.units.add(mu2)

        group.save(failOnError: true)
    }
    def destroy = {
    }
}

