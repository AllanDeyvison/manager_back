package com.manager.repositories;

import com.manager.models.PastPasswords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PastPasswordRepository extends JpaRepository<PastPasswords, Integer> {
}
