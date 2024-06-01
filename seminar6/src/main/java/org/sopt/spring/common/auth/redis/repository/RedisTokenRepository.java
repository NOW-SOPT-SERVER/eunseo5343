package org.sopt.spring.common.auth.redis.repository;

import org.sopt.spring.common.auth.redis.domain.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RedisTokenRepository extends CrudRepository<Token, String> {
    Optional<Token> findByRefreshToken(final String refreshToken);
    Optional<Token> findById(final Long id);
} 
