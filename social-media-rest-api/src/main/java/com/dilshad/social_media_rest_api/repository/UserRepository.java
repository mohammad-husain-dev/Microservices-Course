package com.dilshad.social_media_rest_api.repository;

import com.dilshad.social_media_rest_api.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
