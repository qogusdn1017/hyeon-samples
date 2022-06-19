/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.plugin.objects

import com.baehyeonwoo.sample.plugin.SamplePluginMain
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

object SampleObject {
    val plugin = SamplePluginMain.instance

    val server = plugin.server
    lateinit var event: Listener
}