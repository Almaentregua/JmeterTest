package jmeter.service;

import jmeter.entity.UserEntity;
import jmeter.repository.UserRepo;
import jmeter.res.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public Integer saveUser(UserDTO user){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setLastname(user.getLastname());
        userEntity.setAge(user.getAge());
        userEntity = userRepo.save(userEntity);
        return  userEntity.getUserId();
    }

    public UserEntity getUser(Integer userId){
        UserEntity userEntity = userRepo.findByUserId(userId);
        return userEntity;
    }
}
