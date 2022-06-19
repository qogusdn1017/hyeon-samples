/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.bungee.objects

import com.baehyeonwoo.sample.bungee.SampleBungeePluginMain
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.config.Configuration
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import java.io.File
import java.io.IOException

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

object SampleBungeeObject {
    val bgPlugin = SampleBungeePluginMain.bungeeInstance

    val bgServer: ProxyServer = bgPlugin.proxy

    private lateinit var file: File
    var configuration: Configuration? = null

    fun loadConfig() {
        if (!bgPlugin.dataFolder.exists()) bgPlugin.dataFolder.mkdirs()

        file = File(bgPlugin.dataFolder, "config.yml")
        try {
            if (!file.exists()) {
                file.createNewFile()
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration::class.java).load(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration::class.java).save(configuration, file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}