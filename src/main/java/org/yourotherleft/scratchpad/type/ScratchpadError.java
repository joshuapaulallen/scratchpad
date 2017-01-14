package org.yourotherleft.scratchpad.type;

import javax.annotation.concurrent.Immutable;

/**
 * Describes an error that occurred in the Scratchpad application.
 *
 * @author jallen
 */
@Immutable
public class ScratchpadError {

    private final Integer code;
    private final String message;

    /**
     * Constructor.
     *
     * @param code    An error code.
     * @param message A message describing the error.
     */
    public ScratchpadError(final Integer code, final String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Getter.
     *
     * @return An error code.
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Getter.
     *
     * @return A message describing the error.
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ScratchpadError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
