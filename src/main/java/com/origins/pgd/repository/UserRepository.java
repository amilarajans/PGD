package com.origins.pgd.repository;

import com.origins.pgd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Amila-Kumara on 3/12/2016.
 */
public interface UserRepository extends JpaRepository<User, String> {
}
