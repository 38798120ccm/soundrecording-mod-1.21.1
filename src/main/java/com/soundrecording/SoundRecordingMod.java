package com.soundrecording;

import com.soundrecording.Componets.ModComponets;
import com.soundrecording.Items.ModItems;
import com.soundrecording.Payload.ItemStackPayload;
import com.soundrecording.Screens.ModScreenHandler;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoundRecordingMod implements ModInitializer {
	public static final String MOD_ID = "soundrecording-mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModComponets.initialize();
		ModItems.initialize();
		ModScreenHandler.initialize();

		LOGGER.info("Hello Fabric world!");
	}
}