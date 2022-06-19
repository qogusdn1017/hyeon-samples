/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.bungee

import com.baehyeonwoo.sample.bungee.commands.SampleCommand
import com.baehyeonwoo.sample.bungee.events.SampleBungeeEvent
import com.baehyeonwoo.sample.bungee.objects.SampleBungeeObject.configuration
import com.baehyeonwoo.sample.bungee.objects.SampleBungeeObject.loadConfig
import com.baehyeonwoo.sample.bungee.objects.SampleBungeeObject.saveConfig
import com.baehyeonwoo.sample.bungee.tasks.SampleBungeeTask
import net.md_5.bungee.api.plugin.Plugin
import java.util.concurrent.TimeUnit

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class SampleBungeePluginMain : Plugin() {

    companion object {
        lateinit var bungeeInstance: SampleBungeePluginMain
            private set
    }

    override fun onEnable() {
        bungeeInstance = this
        loadConfig()

        configuration?.set("config-test", "success!")
        saveConfig()

        proxy.pluginManager.registerCommand(this, SampleCommand())
        proxy.pluginManager.registerListener(this, SampleBungeeEvent())
        proxy.scheduler.schedule(this, SampleBungeeTask(), 0L, 1L, TimeUnit.MINUTES)
//        proxy.scheduler.schedule(this, SampleBungeeConfigReloadTask(), 0L, 1L, TimeUnit.SECONDS)
    }
}