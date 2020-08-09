package com.wxw.boot.repository;

import com.wxw.boot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @ Author ：wxw.
 * @ Date ： 13:06 2020/7/10
 * @ Description：持久层 基于jpa
 * @ Version:   v_0.0.1
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);


    /*
     * 我们在这里直接继承 JpaRepository
     * 这里面已经有很多现场的方法了
     * 这也是JPA的一大优点
     *
     * */

}
