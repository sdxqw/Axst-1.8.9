package com.axst.mixins.interfaces;

import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityRenderer.class)
public interface IEnitityRenderer {
    @Invoker("loadShader")
    void invokeLoadShader(ResourceLocation resourceLocationIn);
}
