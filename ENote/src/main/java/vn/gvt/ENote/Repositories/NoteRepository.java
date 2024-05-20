package vn.gvt.ENote.Repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.gvt.ENote.Models.Note;
import vn.gvt.ENote.Models.User;

@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {
    List<Note> findAllByUser(User user);

    List<Note> findAllByUserAndIsArchivedOrderByCreatedDesc(User user, boolean isArchived);

    List<Note> findAllByUserAndIsArchivedAndIsDoneOrderByCreatedDesc(User user, boolean isArchived, boolean isDone);

    List<Note> findAllByUserAndIsArchivedAndCreated(User user, boolean isArchived, LocalDate created);
    
    void deleteAllByUserId(Integer userId);
    
    List<Note> findByUserAndHeaderContaining(User user, String header);

    List<Note> findByUserAndReminderTimeAfterAndReminderTimeIsNotNullAndIsDoneEquals(User user, LocalDateTime time, boolean isDone);

}
