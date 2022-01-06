package com.axst.ui;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class AccountManager {

    //TODO learn how tf i can do that

    private static final ResourceLocation locationStevePng = new ResourceLocation("textures/entity/steve.png");

    public ResourceLocation getHead(GameProfile profile) {
        Minecraft mc = Minecraft.getMinecraft();
        SkinManager skinManager = Minecraft.getMinecraft().getSkinManager();
        Map cache = skinManager.loadSkinFromCache(profile);
        cache.putAll(mc.getSessionService().getTextures(mc.getSessionService().fillProfileProperties(profile, false), false));
        if (cache.size() == 0) {
            return AccountManager.locationStevePng;
        }
        return skinManager.loadSkin((MinecraftProfileTexture)cache.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
    }

}
