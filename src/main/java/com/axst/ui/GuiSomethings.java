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
        ((IEnitityRenderer) mc.entityRenderer).invokeLoadShader(new ResourceLocation("shaders/post/blur.json"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Client.textRenderer.drawStringWithShadow(mc.getSession().getUsername(), 28, 11, new Color(255,255,255).getRGB());
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        ResourceLocation headLocation = GuiUtils.getHeadLocation(mc.getSession().getUsername());
        mc.getTextureManager().bindTexture(headLocation);
        drawModalRectWithCustomSizedTexture(12, 9, 0, 0, 12, 12, 12, 12);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {
        Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);
        super.onGuiClosed();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
