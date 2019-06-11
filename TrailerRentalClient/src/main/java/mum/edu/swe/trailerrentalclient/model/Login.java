package mum.edu.swe.trailerrentalclient.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class Login {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
