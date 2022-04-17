/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.plugin

import com.baehyeonwoo.sample.plugin.commands.SampleKommand.register
import com.baehyeonwoo.sample.plugin.config.SampleConfig.load
import com.baehyeonwoo.sample.plugin.events.SampleEvent
import com.baehyeonwoo.sample.plugin.objects.SampleObject.event
import com.baehyeonwoo.sample.plugin.tasks.SampleTask
import io.github.monun.kommand.kommand
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class SamplePluginMain : JavaPlugin() {

    companion object {
        lateinit var instance: SamplePluginMain
            private set
    }

    private val configFile = File(dataFolder, "config.yml")

    override fun onEnable() {
        instance = this
        load(configFile)

        event = SampleEvent()

        server.pluginManager.registerEvents(event, this)
        server.scheduler.runTaskTimer(this, SampleTask(), 0L, 20 * 60L)
//        server.scheduler.runTaskTimer(this, SampleConfigReloadTask(), 0L, 20L)

        kommand {
            register("sample") {
                requires { isOp }
                register(this)
            }
        }
    }
}