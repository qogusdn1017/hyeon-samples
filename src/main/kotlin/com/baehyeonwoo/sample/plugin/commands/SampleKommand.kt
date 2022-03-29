/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.plugin.commands

import com.baehyeonwoo.sample.plugin.objects.SampeGameContentManager.isRunning
import com.baehyeonwoo.sample.plugin.objects.SampeGameContentManager.startGame
import com.baehyeonwoo.sample.plugin.objects.SampeGameContentManager.stopGame
import io.github.monun.kommand.node.LiteralNode
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

object SampleKommand {
    fun register(builder: LiteralNode) {
        builder.apply {
            then("start") {
                executes {
                    if (!isRunning) startGame() else sender.sendMessage(text("The game is running!", NamedTextColor.RED))
                }
            }
            then("stop") {
                executes {
                    if (isRunning) stopGame() else sender.sendMessage(text("The game is not running!", NamedTextColor.RED))
                }
            }
        }
    }
}