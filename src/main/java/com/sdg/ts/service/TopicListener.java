package com.sdg.ts.service;

import com.sdg.ts.model.Topic;
import com.sdg.ts.model.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import twitter4j.*;

import javax.annotation.PostConstruct;
import java.util.Date;

public class TopicListener {

    private static final Logger log = LoggerFactory.getLogger(TopicListener.class);

    private Topic topic;

    @Autowired
    private TweetSink tweetSink;

    public Topic getTopic() {
        return topic;
    }

    @Required
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @PostConstruct
    public void post() {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(new SimpleStatusListener());

        FilterQuery fq = new FilterQuery();
        fq.track(new String[] {topic.getName()});
        twitterStream.filter(fq);
    }

     class SimpleStatusListener implements StatusListener {
            public void onStatus(Status status) {
                Tweet tweet = new Tweet();
                tweet.setUsername(status.getUser().getScreenName());
                tweet.setDate(new Date());
                tweet.setText(status.getText());

                tweetSink.accept(tweet);

            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
               log.warn("Track Limitation Notice :  {}", numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                log.warn("StallWarning: {}", warning.getMessage() );
            }

            public void onException(Exception ex) {
    //           log.error("Exception reading stream", ex);
            }



    }

}
