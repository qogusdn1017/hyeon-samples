/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package com.baehyeonwoo.sample.plugin.tasks

import com.baehyeonwoo.sample.plugin.objects.SampeGameContentManager.plugin

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class SampleGameTask : Runnable {
    override fun run() {
        plugin.logger.info("Hello World!")
    }
}