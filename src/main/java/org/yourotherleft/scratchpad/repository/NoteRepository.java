package org.yourotherleft.scratchpad.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.yourotherleft.scratchpad.entity.Note;

import java.util.List;

/**
 * A repository for CRUD operations on {@link Note}s.
 *
 * @author jallen
 */
@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {

    /**
     * Retrieve notes whose body contains the given text.
     *
     * @param text The text to search for in body notes.
     * @return The notes whose body contains the given text.
     */
    @Query("select n from Note n where n.body like %:text%")
    List<Note> findByBodyWithText(@Param("text") String text);

}
