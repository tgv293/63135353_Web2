package vn.gvt.ENote.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import vn.gvt.ENote.Models.Note;
import vn.gvt.ENote.Models.User;

public interface NoteManagement {

    void saveNew(Note noteDto, User user);

    List<Note> getAllUnarchived(User user);

    List<Note> getAllUnarchivedByCreated(User user, LocalDate created);

    List<Note> getAllDoneUnarchived(User user);

    List<Note> getAllNotDoneUnarchived(User user);

    List<Note> getAllArchived(User user);

    void update(Note note, User user);

    Optional<Note> getById(int id);

    void deleteById(Integer id);

    void archive(Integer id);

    void unarchive(Integer id);
	
}
