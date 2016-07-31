package querfeld

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProductGroupingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProductGrouping.list(params), model:[productGroupingCount: ProductGrouping.count()]
    }

    def show(ProductGrouping productGrouping) {
        respond productGrouping
    }

    def create() {
        respond new ProductGrouping(params)
    }

    @Transactional
    def save(ProductGrouping productGrouping) {
        if (productGrouping == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productGrouping.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productGrouping.errors, view:'create'
            return
        }

        productGrouping.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'productGrouping.label', default: 'ProductGrouping'), productGrouping.id])
                redirect productGrouping
            }
            '*' { respond productGrouping, [status: CREATED] }
        }
    }

    def edit(ProductGrouping productGrouping) {
        respond productGrouping
    }

    @Transactional
    def update(ProductGrouping productGrouping) {
        if (productGrouping == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productGrouping.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productGrouping.errors, view:'edit'
            return
        }

        productGrouping.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'productGrouping.label', default: 'ProductGrouping'), productGrouping.id])
                redirect productGrouping
            }
            '*'{ respond productGrouping, [status: OK] }
        }
    }

    @Transactional
    def delete(ProductGrouping productGrouping) {

        if (productGrouping == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        productGrouping.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'productGrouping.label', default: 'ProductGrouping'), productGrouping.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'productGrouping.label', default: 'ProductGrouping'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
