package com.brm.mycollegues.repo;

import com.brm.mycollegues.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {

    Optional<Person>findByUsername(String username);
    Optional<Person>findByUsernameAndPassword(String username, String password);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE  Person p SET  p.is_online = :is_online WHERE p.username = :username")
    void updateStatus(@Param("username") String username, @Param("is_online") Boolean is_online);

}
