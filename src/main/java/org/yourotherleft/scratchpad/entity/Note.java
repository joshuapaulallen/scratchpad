package org.yourotherleft.scratchpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * An entity describing a note.
 *
 * @author jallen
 */
@Entity
@Table(name = "Note")
public class Note implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String body;

    /**
     * Getter.
     *
     * @return The id.
     */
    public Integer getId() {
        return id;
    }


    /**
     * Getter.
     *
     * @return The body of the note.
     */
    public String getBody() {
        return body;
    }

    /**
     * Zero-argument constructor needed by the JPA spec.
     */
    protected Note() {
        // nothing to do
    }

    /**
     * Constructor.
     *
     * @param body The body text.
     */
    public Note(final String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (id != null ? !id.equals(note.id) : note.id != null) return false;
        return !(body != null ? !body.equals(note.body) : note.body != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", body='" + body + '\'' +
                '}';
    }

}
