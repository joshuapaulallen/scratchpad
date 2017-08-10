package org.yourotherleft.scratchpad.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yourotherleft.scratchpad.entity.Note;
import org.yourotherleft.scratchpad.repository.NoteRepository;

/**
 * Defines REST API for the Scratchpad application.
 *
 * @author jallen
 */
@RestController
@RequestMapping(value = "/api")
public class ApiRestController {

	private static final Logger LOG = LoggerFactory.getLogger(ApiRestController.class);

	private final NoteRepository noteRepository;

	@Autowired
	public ApiRestController(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	/**
	 * Saves a new note with the given contents.
	 *
	 * @param note The note to be saved.
	 * @return The saved note.
	 */
	@RequestMapping(value = "/notes", method = RequestMethod.POST)
	public Note createNote(@RequestBody final Note note) {
		checkNotNull(note, "note is null");

		// log it
		LOG.info(String.format("request to add note [%s]", note));

		// save and return
		return noteRepository.save(note);
	}


	/**
	 * Retrieve a note with the given id.
	 *
	 * @param id The id.
	 * @return The note with the given id.
	 */
	@RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
	public Note getNote(@PathVariable final Integer id) {
		checkNotNull(id, "id is null");

		// log it
		LOG.info(String.format("request to get note with id [%s]", id));

		// retrieve and return the note, or throw if it doesn't exist
		return checkNotNull(noteRepository.findOne(id), "could not find note with id [%s]", id);
	}


	/**
	 * Retrieve notes containing the given text, or all the notes if no search text is provided.
	 *
	 * @param query Text to use when searching for notes
	 * @return A list of notes containing the given text, or all the notes if no search text is provided.
	 */
	@RequestMapping(value = "/notes", method = RequestMethod.GET)
	public List<Note> getNotes(@RequestParam(required = false) final String query) {
		final List<Note> notes;
		if(Strings.isNullOrEmpty(query)) {
			// if no query string was provided, return all notes
			LOG.info("request to get all notes");
			notes = Lists.newArrayList(noteRepository.findAll());
		} else {
			// if a query string was provided, search for notes containing the query text
			LOG.info(String.format("request to get notes with text [%s]", query));
			notes = noteRepository.findByBodyWithText(query);
		}

		return notes;
	}
}
