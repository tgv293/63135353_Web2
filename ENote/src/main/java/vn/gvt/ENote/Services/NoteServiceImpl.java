package vn.gvt.ENote.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.gvt.ENote.Models.Note;
import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Repositories.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Lưu ghi chú vào cơ sở dữ liệu.
     * 
     * @param note Ghi chú cần lưu.
     * @throws IllegalArgumentException Nếu ghi chú là null.
     */
    @Override
    public void save(Note note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }

        noteRepository.save(note);
    }

    /**
     * Xóa ghi chú khỏi cơ sở dữ liệu.
     * 
     * @param note Ghi chú cần xóa.
     * @return ID của ghi chú đã bị xóa.
     * @throws IllegalArgumentException Nếu ghi chú là null.
     */
    @Override
    public int delete(Note note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }

        noteRepository.delete(note);
        return note.getId();
    }

    /**
     * Xóa ghi chú khỏi cơ sở dữ liệu dựa trên ID.
     * 
     * @param id ID của ghi chú cần xóa.
     * @return ID của ghi chú đã bị xóa.
     * @throws IllegalArgumentException Nếu ID nhỏ hơn 1.
     */
    @Override
    public int delete(int id) {
        if (id < 1) {
            throw new IllegalArgumentException(String.format("Không thể xóa ghi chú với ID nhỏ hơn 1. ID = %d", id));
        }

        noteRepository.deleteById(id);
        return id;
    }

    /**
     * Lấy ghi chú dựa trên ID.
     * 
     * @param id ID của ghi chú cần lấy.
     * @return Optional chứa ghi chú nếu tìm thấy, nếu không trả về Optional rỗng.
     * @throws IllegalArgumentException Nếu ID nhỏ hơn 1.
     */
    @Override
    public Optional<Note> get(int id) {
        if (id < 1) {
            throw new IllegalArgumentException(String.format("Không thể tìm thấy ghi chú với ID nhỏ hơn 1. ID = %d", id));
        }

        return noteRepository.findById(id);
    }

    /**
     * Cập nhật thông tin của ghi chú.
     * 
     * @param note Ghi chú cần cập nhật.
     * @throws IllegalArgumentException Nếu ghi chú là null hoặc ID nhỏ hơn 1.
     */
    @Override
    public void update(Note note) {
        if (note == null) {
            throw new IllegalArgumentException();
        }

        Integer noteId = note.getId();
        if (noteId < 1) {
            throw new IllegalArgumentException(String.format("Không thể cập nhật ghi chú với ID nhỏ hơn 1. ID = %d", noteId));
        }
        note.setLastModified(LocalDate.now());
        noteRepository.save(note);
    }

    /**
     * Lấy danh sách tất cả các ghi chú chưa được lưu trữ của người dùng.
     * 
     * @param user Người dùng.
     * @return Danh sách các ghi chú chưa được lưu trữ, sắp xếp theo thời gian tạo giảm dần.
     * @throws IllegalArgumentException Nếu người dùng là null.
     */
    @Override
    public List<Note> getAllUnarchivedNotes(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return noteRepository.findAllByUserAndIsArchivedOrderByCreatedDesc(user, false);
    }

    /**
     * Lấy danh sách tất cả các ghi chú đã hoàn thành và chưa được lưu trữ của người dùng.
     * 
     * @param user Người dùng.
     * @return Danh sách các ghi chú đã hoàn thành và chưa được lưu trữ, sắp xếp theo thời gian tạo giảm dần.
     * @throws IllegalArgumentException Nếu người dùng là null.
     */
    @Override
    public List<Note> getAllDoneUnarchivedNotes(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return noteRepository.findAllByUserAndIsArchivedAndIsDoneOrderByCreatedDesc(user, false, true);
    }

    /**
     * Lấy danh sách tất cả các ghi chú chưa hoàn thành và chưa được lưu trữ của người dùng.
     * 
     * @param user Người dùng.
     * @return Danh sách các ghi chú chưa hoàn thành và chưa được lưu trữ, sắp xếp theo thời gian tạo giảm dần.
     * @throws IllegalArgumentException Nếu người dùng là null.
     */
    @Override
    public List<Note> getAllNotDoneUnarchivedNotes(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return noteRepository.findAllByUserAndIsArchivedAndIsDoneOrderByCreatedDesc(user, false, false);
    }

    /**
     * Lấy danh sách tất cả các ghi chú đã được lưu trữ của người dùng.
     * 
     * @param user Người dùng.
     * @return Danh sách các ghi chú đã được lưu trữ, sắp xếp theo thời gian tạo giảm dần.
     * @throws IllegalArgumentException Nếu người dùng là null.
     */
    @Override
    public List<Note> getAllArchivedNotes(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return noteRepository.findAllByUserAndIsArchivedOrderByCreatedDesc(user, true);
    }

    /**
     * Lấy danh sách tất cả các ghi chú chưa được lưu trữ của người dùng dựa trên ngày tạo.
     * 
     * @param user    Người dùng.
     * @param created Ngày tạo của ghi chú.
     * @return Danh sách các ghi chú chưa được lưu trữ, sắp xếp theo thời gian tạo giảm dần.
     * @throws IllegalArgumentException Nếu người dùng là null.
     */
    @Override
    public List<Note> getAllUnarchivedByCreated(User user, LocalDate created) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return noteRepository.findAllByUserAndIsArchivedAndCreated(user, false, created);
    }
    
    /**
     * Xóa tất cả các ghi chú của người dùng dựa trên ID người dùng.
     * 
     * @param userId ID của người dùng.
     */
    @Transactional
    @Override
    public void deleteAllByUserId(Integer userId) {
        noteRepository.deleteAllByUserId(userId);
    }
    
    /**
     * Tìm kiếm các ghi chú của người dùng dựa trên từ khóa.
     * 
     * @param user  Người dùng.
     * @param query Từ khóa tìm kiếm.
     * @return Danh sách các ghi chú phù hợp với từ khóa.
     */
    @Override
    public List<Note> searchNotes(User user, String query) {
        return noteRepository.findByUserAndHeaderContaining(user, query);
    }
    
    /**
     * Lấy danh sách các ghi chú nhắc nhở của người dùng.
     * 
     * @param user Người dùng.
     * @return Danh sách các ghi chú nhắc nhở, sắp xếp theo thời gian nhắc nhở tăng dần.
     */
    @Override
    public List<Note> getReminders(User user) {
        LocalDateTime now = LocalDateTime.now().minusMinutes(1);
        return noteRepository.findByUserAndReminderTimeAfterAndReminderTimeIsNotNullAndIsDoneEquals(user, now, false);
    }

}
