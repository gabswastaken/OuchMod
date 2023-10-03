package me.Ouch.learning

import net.minecraft.client.Minecraft
import net.weavemc.loader.api.ModInitializer
import net.weavemc.loader.api.event.EventBus
import net.weavemc.loader.api.event.SubscribeEvent
import net.weavemc.loader.api.event.TickEvent

class OuchMod : ModInitializer {
    override fun preInit() {
        // Register this class as a subscriber
        EventBus.subscribe(this)
    }

    var prevHealth: Float = 0f
    @SubscribeEvent
    fun onTick(event: TickEvent.Post) {
        // Get the local player
        val player = Minecraft.getMinecraft().thePlayer

        // Check if the player is not null
        if (player != null) {
            // Get the current health of the player
            val currentHealth = player.health

            // Check if the player is not dead
            if (player.deathTime == 0) {
                // Check if the current health is less than the previous health
                if (currentHealth < prevHealth) {
                    // Send a chat message that says "ouch"
                    player.sendChatMessage("ouch")
                }
            }

            prevHealth = currentHealth
        }
    }
}