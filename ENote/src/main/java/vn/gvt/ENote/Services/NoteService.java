package vn.gvt.ENote.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import vn.gvt.ENote.Models.Note;
import vn.gvt.ENote.Models.User;

public interface NoteService {

    void save(Note note);

    int delete(Note note);

    int delete(int id);

    Optional<Note> get(int id);

    void update(Note note);

    List<Note> getAllUnarchivedNotes(User user);

    List<Note> getAllDoneUnarchivedNotes(User user);

    List<Note> getAllNotDoneUnarchivedNotes(User user);

    List<Note> getAllArchivedNotes(User user);

    List<Note> getAllUnarchivedByCreated(User user, LocalDate created);
    
    void deleteAllByUserId(Integer userId);
    
    List<Note> searchNotes(User user, String query);

    List<Note> getReminders(User user);
}
