package com.csoft;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

import static com.csoft.gluon.Gluon.newGluonServer;


public class App {

    public static void main(String... args) {

        newGluonServer(8080)
            .withRouter("/test", new MyHandler())
            .start();

    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
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
