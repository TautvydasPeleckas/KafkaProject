package kafka2;

import java.util.List;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterController {

public TwitterController()
  {
	  TwitterStream twStream = createStream();
	  StatusListener stListener = createListener();
	  
	  long[] users = new long[]{1214921890785050627L}; //This contains userId which tweets to follow
	  
	  FilterQuery filtre = new FilterQuery();
      filtre.follow(users);
	  
      twStream.addListener(stListener);
	  twStream.filter(filtre);	  
	  //twStream.sample();
		   }
  private TwitterStream createStream () 
  {
	  ConfigurationBuilder cb = new ConfigurationBuilder();
      cb.setDebugEnabled(true).setOAuthConsumerKey("")
              .setOAuthConsumerSecret("")
              .setOAuthAccessToken("")
              .setOAuthAccessTokenSecret("");

      TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
              .getInstance();
      
      return twitterStream;

   }
   private StatusListener createListener () {
	   StatusListener listener = new StatusListener() {
           @Override
           public void onStatus(Status status) {
               System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
               new MyProducer().sendMessage("@" + status.getUser().getScreenName() + " - " + status.getText());
           }

           @Override
           public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
               System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
           }

           @Override
           public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
               System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
           }

           @Override
           public void onScrubGeo(long userId, long upToStatusId) {
               System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
           }

           @Override
           public void onStallWarning(StallWarning warning) {
               System.out.println("Got stall warning:" + warning);
           }

           @Override
           public void onException(Exception ex) {
               ex.printStackTrace();
           }
           };
           return listener;
   
      
  }
  
}
