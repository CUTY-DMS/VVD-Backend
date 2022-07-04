package com.project.dmstodolist.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

   // Optional<User> findByUsername
}
