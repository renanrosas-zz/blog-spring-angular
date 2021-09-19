package com.renanrosas.blogspringangular.repositories;

import com.renanrosas.blogspringangular.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
