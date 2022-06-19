/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 *
 * Original code by monun.
 */

package com.baehyeonwoo.sample

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

interface Sample {
    companion object: Sample by LibraryLoader.loadImplement(Sample::class.java)

    /***
     * sampleAPI 입니다.
     */
    fun sampleAPI()
}