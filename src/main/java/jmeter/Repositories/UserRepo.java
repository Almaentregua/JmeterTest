package jmeter.Repositories;

import jmeter.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    @Override
    <S extends UserEntity> S save(S entity);
    UserEntity findByUserId(Integer userId);
}
