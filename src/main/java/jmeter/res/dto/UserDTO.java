package jmeter.res.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserDTO {
    private static final String NAME_REGEX = "^[a-zA-Z]{1,20}";
    @Pattern(regexp = NAME_REGEX,message = "incorrect name")
    private String name;
    @Pattern(regexp = NAME_REGEX,message = "incorrect name")
    private String lastname;
    @Min(1)
    @Max(130)
    private int age;
}
