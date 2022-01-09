package com.axst.ui;

import com.axst.Client;
import com.axst.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.awt.*;

public class GuiButtons extends GuiButton {

    public GuiButtons(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(this.visible) {
            this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
            GuiUtils.drawRoundOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 4.0f, 2.0f, this.enabled ? (this.hovered ? new Color(0, 0, 0, 170).getRGB() : new Color(0, 0, 0, 220).getRGB()) : new Color(0, 0, 0, 240).getRGB());
            GuiUtils.drawRoundRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 7.0f, this.enabled ? (this.hovered ? new Color(0, 0, 0, 100).getRGB() : new Color(30, 30, 30, 100).getRGB()) : new Color(70, 70, 70, 50).getRGB());
            Client.textRenderer.drawCenteredString(this.displayString, this.xPosition + (this.width >> 1), this.yPosition + ((this.height - 8) >> 1), new Color(255,255,255).getRGB());
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}
