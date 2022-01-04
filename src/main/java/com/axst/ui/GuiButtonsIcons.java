package com.axst.ui;

import com.axst.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiButtonsIcons extends GuiButtons {

    public ResourceLocation ICON;
    public int sizeIcon;

    public GuiButtonsIcons(int buttonId, int x, int y, int widthIn, int heightIn, String iconName, int sizeIcon) {
        super(buttonId, x, y, widthIn, heightIn, "");
        ICON = new ResourceLocation("axst/icons/"+iconName);
        this.sizeIcon = sizeIcon;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY){
        GlStateManager.enableAlpha();
        mc.getTextureManager().bindTexture(ICON);
        this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
        GuiUtils.drawRoundOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 4.0f, 2.0f, this.enabled ? (this.hovered ? new Color(0, 0, 0, 170).getRGB() : new Color(0, 0, 0, 220).getRGB()) : new Color(0, 0, 0, 240).getRGB());
        GuiUtils.drawRoundRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 7.0f, this.enabled ? (this.hovered ? new Color(0, 0, 0, 100).getRGB() : new Color(30, 30, 30, 100).getRGB()) : new Color(70, 70, 70, 50).getRGB());
        this.mouseDragged(mc, mouseX, mouseY);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.blendFunc(770, 771);
        Gui.drawModalRectWithCustomSizedTexture(this.xPosition + (this.width - sizeIcon) / 2, this.yPosition + (this.height - sizeIcon) / 2, 0, 0, sizeIcon, sizeIcon, (float) sizeIcon, (float) sizeIcon);
    }
    public int getButtonWidth()
    {
        return this.width;
    }
}
