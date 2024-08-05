package org.zerock.api00.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.api00.domain.APIUser;

public interface APIUserRepository extends JpaRepository<APIUser, String> {
}
