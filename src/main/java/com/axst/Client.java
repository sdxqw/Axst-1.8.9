package com.axst;

import com.axst.event.EventManager;
import com.axst.event.EventTarget;
import com.axst.event.impl.TickEvent;
import com.axst.ui.GuiSomethings;
import com.axst.utils.DiscordIPC;
import com.axst.utils.Keybind;
import com.axst.utils.LoggerUtil;
import com.axst.utils.SessionChanger;
import com.axst.utils.fonts.CustomFontRenderer;
import net.minecraft.client.Minecraft;

public class Client {
    public static Client instance = new Client();
    public static String name = "Axst Client", version = "Master-1.8.9";
    public static CustomFontRenderer textRenderer;
    public static final Minecraft mc = Minecraft.getMinecraft();
    public EventManager eventManager;
    public static Keybind keyBind;

    public void onPreInit(){
        DiscordIPC.INSTANCE.init();
        SessionChanger.getInstance().setUserOffline("SuchSpeed");
    }

    public void onStart(){
        eventManager = new EventManager();
        keyBind = new Keybind();
        Client.textRenderer = new CustomFontRenderer("Main Menu", 15.0f);
        LoggerUtil.info("Client Started");
        EventManager.register(this);
    }

    public void onShutdown(){
        LoggerUtil.info("Client Shutdown");
    }

    @EventTarget
    public void onTick(TickEvent event)
    {
        if (Keybind.OpenSomeThings.isPressed())
        {
            mc.displayGuiScreen(new GuiSomethings());
        }
    }

}
