package com.axst;

import com.axst.utils.Logger;
import com.axst.utils.fonts.FontUtil;
import net.minecraft.util.ResourceLocation;

public class Client {
    public static Client instance;
    public static ResourceLocation Background;
    public static String name = "Axst Client", version = "Master-1.8.9";

    public static void onPreInit(){
        FontUtil.bootstrap();
        Logger.info("Client Started");
    }

    public static void onShutdown(){
        Logger.info("Client Shutdown");
    }

    static {
        Background = new ResourceLocation("axst/bg.png");
    }
}
