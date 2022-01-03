package com.axst;

import com.axst.utils.Logger;

public class Client {
    public static Client instance;
    public static String name = "Axst Client", version = "master";

    public static void onPreInit(){
        Logger.info("Client Started");

    }

    public static void onShutdown(){
        Logger.info("Client Shutdown");
    }
}
