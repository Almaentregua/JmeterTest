package jmeter.res.controller;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import javax.xml.ws.http.HTTPException;

@RestController
@ControllerAdvice
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "Save user in database")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Ok"),
                    @ApiResponse(code = 400, message = "Bad Request")
            }
    )
    @RequestMapping(value= "/user",method = RequestMethod.POST)
    public HttpEntity<UserIdDTO> userRegistration(@Valid @RequestBody UserDTO userDTO){
        Integer userId = userService.saveUser(userDTO);
        UserIdDTO userIdDTO = new UserIdDTO(userId);
        return new ResponseEntity<>(userIdDTO, HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Ok"),
                    @ApiResponse(code = 400, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "Not Found")
            }
    )
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public HttpEntity<UserEntity> getUser(@Valid @NotNull @RequestParam Integer userId){
        UserEntity userEntity = userService.getUser(userId);
        return new ResponseEntity<>(userEntity,HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpEntity handleBadException(MethodArgumentNotValidException ex)
    {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HTTPException.class)
    public HttpEntity handleNotFoundException(HTTPException ex){
        if (ex.getStatusCode() == 404){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
