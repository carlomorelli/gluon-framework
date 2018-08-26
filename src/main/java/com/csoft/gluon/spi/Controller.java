package com.csoft.gluon.spi;

import com.csoft.gluon.model.ExchangeMapper;
import com.csoft.gluon.model.Request;
import com.csoft.gluon.model.Response;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private ExchangeMapper exchangeMapper = new ExchangeMapper();

    public Controller() {
    }

    public final HttpHandler toHandler() {
        return exchange -> {
            LOGGER.info("Access: method=[{}], source=[{}], path=[{}]",
                    exchange.getRequestMethod(),
                    exchange.getRemoteAddress().getHostString(),
                    exchange.getHttpContext().getPath());
            Response response = handle(exchangeMapper.extractRequest(exchange));
            exchangeMapper.passResponse(exchange, response);
        };
    }

    public abstract Response handle(final Request request);


}
