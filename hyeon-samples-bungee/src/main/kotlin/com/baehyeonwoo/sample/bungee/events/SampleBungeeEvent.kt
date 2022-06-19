/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.bungee.events

import com.baehyeonwoo.sample.bungee.objects.SampleBungeeObject.bgServer
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.event.ServerConnectEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class SampleBungeeEvent : Listener {
    @EventHandler
    fun onServerConnect(e: ServerConnectEvent) {
        val p = requireNotNull(e.player)

        bgServer.broadcast(TextComponent("${ChatColor.GREEN}Hello ${p.name}!"))
    }
}