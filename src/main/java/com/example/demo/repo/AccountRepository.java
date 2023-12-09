package com.example.demo.repo;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findByUser(User user);

    Account findByAccountId(UUID accountId);

}
