/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.plugin.objects

import com.baehyeonwoo.sample.plugin.SamplePluginMain
import com.baehyeonwoo.sample.plugin.tasks.SampleConfigReloadTask
import com.baehyeonwoo.sample.plugin.tasks.SampleGameTask
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

object SampeGameContentManager {
    val plugin = SamplePluginMain.instance

    val server = plugin.server
    lateinit var event: Listener
    var isRunning = false

    fun startGame() {
        server.pluginManager.registerEvents(event, plugin)
        server.scheduler.runTaskTimer(plugin, SampleGameTask(), 0L, 20 * 60L)
    }

    fun stopGame() {
        HandlerList.unregisterAll(event)
        server.scheduler.cancelTasks(plugin)
        server.scheduler.runTaskTimer(plugin, SampleConfigReloadTask(), 0L, 20L)
    }
}