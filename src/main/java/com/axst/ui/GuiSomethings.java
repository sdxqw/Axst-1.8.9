package com.axst.ui;

import com.axst.Client;
import com.axst.mixins.interfaces.IEnitityRenderer;
import com.axst.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class GuiSomethings extends GuiScreen {

    @Override
    public void initGui() {
        // bruh blur is fucked
        ((IEnitityRenderer) mc.entityRenderer).invokeLoadShader(new ResourceLocation("shaders/post/blur.json"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // just for fun xd (https://discord.gg/4znxNdjav8)
        //Client.textRenderer.drawStringWithShadow("CUM-FPS: " + Minecraft.getDebugFPS() * 999999999, this.width / 2 - 100, this.height / 2 - 40, new Color(255,255,255).getRGB());
        Client.textRenderer.drawStringWithShadow(mc.getSession().getUsername(), 28, 11, new Color(255,255,255).getRGB());
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        ResourceLocation headLocation = GuiUtils.getHeadLocation(mc.getSession().getUsername());
        mc.getTextureManager().bindTexture(headLocation);
        drawModalRectWithCustomSizedTexture(12, 9, 0, 0, 12, 12, 12, 12);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {
        // bruh blur is fucked
        Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);
        super.onGuiClosed();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
