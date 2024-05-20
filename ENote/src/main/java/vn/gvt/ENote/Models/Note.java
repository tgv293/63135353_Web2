package vn.gvt.ENote.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

@Entity
@Table(name = "notes")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Note implements Serializable {
	@Id
	@Positive
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Version
	private Integer version;

	@NotBlank
	@Size(max = 500)
	private String header;

	@Size(max = 5000)
	private String body;

	@NotNull
	@Enumerated(EnumType.STRING)
	private NoteState state;

	@PastOrPresent
	@NotNull
	private LocalDate created;
	@PastOrPresent
	private LocalDate lastModified;
	@PastOrPresent
	private LocalDate archivedAt;
	@Future
	private LocalDateTime reminderTime;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private boolean isDeleted;
	private boolean isArchived;
	private boolean isDone;
}
