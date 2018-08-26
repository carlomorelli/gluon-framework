package com.csoft.gluon.model;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Request {

    private final String method;
    private final URI requestURI;
    private final Map<String, List<String>> headers;
    private final Map<String, List<String>> params;
    private final byte[] body;

    public Request(final String method,
            final URI requestURI,
            final Map<String, List<String>> headers,
            final Map<String, List<String>> params,
            final byte[] body) {
        this.method = method;
        this.requestURI = requestURI;
        this.headers = headers;
        this.params = params;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public URI getRequestURI() {
        return requestURI;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public Map<String, List<String>> getParams() {
        return params;
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
                Objects.equals(headers, request.params) &&
                Arrays.equals(body, request.body);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(method, requestURI, headers, params);
        result = 31 * result + Arrays.hashCode(body);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "method=" + method +
                ", requestURI=" + requestURI +
                ", headers=" + headers +
                ", params=" + params +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}
