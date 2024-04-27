package vn.gvt.ENote.Models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter @Setter
@EqualsAndHashCode(exclude = {"password", "decryptedPassword"})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password", "decryptedPassword"})
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 25)
    private String firstName;

    @NotBlank
    @Size(max = 25)
    private String lastName;

    @Email
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 2000)
    private String password;
    
    @Transient
    private String decryptedPassword;

    @Min(3)
    @Max(100)
    private int age;

    @NotBlank
    @Size(max = 255)
    private String address;

    @PastOrPresent
    @NotNull
    private LocalDate registration;

    private boolean active = true;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole role;
}
