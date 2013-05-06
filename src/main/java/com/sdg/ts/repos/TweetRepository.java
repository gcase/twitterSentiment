package com.sdg.ts.repos;


import com.sdg.ts.model.Tweet;
import org.springframework.data.repository.CrudRepository;

public interface TweetRepository extends CrudRepository<Tweet, Long>{
}
