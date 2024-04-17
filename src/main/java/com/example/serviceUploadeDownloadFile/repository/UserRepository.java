package com.example.serviceUploadeDownloadFile.repository;

import com.example.serviceUploadeDownloadFile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
