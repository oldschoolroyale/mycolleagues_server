package com.brm.mycollegues.repo;

import com.brm.mycollegues.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Register,Long> {

    Optional<Register>findByUsernameAndPassword(String username ,String password);
    Optional<Register>findByUsername(String username);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE  Register r SET  r.is_online = :is_online WHERE r.username = :username")
    void updateStatus(@Param("username") String username, @Param("is_online") Boolean is_online);


}
