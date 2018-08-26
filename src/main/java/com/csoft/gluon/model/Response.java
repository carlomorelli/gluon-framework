package com.csoft.gluon.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Response {

    private int status;
    private Map<String, List<String>> headers;
    private byte[] body;

    public Response(final int status, final Map<String, List<String>> headers, final byte[] body) {
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public byte[] getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return status == response.status &&
                Objects.equals(headers, response.headers) &&
                Arrays.equals(body, response.body);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(status, headers);
        result = 31 * result + Arrays.hashCode(body);
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", headers=" + headers +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}
