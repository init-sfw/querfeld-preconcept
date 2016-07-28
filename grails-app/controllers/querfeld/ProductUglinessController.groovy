package querfeld

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProductUglinessController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProductUgliness.list(params), model:[productUglinessCount: ProductUgliness.count()]
    }

    def show(ProductUgliness productUgliness) {
        respond productUgliness
    }

    def create() {
        respond new ProductUgliness(params)
    }

    @Transactional
    def save(ProductUgliness productUgliness) {
        if (productUgliness == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productUgliness.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productUgliness.errors, view:'create'
            return
        }

        productUgliness.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'productUgliness.label', default: 'ProductUgliness'), productUgliness.id])
                redirect productUgliness
            }
            '*' { respond productUgliness, [status: CREATED] }
        }
    }

    def edit(ProductUgliness productUgliness) {
        respond productUgliness
    }

    @Transactional
    def update(ProductUgliness productUgliness) {
        if (productUgliness == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (productUgliness.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond productUgliness.errors, view:'edit'
            return
        }

        productUgliness.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'productUgliness.label', default: 'ProductUgliness'), productUgliness.id])
                redirect productUgliness
            }
            '*'{ respond productUgliness, [status: OK] }
        }
    }

    @Transactional
    def delete(ProductUgliness productUgliness) {

        if (productUgliness == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        productUgliness.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'productUgliness.label', default: 'ProductUgliness'), productUgliness.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'productUgliness.label', default: 'ProductUgliness'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
