/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.bungee.tasks

import com.baehyeonwoo.sample.bungee.objects.SampleBungeeObject.bgPlugin
import com.baehyeonwoo.sample.bungee.objects.SampleBungeeObject.loadConfig
import com.baehyeonwoo.sample.bungee.objects.SampleBungeeObject.saveConfig
import java.io.File

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

// REMOVE SUPPRES WHEN USING
@Suppress("UNUSED")
class SampleBungeeConfigReloadTask: Runnable {
    private val configFile = File(bgPlugin.dataFolder, "config.yml")

    private var configFileLastModified = configFile.lastModified()

    override fun run() {
        if (configFileLastModified != configFile.lastModified()) {
            bgPlugin.logger.info("Config Reloaded.")
            loadConfig()
            saveConfig()

            configFileLastModified = configFile.lastModified()
        }
    }
}
