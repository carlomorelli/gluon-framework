package com.csoft.gluon.model;

import java.net.URI;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class Request {

    private String method;
    private URI requestURI;
    private Map<String, String> headers;
    private byte[] body;

    public Request(final String method, final URI requestURI, final Map<String, String> headers, final byte[] body) {
        this.requestURI = requestURI;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }
    public URI getRequestURI() {
        return requestURI;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(method, request.method) &&
                Objects.equals(requestURI, request.requestURI) &&
                Objects.equals(headers, request.headers) &&
                Arrays.equals(body, request.body);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(method, requestURI, headers);
        result = 31 * result + Arrays.hashCode(body);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "method=" + method +
                ", requestURI=" + requestURI +
                ", headers=" + headers +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}