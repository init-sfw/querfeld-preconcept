package querfeld

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProductTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProductType.list(params), model:[productTypeCount: ProductType.count()]
    }

    def show(ProductType productType) {
        respond productType
    }

    def create() {
        respond new ProductType(params)
    }

    @Transactional
    def save(ProductType productType) {
        if (productType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productType.errors, view:'create'
            return
        }

        productType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'productType.label', default: 'ProductType'), productType.id])
                redirect productType
            }
            '*' { respond productType, [status: CREATED] }
        }
    }

    def edit(ProductType productType) {
        respond productType
    }

    @Transactional
    def update(ProductType productType) {
        if (productType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productType.errors, view:'edit'
            return
        }

        productType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'productType.label', default: 'ProductType'), productType.id])
                redirect productType
            }
            '*'{ respond productType, [status: OK] }
        }
    }

    @Transactional
    def delete(ProductType productType) {

        if (productType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        productType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'productType.label', default: 'ProductType'), productType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'productType.label', default: 'ProductType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
