package vn.gvt.ENote.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import vn.gvt.ENote.Models.Note;
import vn.gvt.ENote.Models.User;

public interface NoteService {

    void save(Note note);
    
    void saveNew(Note note, User user);

    long delete(Note note);

    long delete(int id);

    Optional<Note> get(int id);
    
    Optional<Note> getById(int id);

    void update(Note note);
    
    void update(Note note, User user);

    List<Note> getAllUnarchivedNotes(User user);

    List<Note> getAllDoneUnarchivedNotes(User user);

    List<Note> getAllNotDoneUnarchivedNotes(User user);

    List<Note> getAllArchivedNotes(User user);

    List<Note> getAllUnarchivedByCreated(User user, LocalDate created);
    
    void deleteById(int id);
    
    void archive(int id);

    void unarchive(int id);
}
