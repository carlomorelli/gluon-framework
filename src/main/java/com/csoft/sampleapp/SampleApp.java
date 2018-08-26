package com.csoft.sampleapp;

import com.csoft.gluon.exceptions.GluonException;
import com.csoft.gluon.model.Request;
import com.csoft.gluon.model.Response;
import com.csoft.gluon.spi.Controller;

import java.util.Collections;

import static com.csoft.gluon.Gluon.newGluonServer;
import static com.csoft.gluon.spi.Router.newGluonRouter;


public class SampleApp {

    public static void main(String... args) {
        newGluonServer(8080)
                .withRouter(
                        newGluonRouter()
                                .get("/test", new MyController())
                )
                .start();
    }

    static class MyController extends Controller {
        @Override
        public Response handle(final Request request) {
            if (!request.getMethod().equalsIgnoreCase("GET")) {
                throw new GluonException("Invalid method");
            }
            return new Response(200, Collections.emptyMap(), "This is the response".getBytes());
        }
    }

}
