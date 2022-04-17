/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 *
 * Original code by monun.
 */

package com.baehyeonwoo.sample.internal

import com.baehyeonwoo.sample.LibraryLoader
import com.baehyeonwoo.sample.Sample

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

@Suppress("UNUSED")
class SampleImpl: Sample {
    private val version = LibraryLoader.loadNMS(Version::class.java)

    override fun sampleAPI() {
        println("version = ${version.value}")
    }
}

interface Version {
    val value: String
}