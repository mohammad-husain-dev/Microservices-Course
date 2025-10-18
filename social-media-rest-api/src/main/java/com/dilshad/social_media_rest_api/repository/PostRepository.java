package com.dilshad.social_media_rest_api.repository;

import com.dilshad.social_media_rest_api.beans.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
