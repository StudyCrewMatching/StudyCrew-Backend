package com.studycrew.studycrew_backend.domain.profile.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studycrew.studycrew_backend.domain.profile.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
