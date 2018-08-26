package com.csoft.gluon.spi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class Controller {

    public Controller() {
    }

    public HttpHandler asHttpHandler() {
        return this::handle;
    }

    public abstract void handle(final HttpExchange exchange);
}
