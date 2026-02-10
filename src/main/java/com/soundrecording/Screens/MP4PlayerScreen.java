package com.soundrecording.Screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.soundrecording.SoundRecordingMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MP4PlayerScreen extends HandledScreen<MP4PlayerScreenHandler> {
    public static Identifier GUI_TEXTURE =
            Identifier.of(SoundRecordingMod.MOD_ID, "textures/gui/mp4player/mp4player_gui.png");

    public MP4PlayerScreen(MP4PlayerScreenHandler handler, PlayerInventory inventory, Text title){
        super(handler, inventory, title);
    }

    @Override
    protected void init(){
        super.init();
        ButtonWidget buttonWidget = ButtonWidget.builder(Text.of("Start Recording"), (btn) -> {
            this.client.interactionManager.clickButton(handler.syncId, 0);
            MinecraftClient.getInstance().setScreen(null);
        }).dimensions(40, 40, 120, 20).build();
        this.addDrawableChild(buttonWidget);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - backgroundWidth)/2;
        int y = (height - backgroundHeight)/2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }
}
