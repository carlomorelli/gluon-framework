package com.csoft.gluon.spi;

import com.csoft.gluon.model.Request;
import com.csoft.gluon.model.Response;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;

public class ExchangeMapper {

    public HttpExchange asHttpExchange(final Request request, final Response response) {

        return new HttpExchange() {
            @Override
            public Headers getRequestHeaders() {
                return null;
            }

            @Override
            public Headers getResponseHeaders() {
                return null;
            }

            @Override
            public URI getRequestURI() {
                return request.getRequestURI();
            }

            @Override
            public String getRequestMethod() {
                return request.getMethod();
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return new ByteArrayInputStream(request.getBody());
            }

            @Override
            public OutputStream getResponseBody() {
                return new ByteArrayOutputStream();
            }

            @Override
            public void sendResponseHeaders(int rCode, long responseLength) throws IOException {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return response.getStatus();
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String name) {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) {

            }

            @Override
            public void setStreams(InputStream i, OutputStream o) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
    }


    public Request asRequest(final HttpExchange httpExchange) {

        return null;
    }

    public Response asResponse(final HttpExchange httpExchange) {

        return null;
    }
}
