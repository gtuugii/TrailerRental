package mum.edu.swe.trailerrentalserver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private String email;
    private String password;

    @NotEmpty
    @Column(name="first_name")
    @Size(min = 3, max = 50, message = "{Size.String.validation}")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @NotEmpty
    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="picture_path")
    private String picturePath;

    private int status;
    private int sex;

    @Column(name="reg_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate regDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id") )
    private List<Role> roles;
}
