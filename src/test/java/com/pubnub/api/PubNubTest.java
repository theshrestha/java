package com.pubnub.api;

import com.pubnub.api.enums.PNReconnectionPolicy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PubNubTest {
    private PubNub pubnub;
    private PNConfiguration pnConfiguration;
// this is also a test
    int i =50;
    @Before
    public void beforeEach() throws IOException {
        pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("demo");
        pnConfiguration.setPublishKey("demo");
    }

    @org.junit.Test
    public void testCreateSuccess() throws IOException, PubNubException {
        pubnub = new PubNub(pnConfiguration);
        Assert.assertEquals(true, pubnub.getConfiguration().isSecure());
        Assert.assertNotNull("pubnub object is null", pubnub);
        Assert.assertNotNull(pubnub.getConfiguration());
        Assert.assertEquals("https://pubsub.pubnub.com", pubnub.getBaseUrl());
    }

    @Test
    public void testEncryptCustomKey() throws PubNubException {
        pubnub = new PubNub(pnConfiguration);
        Assert.assertEquals("iALQtn3PfIXe74CT/wrS7g==", pubnub.encrypt("test1", "cipherKey").trim());

    }

    @Test
    public void testEncryptConfigurationKey() throws PubNubException {
        pnConfiguration.setCipherKey("cipherKey");
        pubnub = new PubNub(pnConfiguration);
        Assert.assertEquals("iALQtn3PfIXe74CT/wrS7g==", pubnub.encrypt("test1").trim());

    }

    @Test
    public void testDecryptCustomKey() throws PubNubException {
        pubnub = new PubNub(pnConfiguration);
        Assert.assertEquals("test1", pubnub.decrypt("iALQtn3PfIXe74CT/wrS7g==", "cipherKey").trim());

    }

    @Test
    public void testDecryptConfigurationKey() throws PubNubException {
        pnConfiguration.setCipherKey("cipherKey");
        pubnub = new PubNub(pnConfiguration);
        Assert.assertEquals("test1", pubnub.decrypt("iALQtn3PfIXe74CT/wrS7g==").trim());

    }

    @org.junit.Test
    public void testPNConfiguration() throws IOException, PubNubException {
        pnConfiguration.setSubscribeTimeout(3000);
        pnConfiguration.setConnectTimeout(4000);
        pnConfiguration.setNonSubscribeRequestTimeout(5000);
        pnConfiguration.setReconnectionPolicy(PNReconnectionPolicy.NONE);
        pubnub = new PubNub(pnConfiguration);

        Assert.assertNotNull("pubnub object is null", pubnub);
        Assert.assertNotNull(pubnub.getConfiguration());
        Assert.assertEquals("https://pubsub.pubnub.com", pubnub.getBaseUrl());
        Assert.assertEquals(3000, pnConfiguration.getSubscribeTimeout());
        Assert.assertEquals(4000, pnConfiguration.getConnectTimeout());
        Assert.assertEquals(5000, pnConfiguration.getNonSubscribeRequestTimeout());
    }

    @org.junit.Test(expected = PubNubException.class)
    public void testDecryptNull() throws PubNubException {
        pnConfiguration.setCipherKey("cipherKey");
        pubnub = new PubNub(pnConfiguration);
        Assert.assertEquals("test1", pubnub.decrypt(null).trim());
    }

    @org.junit.Test(expected = PubNubException.class)
    public void testDecryptNull_B() throws PubNubException {
        pubnub = new PubNub(pnConfiguration);
        Assert.assertEquals("test1", pubnub.decrypt(null, "cipherKey").trim());
    }

    @org.junit.Test
    public void GetVersionAndTimeStamp() throws PubNubException {
        pubnub = new PubNub(pnConfiguration);
        String version = pubnub.getVersion();
        int timeStamp = pubnub.getTimestamp();
        Assert.assertEquals("4.0.9", version);
        Assert.assertTrue(timeStamp > 0);
    }

    @org.junit.Test(expected = PubNubException.class)
    public void testEcryptNull() throws PubNubException {
        pubnub = new PubNub(pnConfiguration);
        Assert.assertEquals("test1", pubnub.encrypt(null));
    }

    @org.junit.Test(expected = PubNubException.class)
    public void testEcryptNull_B() throws PubNubException {
        pubnub = new PubNub(pnConfiguration);
        Assert.assertEquals("test1", pubnub.encrypt(null, "chiperKey"));
    }
}
