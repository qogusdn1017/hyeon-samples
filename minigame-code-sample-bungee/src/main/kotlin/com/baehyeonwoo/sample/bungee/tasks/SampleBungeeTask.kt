/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.bungee.tasks

import com.baehyeonwoo.sample.bungee.objects.SampleBungeeObject.bgPlugin

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class SampleBungeeTask : Runnable {
    override fun run() {
        bgPlugin.logger.info("Hello World!")
    }
}