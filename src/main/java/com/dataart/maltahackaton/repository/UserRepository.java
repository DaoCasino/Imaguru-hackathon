package com.dataart.maltahackaton.repository;

import com.dataart.maltahackaton.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
