package jmeter.res.controller;
import jmeter.entity.UserEntity;
import jmeter.res.dto.UserDTO;
import jmeter.res.dto.UserIdDTO;
import jmeter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@ControllerAdvice
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value= "/user",method = RequestMethod.POST)
    public HttpEntity<UserIdDTO> userRegistration(@Valid @RequestBody UserDTO userDTO){
        Integer userId = userService.saveUser(userDTO);
        UserIdDTO userIdDTO = new UserIdDTO(userId);
        return new ResponseEntity<>(userIdDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public HttpEntity<UserEntity> getUser(@Valid @NotNull @RequestParam Integer userId){
        UserEntity userEntity = userService.getUser(userId);
        return new ResponseEntity<>(userEntity,HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpEntity handleBadException(MethodArgumentNotValidException objException)
    {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
