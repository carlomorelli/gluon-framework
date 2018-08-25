package com.csoft.gluon.spi;

import com.sun.net.httpserver.HttpHandler;

public class Controller {

    private HttpHandler httpHandler;

    public Controller(final HttpHandler httpHandler) {
        this.httpHandler = httpHandler;
    }

    public HttpHandler asHttpHandler() {
        return httpHandler;
    }

}
