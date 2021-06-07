package com.gabriellazar.projectmanagement.repository;

import com.gabriellazar.projectmanagement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserNameIgnoreCase(String userName);
    Page<User> findAllByOrderByLastUpdateDateTimeDesc(Pageable pageable);
}
