package org.yourotherleft.scratchpad;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yourotherleft.scratchpad.controller.AdminRestController;
import org.yourotherleft.scratchpad.controller.ApiRestController;
import org.yourotherleft.scratchpad.entity.Note;

import java.util.List;
import org.yourotherleft.scratchpad.entity.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeFalse;

/**
 * Integration tests for the Scratchpad Application.
 *
 * @author jallen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScratchpadApplicationTests {

    @Autowired
    private ApiRestController apiRestController;

    @Autowired
    private AdminRestController adminRestController;

    @Test
    public void testApi() {
        // test the ping endpoint
        assertEquals("OK", adminRestController.ping().getText());

        // create a couple of notes, and make sure they are saved
        final Note noteTwain = testCreateNote("Whenever you find yourself on the side of the majority, it is time to pause and reflect. -- Mark Twain");
        final Note noteChurchill = testCreateNote("A pessimist sees the difficulty in every opportunity; an optimist sees the opportunity in every difficulty. -- Winston Churchill");
        final Note noteVonnegut = testCreateNote("We are what we pretend to be, so we must be careful about what we pretend to be. -- Kurt Vonnegut");
        final Note noteEmptyText = testCreateNote("");

        // null note should throw
        try {
            apiRestController.createNote(null);
            fail("expected npe because note was null");
        } catch (final NullPointerException npe) {
            // expected
        }

        // test retrieval of all notes
        final List<Note> allNotes = Lists.newArrayList(noteTwain, noteChurchill, noteVonnegut, noteEmptyText);
        assertEquals(allNotes, apiRestController.getNotes(null));

        // test retrieval of note by id
        allNotes.forEach(note -> assertEquals(note, apiRestController.getNote(note.getId())));

        // null id should throw
        try {
            apiRestController.getNote(null);
            fail("expected npe because note id was null");
        } catch (final NullPointerException npe) {
            // expected
        }

        // test retrieval by body text
        assertEquals(Lists.newArrayList(noteTwain), apiRestController.getNotes("Twain"));
        assertTrue(apiRestController.getNotes("Josh").isEmpty());

        // missing id should result in an exception
        final Integer missingId = 0;
        assumeFalse(allNotes.stream().anyMatch(note -> missingId.equals(note.getId())));
        try {
            apiRestController.getNote(missingId);
            fail("expected npe because no such note exists");
        } catch (final NullPointerException npe) {
            // expected
        }
    }

    @Test
    public void testAdmin() {
        // test the ping endpoint
        assertEquals("OK", adminRestController.ping().getText());

        // create some users
        final User homer = testCreateUser("homer", "donuts!");
        final User barney = testCreateUser("barney", "beer!");

        // test retrieval by id
        final List<User> allUsers = Lists.newArrayList(homer, barney);
        allUsers.forEach(user -> assertEquals(user, adminRestController.getUser(user.getId())));
    }


    /**
     * Create and save a note with the given body text.
     *
     * @param body The body text.
     * @return The saved note.
     */
    private Note testCreateNote(final String body) {
        final Note testNote = new Note(body);
        final Note savedTestNote = apiRestController.createNote(testNote);
        assertNotNull(savedTestNote);
        assertNotNull(savedTestNote.getId());
        assertEquals(testNote.getBody(), savedTestNote.getBody());

        return savedTestNote;
    }

    /**
     * Create and save a user with the given details.
     *
     * @param username The username.
     * @param password The password.
     * @return The saved user.
     */
    private User testCreateUser(final String username, final String password) {
        final User testUser = new User(username, password);
        final User savedTestUser = adminRestController.createUser(testUser);
        assertNotNull(savedTestUser);
        assertNotNull(savedTestUser.getId());
        assertEquals(savedTestUser.getUsername(), username);
        assertEquals(savedTestUser.getPassword(), password);

        return savedTestUser;
    }

}
