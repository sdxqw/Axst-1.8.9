package com.axst;

import com.axst.utils.DiscordIPC;
import com.axst.utils.LoggerUtil;
import com.axst.utils.SessionChanger;
import com.axst.utils.fonts.CustomFontRenderer;

public class Client {
    public static Client instance;
    public static String name = "Axst Client", version = "Master-1.8.9";
    public static CustomFontRenderer textRenderer;

    public static void onPreInit(){
        DiscordIPC.INSTANCE.init();
        SessionChanger.getInstance().setUserOffline("xNtrowIsBaka");
    }

    public static void start(){
        Client.textRenderer = new CustomFontRenderer("Main Menu", 15.0f);
        LoggerUtil.info("Client Started");
    }

    public static void onShutdown(){
        LoggerUtil.info("Client Shutdown");
    }
}
