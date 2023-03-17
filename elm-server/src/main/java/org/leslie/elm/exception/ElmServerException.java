package org.leslie.elm.exception;

import java.io.Serial;

/**
 * @author zhang
 * date created in 2023/3/17 00:45
 */
public class ElmServerException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -481838313478319086L;

    public ElmServerException(String message) {
        super(message);
    }
}
