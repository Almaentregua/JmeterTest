package jmeter.res.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdDTO {
    private Integer id;

    public UserIdDTO(Integer id){
        this.id = id;
    }
}
