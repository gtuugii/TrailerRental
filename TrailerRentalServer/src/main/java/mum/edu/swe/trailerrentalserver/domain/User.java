package mum.edu.swe.trailerrentalserver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import mum.edu.swe.trailerrentalserver.domain.validators.UniqueEmail;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(indexes = {@Index(columnList = "email, password")})
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    @Size(min = 8, max = 50, message = "{Size.String.validation}")
    @UniqueEmail
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    @Column(name="first_name")
    @Size(min = 3, max = 50, message = "{Size.String.validation}")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @NotEmpty
    @Column(name="phone_number")
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}", message = "Phone number format is : [(###) ###-####] e.g-(123) 456-7890")
    private String phoneNumber;

    @Column(name="picture_path")
    private String picturePath;

    //0-INACTIVE, 1-ACTIVE
    private int status;

    //0-Male, 1-Female
    private int sex;

    @Column(name="birth_date")
    @PastOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(name="reg_date")
    @PastOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate regDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id") )
    private List<Role> roles;
}
