package com.phoenix.UrlShortener.repository;

import com.phoenix.UrlShortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>
{
}
