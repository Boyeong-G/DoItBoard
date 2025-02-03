package com.mysite.sbb.repository;

import com.mysite.sbb.entity.SiteUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUserEntity, Long> {
    Optional<SiteUserEntity> findByUsername(String username);
}
