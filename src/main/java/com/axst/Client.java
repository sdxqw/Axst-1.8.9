package com.axst;

import com.axst.utils.DiscordIPC;
import com.axst.utils.LoggerUtil;
import com.axst.utils.fonts.FontUtil;

public class Client {
    public static Client instance;
    public static String name = "Axst Client", version = "Master-1.8.9";

    public static void onPreInit(){
        DiscordIPC.INSTANCE.init();
    }

    public static void start(){
        FontUtil.bootstrap();
        LoggerUtil.info("Client Started");
    }

    public static void onShutdown(){
        LoggerUtil.info("Client Shutdown");
    }
}
