package com.csoft.sampleapp

import com.csoft.gluon.Gluon
import com.csoft.gluon.exceptions.GluonException
import com.csoft.gluon.model.Request
import com.csoft.gluon.model.Response
import com.csoft.gluon.spi.Controller
import com.csoft.gluon.spi.Router


object SampleApp {

    internal class MyController : Controller() {
        override fun handle(request: Request): Response {
            if (!request.method.equals("GET", ignoreCase = true)) {
                throw GluonException("Invalid method")
            }
            return Response(200, emptyMap(), "This is the response".toByteArray())
        }
    }

}


fun main(args: Array<String>) {
    Gluon(8080)
            .withRouter(
                    Router()
                            .get("/test", SampleApp.MyController())
            )
            .start()
}
