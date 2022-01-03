package com.axst;

import com.axst.utils.Logger;

public class Client {
    public static Client instance;
    public static Logger log;
    public static String name = "Axst Client", version = "master";

    public static void onPreInit(){
        log.info("Client Started");

    }

    public static void onShutdown(){
        log.info("Client Shutdown");
    }
}
