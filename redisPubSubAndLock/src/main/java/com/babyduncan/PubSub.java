package com.babyduncan;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.logging.Logger;

/**
 * Created by zhaoguohao on 2018/2/5.
 */
public class PubSub {


    private static final Logger log = Logger.getLogger(PubSub.class.toString());

    /**
     * 两个线程同时订阅,所有订阅的都会收到
     *
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.auth("esf.focus.cn");
        jedis.psubscribe(new JedisPubSub() {
            @Override
            public void onPSubscribe(String pattern, int subscribedChannels) {
                log.info("onPSubscribe "
                        + pattern + " " + subscribedChannels);
            }

            @Override
            public void onPMessage(String pattern, String channel, String message) {
                log.info("onPMessage :" + message);
            }
        }, "__keyevent@0__:expired");
    }
}
