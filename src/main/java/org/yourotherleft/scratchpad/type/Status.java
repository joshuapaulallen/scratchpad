package org.yourotherleft.scratchpad.type;

import javax.annotation.concurrent.Immutable;

/**
 * Describes the status of the application.
 *
 * @author jallen
 */
@Immutable
public class Status {

    private final String text;

    public Status(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        return !(text != null ? !text.equals(status.text) : status.text != null);

    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Status{" +
                "text='" + text + '\'' +
                '}';
    }
}
