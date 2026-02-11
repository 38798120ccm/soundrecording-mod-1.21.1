package com.soundrecording;

import com.soundrecording.Screens.MP4PlayerScreen;
import com.soundrecording.Screens.ModScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class SoundRecordingModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandler.MP4PLAYER_SCREEN_HANDLER, MP4PlayerScreen::new);
    }

}
