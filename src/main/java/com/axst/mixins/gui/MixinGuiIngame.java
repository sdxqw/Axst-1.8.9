package com.axst.mixins.gui;

import com.axst.ui.GuiSomethings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Redirect(method = "renderGameOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;showCrosshair()Z"))
    public boolean injectCrosshair(GuiIngame guiIngame) {
        return !(Minecraft.getMinecraft().currentScreen instanceof GuiSomethings);
    }
}
