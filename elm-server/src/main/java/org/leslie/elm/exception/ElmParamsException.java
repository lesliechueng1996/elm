package org.leslie.elm.exception;

import java.io.Serial;

/**
 * @author zhang
 * date created in 2023/3/17 00:57
 */
public class ElmParamsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1421811771142698509L;

    public ElmParamsException(String message) {
        super(message);
    }
}
