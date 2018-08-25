package com.csoft.gluon;

import com.csoft.gluon.spi.Router;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Gluon {

    private static final Logger LOGGER = LoggerFactory.getLogger(Gluon.class);

    private HttpServer httpServer;

    private Router router;

    private Gluon(final int port) {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServer.setExecutor(null);
            LOGGER.info("Gluon started on port [{}].", port);
        } catch (IOException e) {
            LOGGER.error("Unable to start Gluon on port [{}]. Reason: {}", port, e.getMessage());
        }
    }

    public void start() {
        httpServer.start();
    }

    public void stop() {
        httpServer.stop(0);
    }

    public Gluon withRouter(String path, final HttpHandler httpHandler) {
        httpServer.createContext(path, httpHandler);
        return this;
    }


    public static Gluon newGluonServer(final int port) {
        return new Gluon(port);
    }
}
