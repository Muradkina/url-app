package com.mk.urlShorttenerApplication.repository;

import com.mk.urlShorttenerApplication.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortUrlRepo extends JpaRepository<ShortUrl,Long> {
    Optional<ShortUrl> findAllByCode(String code);
}
