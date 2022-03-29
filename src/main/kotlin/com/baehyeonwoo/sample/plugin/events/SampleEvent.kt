/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.plugin.events

import com.baehyeonwoo.sample.plugin.objects.SampeGameContentManager.plugin
import com.baehyeonwoo.sample.plugin.objects.SampeGameContentManager.server
import io.github.monun.tap.effect.playFirework
import net.kyori.adventure.text.Component.text
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class SampleEvent : Listener {
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val loc = e.player.location.add(0.0, 0.5, 0.0)
        val firework = FireworkEffect.builder().with(FireworkEffect.Type.STAR).withColor(Color.AQUA).build()

        if (plugin.config.getString("administrator").toString().contains(e.player.uniqueId.toString())) {
            server.broadcast(text("Tap Config Sample: Admin Join"))
        }

        plugin.logger.info("Hello World!")
        e.player.sendMessage(text("Hello World!"))
        loc.world.playFirework(loc, firework)
    }
}