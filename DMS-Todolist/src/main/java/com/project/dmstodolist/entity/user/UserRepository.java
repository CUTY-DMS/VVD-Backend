package com.project.dmstodolist.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByAccountId(String accountId);

    Optional<User> findByAccountId(String accountId);

}
