package querfeld

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MeasureUnitController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MeasureUnit.list(params), model:[measureUnitCount: MeasureUnit.count()]
    }

    def show(MeasureUnit measureUnit) {
        respond measureUnit
    }

    def create() {
        respond new MeasureUnit(params)
    }

    @Transactional
    def save(MeasureUnit measureUnit) {
        if (measureUnit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (measureUnit.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond measureUnit.errors, view:'create'
            return
        }

        measureUnit.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'measureUnit.label', default: 'MeasureUnit'), measureUnit.id])
                redirect measureUnit
            }
            '*' { respond measureUnit, [status: CREATED] }
        }
    }

    def edit(MeasureUnit measureUnit) {
        respond measureUnit
    }

    @Transactional
    def update(MeasureUnit measureUnit) {
        if (measureUnit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (measureUnit.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond measureUnit.errors, view:'edit'
            return
        }

        measureUnit.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'measureUnit.label', default: 'MeasureUnit'), measureUnit.id])
                redirect measureUnit
            }
            '*'{ respond measureUnit, [status: OK] }
        }
    }

    @Transactional
    def delete(MeasureUnit measureUnit) {

        if (measureUnit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        measureUnit.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'measureUnit.label', default: 'MeasureUnit'), measureUnit.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'measureUnit.label', default: 'MeasureUnit'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
