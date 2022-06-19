/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.bungee.commands

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.connection.Server
import net.md_5.bungee.api.plugin.Command

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class SampleCommand : Command("") {
    override fun execute(commandSender: CommandSender, args: Array<String>) {
        if ((commandSender is ProxiedPlayer && commandSender.hasPermission("sample.permission")) || commandSender is Server) {
            commandSender.sendMessage(TextComponent("${ChatColor.GREEN}Hello World!"))
        }
    }
}