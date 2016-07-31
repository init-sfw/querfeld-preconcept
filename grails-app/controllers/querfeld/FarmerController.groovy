package querfeld

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FarmerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Farmer.list(params), model:[farmerCount: Farmer.count()]
    }

    def show(Farmer farmer) {
        respond farmer
    }

    def create() {
        respond new Farmer(params)
    }

    @Transactional
    def save(Farmer farmer) {
        if (farmer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (farmer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond farmer.errors, view:'create'
            return
        }

        farmer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'farmer.label', default: 'Farmer'), farmer.id])
                redirect farmer
            }
            '*' { respond farmer, [status: CREATED] }
        }
    }

    def edit(Farmer farmer) {
        respond farmer
    }

    @Transactional
    def update(Farmer farmer) {
        if (farmer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (farmer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond farmer.errors, view:'edit'
            return
        }

        farmer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'farmer.label', default: 'Farmer'), farmer.id])
                redirect farmer
            }
            '*'{ respond farmer, [status: OK] }
        }
    }

    @Transactional
    def delete(Farmer farmer) {

        if (farmer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        farmer.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'farmer.label', default: 'Farmer'), farmer.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'farmer.label', default: 'Farmer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
