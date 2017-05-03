package edu.iis.mto.integration.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.iis.mto.integration.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
