import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNLogVerbosity;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.util.Arrays;

/**
 * Created by Max on 8/26/16.
 */
public class Testyz {

    public static void main(String[] args) {
        PNConfiguration pnConfiguration = new PNConfiguration()
                .setSubscribeKey("sub-c-d048d86e-0c1a-11e6-bb6c-02ee2ddab7fe")
                .setLogVerbosity(PNLogVerbosity.BODY)
                .setPublishKey("pub-c-0d53e85f-3887-4aba-b651-ec662a6954f4");

        PubNub pubNub = new PubNub(pnConfiguration);


        pubNub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {

            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                int moose = 10;
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });

        try {
            pubNub.addChannelsToChannelGroup().channelGroup("max-cg2").channels(Arrays.asList("max-ch10")).sync();
            pubNub.publish().channel("max-ch10").message("hi").sync();
        } catch (PubNubException e) {
            e.printStackTrace();
        }

        pubNub.subscribe().channels(Arrays.asList("max-ch10")).execute();
        try {
            pubNub.publish().channel("max-ch10").message("hi").sync();
        } catch (PubNubException e) {
            e.printStackTrace();
        }


    }

}
