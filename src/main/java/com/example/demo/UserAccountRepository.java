package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    /*
     * findBy<column_name>.
     */
    List<UserAccount> findByUsername(String username);
    List<UserAccount> findById(String id);
    List<UserAccount> findByThing3(String thing3);

    /*
     * findBy<column_name_1>And<column_name_2>.
     */
    List<UserAccount> findByThing1AndThing2(String thing1, String thing2);
    List<UserAccount> findByUsernameAndPassword(String username, String password);
    @Transactional
    void deleteByUsernameAndPassword(String username, String password);

    @Transactional
    void deleteByUsername(String username);

    @Transactional
    void deleteById(String id);

}