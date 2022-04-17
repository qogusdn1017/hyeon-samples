/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 *
 * Original code by monun.
 */

package com.baehyeonwoo.sample.v1_18_2.internal

import com.baehyeonwoo.sample.internal.Version
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_18_R2.CraftServer

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

@Suppress("UNUSED")
class NMSVersion: Version {
    override val value: String
        get() = ((Bukkit.getServer()) as CraftServer).handle.server.serverVersion
}