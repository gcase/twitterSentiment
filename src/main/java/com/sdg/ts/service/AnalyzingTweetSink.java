package com.sdg.ts.service;


import com.sdg.ts.model.Sentiment;
import com.sdg.ts.model.Tweet;
import com.sdg.ts.repos.TweetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import java.util.concurrent.*;

public class AnalyzingTweetSink implements TweetSink {

    private static final Logger logger = LoggerFactory.getLogger(AnalyzingTweetSink.class);

    @Autowired
    private TweetRepository tweetRepository;

    private SentimentAnalyzer analyzer;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final SentimentStats stats = new SentimentStats();
    private final Semaphore available = new Semaphore(100, true);

    @Override
    public void accept(Tweet tweet) {
        logger.info("{} : {}", tweet.getUsername(), tweet.getText());

        if (available.availablePermits() == 0) {
            logger.info("All Done!");
            logger.info(stats.toString());
        }

        try {
            available.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Future<Sentiment> result = executor.submit (new TweetAnalyzeCallable(tweet));
        Sentiment sentiment = null;
        try {
            sentiment = result.get();
            tweet.setSentiment(sentiment);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        finally {
            available.release();
        }
        logger.info("{} Sentiment: {}", tweet.getText(), sentiment);


        tweetRepository.save(tweet);
    }

    public SentimentAnalyzer getAnalyzer() {
        return analyzer;
    }

    @Required
    public void setAnalyzer(SentimentAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    class TweetAnalyzeCallable implements Callable<Sentiment> {

        private final Tweet tweet;

        TweetAnalyzeCallable (Tweet tweet) {
            this.tweet = tweet;
        }

        @Override
        public Sentiment call() throws Exception {
           return analyzer.analyze(tweet.getText());
        }
    }

}

