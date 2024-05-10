package vn.gvt.ENote.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.gvt.ENote.Models.Note;
import vn.gvt.ENote.Models.User;

@Component
public class NoteManagementImpl implements NoteManagement {

    private final NoteService noteService;

    @Autowired
    public NoteManagementImpl(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public void saveNew(Note note, User user) {
        note.setUser(user);
        note.setCreated(LocalDate.now());
        noteService.save(note);
    }

    @Override
    public List<Note> getAllUnarchived(User user) {
        return noteService.getAllUnarchivedNotes(user);
    }

    @Override
    public List<Note> getAllArchived(User user) {
        return noteService.getAllArchivedNotes(user);
    }

    @Override
    public void update(Note note, User user) {
        note.setUser(user);
        noteService.update(note);
    }

    @Override
    public Optional<Note> getById(int id) {
        return noteService.get(id);
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        noteService.delete(id);
    }

    @Override
    public void archive(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        Optional<Note> note = noteService.get(id);
        note.ifPresent(n -> {
            n.setArchived(true);
            n.setArchivedAt(LocalDate.now());
            noteService.update(n);
        });
    }

    @Override
    public void unarchive(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException();
        }
        Optional<Note> note = noteService.get(id);
        note.ifPresent(n -> {
            n.setArchived(false);
            n.setArchivedAt(null);
            noteService.update(n);
        });
    }

    @Override
    public List<Note> getAllDoneUnarchived(User user) {
        return noteService.getAllDoneUnarchivedNotes(user);
    }

    @Override
    public List<Note> getAllUnarchivedByCreated(User user, LocalDate created) {
        return noteService.getAllUnarchivedByCreated(user, created);
    }

    @Override
    public List<Note> getAllNotDoneUnarchived(User user) {
        return noteService.getAllNotDoneUnarchivedNotes(user);
    }
	
}
