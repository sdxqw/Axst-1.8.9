package com.axst.mixins;

import com.axst.Client;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "createDisplay", at = @At("RETURN"))
    public void injectCreateDisplay(CallbackInfo ci){
        Display.setTitle(Client.name + " (" + Client.version + "/unknown" + ")");
    }

    @Inject(method = "startGame", at = @At("RETURN"))
    public void injectStartGame(CallbackInfo ci) {
        Client.onPreInit();

    }

    @Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
    public void injectShutdownMinecraftApplet(CallbackInfo ci) {
        Client.onShutdown();
    }
}
