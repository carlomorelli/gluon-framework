package com.csoft;

import com.csoft.gluon.exceptions.GluonException;
import com.csoft.gluon.spi.Controller;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

import static com.csoft.gluon.Gluon.newGluonServer;
import static com.csoft.gluon.spi.Router.newGluonRouter;


public class App {

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
        public void handle(HttpExchange t) {
            String response = "This is the response";
            OutputStream os = t.getResponseBody();
            try {
                t.sendResponseHeaders(200, response.length());
                os.write(response.getBytes());
                os.close();
            } catch (IOException e) {
                throw new GluonException("Unable to correctly format response", e);
            }
        }
    }

/*
    newGluonServer()
        .withRouter(
                newGluonRouter()
                    .get("/version", new GetVersionController())
                    .get("/users", new GetUsersController())
                    .post("/users/:id", new PostUserController())
        )
        .serve(8080)
*/
}
