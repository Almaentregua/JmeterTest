package jmeter.service;

import jmeter.entity.UserEntity;
import jmeter.repository.UserRepo;
import jmeter.res.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    /**
     * Save user in database and return userId
     * @param user
     * @return Integer
     */
    public Integer saveUser(UserDTO user){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setLastname(user.getLastname());
        userEntity.setAge(user.getAge());
        userEntity = userRepo.save(userEntity);
        return  userEntity.getUserId();
    }

    /**
     * Get user from database
     * @param userId
     * @return UserEntity
     */
    public UserEntity getUser(Integer userId){
        UserEntity userEntity = userRepo.findByUserId(userId);
        if(isEmpty(userEntity)){
            throw new HTTPException(404);
        }
        return userEntity;
    }
}
