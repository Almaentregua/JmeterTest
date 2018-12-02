package jmeter.repository;

import jmeter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    @Override
    <S extends UserEntity> S save(S entity);
    UserEntity findByUserId(Integer userId);
}
