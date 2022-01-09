package com.axst.mixins.gui;

import com.axst.Client;
import com.axst.ui.GuiButtons;
import com.axst.ui.GuiButtonsIcons;
import com.axst.utils.DiscordIPC;
import com.axst.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.io.IOException;

@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu extends GuiScreen {

    /**
     * i love skid minecraft class!
     */
    public ResourceLocation LOGO = new ResourceLocation("axst/axst.png");
    public ResourceLocation BACKGROUND = new ResourceLocation("axst/bg.png");
    String copyright = "Copyright Mojang AB. Do not distribute!";
    String watermark = "Axst Client 1.8.9";
    Color color;
    Color colorFont = new Color(255, 255, 255,255);

    /**
     * InitGui Overwrite.
     *
     * @author xNtrow
     */
    @Overwrite
    public void initGui(){
        DiscordIPC.INSTANCE.update("Idle (Main Menu)","IGN: " + Minecraft.getMinecraft().getSession().getUsername());
        buttonList.add(new GuiButtonsIcons(0, width / 2 + 1, height / 2 + 33, 22, 22, "settings.png", 16));
        buttonList.add(new GuiButtons(1, width / 2 - 92, height / 2 - 16, 180, 20, "S I N G L E P L A Y E R"));
        buttonList.add(new GuiButtons(2, width / 2 - 92, height / 2 + 8, 180, 20, "M U L T I P L A Y E R"));
        buttonList.add(new GuiButtonsIcons(4, width - 24, 2, 22, 22, "exit.png", 16));
        buttonList.add(new GuiButtonsIcons(5, width / 2 + 24, height / 2 + 33, 22, 22, "language.png", 16));
        buttonList.add(new GuiButtonsIcons(100, width / 2 - 45, height / 2 + 33, 22, 22, "axstsettings.png", 16));
        buttonList.add(new GuiButtonsIcons(101, width / 2 - 22, height / 2 + 33, 22, 22, "cosmetics.png", 16));
    }


    /**
     * MainMenu Method Overwrite.
     *
     * @description Gui.drawModalRectWithCustomSizedTexture its made by (Fireentr#5845)
     * @author xNtrow
     */
    @Overwrite
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F,1.0F);
        this.mc.getTextureManager().bindTexture(BACKGROUND);
        Gui.drawModalRectWithCustomSizedTexture(-22 + (Mouse.getX() / 90), ((Mouse.getY() * -1 / 90)), 0, 0, width + 20, height + 20, width + 21, height + 20);
        this.mc.getTextureManager().bindTexture(LOGO);
        Gui.drawScaledCustomSizeModalRect(width / 2 - 60, height / 2 - 115, 0,0, 120,120,120,120,120,120);
        if(mc.getSession().getUsername() != null){
            boolean hovering = mouseX >= 8 && mouseX <= 35 + Client.textRenderer.getWidth(mc.getSession().getUsername()) && mouseY <= 22;
            if(hovering){
                color = new Color(191, 191, 191, 65);
            }else{
                color = new Color(0, 0, 0, 76);
            }
            Client.textRenderer.drawStringWithShadow(mc.getSession().getUsername(), 28, 11, colorFont.getRGB());
            GuiUtils.drawRoundOutline(8, 7, (int) ((float)36 + Client.textRenderer.getWidth(mc.getSession().getUsername())), 23, 4f, 1.0f,color.getRGB());
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            ResourceLocation headLocation = GuiUtils.getHeadLocation(mc.getSession().getUsername());
            mc.getTextureManager().bindTexture(headLocation);
            drawModalRectWithCustomSizedTexture(12, 9, 0,0,12,12, 12, 12);
        }
        GlStateManager.popMatrix();
        Client.textRenderer.drawString(watermark,4, this.height - 15, -1);
        Client.textRenderer.drawString(copyright, this.width - this.fontRendererObj.getStringWidth(copyright) + 62, this.height - 15, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Inject(method = "actionPerformed", at = @At("RETURN"))
    public void injectActionPerformed(GuiButton button, CallbackInfo ci){
        switch(button.id){
            case 100:
                //axst settings
                break;
            case 101:
                //cosmetics
                break;
        }
    }

    @Inject(method = "mouseClicked", at = @At("RETURN"))
    public void mouseClicked(int mouseX, int mouseY, int mouseButton, CallbackInfo ci) throws IOException {
        boolean clicked = ((mouseX >= 8)
                && (mouseX <= (35 + Client.textRenderer.getWidth(mc.getSession().getUsername()))))
                && ((mouseX >= 10)
                && (mouseY <= 22)
                && (Mouse.isButtonDown(mouseButton)));
        if(clicked){
            mc.displayGuiScreen((null));
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
