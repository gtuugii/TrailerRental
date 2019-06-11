package mum.edu.swe.trailerrentalclient.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class User {

    private Long id;

    @NotEmpty
    @Size(min = 8, max = 50, message = "{Size.String.validation}")
    @Email
    //@Pattern(regexp=".+@.+\\.[a-z]+")
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    @Size(min = 2, max = 50, message = "${Size.firstName}")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 50, message = "${Size.lastName}")
    private String lastName;

    @NotEmpty
    //@Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;

    private String picturePath;

    //0-INACTIVE, 1-ACTIVE
    private int status;

    //0-Male, 1-Female
    private int sex;

    @PastOrPresent
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    //@PastOrPresent
    //@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate regDate = LocalDate.now();

    private List<Role> roles;

}
