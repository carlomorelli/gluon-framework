package com.csoft.gluon.model;

import com.csoft.gluon.exceptions.GluonException;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import static java.util.stream.Collectors.*;

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
                    Collections.emptyMap(), //URLEncodedUtils.parse(httpExchange.getRequestURI(), Charset.forName("UTF-8")),
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


    private static Map<String, List<String>> parseURLQuery(String query) {
        return Arrays.stream(query.split("&"))
                .map(ExchangeMapper::splitQueryParameter)
                .collect(groupingBy(AbstractMap.SimpleImmutableEntry::getKey, LinkedHashMap::new, mapping(Map.Entry::getValue, toList())));
    }

    private static AbstractMap.SimpleImmutableEntry<String, String> splitQueryParameter(String string) {
        final int idx = string.indexOf("=");
        final String key = idx > 0 ? string.substring(0, idx) : string;
        final String value = idx > 0 && string.length() > idx + 1 ? string.substring(idx + 1) : null;
        return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }
}
