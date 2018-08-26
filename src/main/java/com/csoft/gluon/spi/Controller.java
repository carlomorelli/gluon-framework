package com.csoft.gluon.spi;

import com.csoft.gluon.model.Request;
import com.csoft.gluon.model.Response;
import com.sun.net.httpserver.HttpHandler;

public abstract class Controller {

    private ExchangeMapper exchangeMapper = new ExchangeMapper();

    public Controller() {
    }

    public HttpHandler asHttpHandler() {
        System.out.println("here controller");
        return exchange -> {
            System.out.println("here internal controller");
            Response response = handle(exchangeMapper.extractRequest(exchange));
            exchangeMapper.passResponse(exchange, response);
        };
    }

    public abstract Response handle(final Request request);


}
