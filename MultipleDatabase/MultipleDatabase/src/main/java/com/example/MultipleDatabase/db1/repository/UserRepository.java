package com.example.MultipleDatabase.db1.repository;


import com.example.MultipleDatabase.db1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}


