package Entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@EntityScan
@Data
public class User {

	
    private int user_id;
    @NotEmpty(message = "The First name field cannot be empty")
    @Size(min=3, message = "The First name field must greater that 3 characters")
    private String first_name;
    @NotEmpty(message = "The Last name field cannot be empty")
    @Size(min=3, message = "The First name field must greater that 3 characters")
    private String last_name;
    @Email
    @NotEmpty
    @Pattern(regexp = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", message = "Please Enter a Valid Email")
    private String email;
    @NotEmpty
    @NotNull
    private String password;
    private String token;
    private String code;
    private int verified;
    private LocalDate verified_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    
}
