package com.vic.ck.api.exception;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class CommonRuntimeException extends HttpStatusCodeException {
	private static final long serialVersionUID = 1L;
	
	private Object[] params;

    public CommonRuntimeException(HttpStatus statusCode) {
        super(statusCode,"error.default");
    }

    public CommonRuntimeException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public CommonRuntimeException(HttpStatus statusCode, String statusText, Object... params) {
        super(statusCode, statusText);
        this.params = params;
    }

    public CommonRuntimeException(HttpStatus statusCode, String statusText, Throwable ex) {
        super(statusCode, statusText);
        initCause(ex);
    }

    public CommonRuntimeException(HttpStatus statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseBody, responseCharset);
    }

    public CommonRuntimeException(HttpStatus statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
        super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

}
