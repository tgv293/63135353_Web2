package vn.gvt.ENote.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import vn.gvt.ENote.Models.Note;
import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Services.NoteService;
import vn.gvt.ENote.Services.UserService;

@Configuration
@EnableScheduling
public class SchedulingConfig {
	@Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService; // Assuming you have a UserService to get all users

    @Scheduled(fixedRate = 60000) // Check every minute
    public void checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        List<User> users = userService.getAllUsers(); // Assuming you have a method to get all users
        for (User user : users) {
            List<Note> notes = noteService.getNotesByUser(user);
            for (Note note : notes) {
                if (note.getReminderTime() != null && !note.getReminderTime().isAfter(now)) {
                    // Send reminder to user
                }
            }
        }
    }

}
