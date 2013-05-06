package com.sdg.ts.repos;

import com.sdg.ts.model.Sentiment;
import com.sdg.ts.model.Tweet;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/context-services.xml")
public class TweetRepositoryTest {

    @Qualifier("tweetRepository")
    @Autowired
    private TweetRepository tweetRepository;


    @Test
    public void testDeleteByStatusIdAnd() throws Exception {

        Tweet tweet = new Tweet();
        tweet.setText("Test");
        tweet.setStatusId(Long.valueOf(42));
        tweet.setSentiment(Sentiment.neutral());

        tweetRepository.save(tweet);
        Assert.assertEquals(1, tweetRepository.count());

        tweetRepository.deleteByStatusId(tweet.getStatusId());
        Assert.assertEquals(0, tweetRepository.count());


    }
}
