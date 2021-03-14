package com.gabriellazar.projectmanagement.repository;

import com.gabriellazar.projectmanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);
    Page<User> findAllByOrderByIdDesc(Pageable pageable);
}
