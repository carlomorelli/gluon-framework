package com.csoft.gluon.model;

import com.csoft.gluon.exceptions.GluonException;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ExchangeMapper {

    public Request extractRequest(HttpExchange httpExchange) {
        try {
            return new Request(
                    httpExchange.getRequestMethod(),
                    httpExchange.getRequestURI(),
                    httpExchange.getRequestHeaders()
                            .entrySet()
                            .stream()
                            .collect(toMap(Map.Entry::getKey, Map.Entry::getValue)),
                    Collections.emptyMap(), //TODO use OkHttp HttpUrl::parse
                    IOUtils.toByteArray(httpExchange.getRequestBody())
            );
        } catch (IOException e) {
            throw new GluonException("Unable to fully read input stream", e);
        }
    }

    public void passResponse(HttpExchange httpExchange, final Response response) {
        OutputStream os = httpExchange.getResponseBody();
        try {
            httpExchange.sendResponseHeaders(
                    response.getStatus(),
                    response.getBody().length
            );
            os.write(response.getBody());
            os.close();
        } catch (IOException e) {
            throw new GluonException("Unable to fully dump output stream", e);
        }
    }

}
