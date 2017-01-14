package org.yourotherleft.scratchpad.controller;

import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.yourotherleft.scratchpad.type.ScratchpadError;

/**
 * Global error handler for RestControllers.
 *
 * @author jallen
 */
@ControllerAdvice(annotations = RestController.class)
public class RestControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(RestControllerAdvice.class);

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ScratchpadError handleException(final Throwable t) {
        // log the error
        LOG.error(String.format("unhandled exception [%s]", Throwables.getStackTraceAsString(t)));

        return new ScratchpadError(HttpStatus.INTERNAL_SERVER_ERROR.value(), t.getMessage());
    }

}
