package jmeter.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "UserEntity")
@Table(name = "user")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userId;
    private String name;
    private String lastname;
    private int age;
}
