package com.axst.mixins.gui;

import com.axst.Client;
import com.axst.ui.GuiButtons;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu extends GuiScreen {

    /**
     * InitGui Overwrite.
     *
     * @author xNtrow
     */
    @Overwrite
    public void initGui(){
        buttonList.add(new GuiButtons(1, width / 2 - 92, height / 2 - 16, 180, 20, "S I N G L E P L A Y E R"));
        buttonList.add(new GuiButtons(2, width / 2 - 92, height / 2 + 8, 180, 20, "M U L T I P L A Y E R"));
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
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(Client.Background);
        Gui.drawModalRectWithCustomSizedTexture(-21 + (Mouse.getX() / 90), ((Mouse.getY() * -1 / 90)), 0, 0, width + 20, height + 20, width + 21, height + 20);
        GlStateManager.popMatrix();
        this.drawString(this.fontRendererObj, "Axst Client 1.8.9", 2, this.height - 10, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
