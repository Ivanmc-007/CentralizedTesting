package com.ivan.CT.repositories;

import com.ivan.CT.entities.Choice;
import com.ivan.CT.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser,Long> {
    MyUser findByLogin(String login);

    Optional<MyUser> findByActivationCode(String activationCode);

//    SELECT DISTINCT my_user.id, my_user.login
//    FROM my_user
//    INNER JOIN choice ON choice.my_user_id = my_user.id
//    where choice.date_creation between '2020-03-15' and '2020-03-20';

    @Query("SELECT DISTINCT u FROM MyUser u JOIN u.choices as ch WHERE ch.dateCreation between :startDate AND :endDate")
    List<MyUser> findByChoiceByDateCreation(@Param("startDate") java.sql.Date startDate, @Param("endDate")java.sql.Date endDate);

}
