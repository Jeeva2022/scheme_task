package com.example.scheme.repository;

import com.example.scheme.entity.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SchemeRepository extends JpaRepository<Scheme,Long> {


    @Query(value = "select * from schemes where scheme_name like ? ",nativeQuery = true)
    List<Scheme> findBySchemeName(String schemeName);

}
