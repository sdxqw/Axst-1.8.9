package com.axst.mixins;

import com.axst.Client;
import com.axst.event.impl.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.InputStream;
import java.nio.ByteBuffer;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Shadow
    @Final
    private DefaultResourcePack mcDefaultResourcePack;

    @Inject(method = "createDisplay", at = @At("RETURN"))
    public void injectCreateDisplay(CallbackInfo ci){
        // its unknow bc idk how to add git commit id lol
        Display.setTitle(Client.name + " (" + Client.version + "/unknow" + ")");
    }

    @Inject(method = "startGame", at = @At("HEAD"))
    public void injectStartGame1(CallbackInfo ci) {
        Client.instance.onPreInit();
    }

    @Inject(method = "startGame", at = @At("RETURN"))
    public void injectStartGame2(CallbackInfo ci) {
        Client.instance.onStart();
    }

    @Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
    public void injectShutdownMinecraftApplet(CallbackInfo ci) {
        Client.instance.onShutdown();
    }

    @Inject(method = "runTick", at = @At("RETURN"))
    private void runTick(CallbackInfo info)
    {
        new TickEvent().call();
    }

    /**
     * @author Kaimson the Clown.
     */
    @Overwrite
    private void setWindowIcon() {
        if (Util.getOSType() != Util.EnumOS.OSX) {
            try {
                InputStream inputStream = MixinMinecraft.class.getResourceAsStream("/assets/minecraft/axst/icons/windows/icon16.png");
                InputStream inputStream2 = MixinMinecraft.class.getResourceAsStream("/assets/minecraft/axst/icons/windows/icon32.png");
                if (inputStream == null) {
                    inputStream = this.mcDefaultResourcePack.getInputStreamAssets(new ResourceLocation("icons/icon_16x16.png"));
                }
                if (inputStream2 == null) {
                    inputStream2 = this.mcDefaultResourcePack.getInputStreamAssets(new ResourceLocation("icons/icon_32x32.png"));
                }
                Display.setIcon(new ByteBuffer[] { this.readImageToBuffer(inputStream), this.readImageToBuffer(inputStream2) });
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Shadow
    protected abstract ByteBuffer readImageToBuffer(final InputStream p0);
}
