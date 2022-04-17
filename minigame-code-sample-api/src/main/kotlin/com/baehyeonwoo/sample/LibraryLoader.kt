/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 *
 * Original code by monun.
 */

package com.baehyeonwoo.sample

import org.apache.commons.lang.reflect.ConstructorUtils
import org.bukkit.Bukkit
import java.lang.reflect.InvocationTargetException

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

object LibraryLoader {
    @Suppress("UNCHECKED_CAST")
    fun <T> loadImplement(type: Class<T>, vararg initArgs: Any? = emptyArray()): T {
        val packageName = type.`package`.name
        val className = "${type.simpleName}Impl"
        val parameterTypes = initArgs.map { it?.javaClass }.toTypedArray()

        return try {
            val internalClass =
                Class.forName("$packageName.internal.$className", true, type.classLoader).asSubclass(type)
            val constructor = ConstructorUtils.getMatchingAccessibleConstructor(internalClass, parameterTypes)
                ?: throw UnsupportedOperationException("${type.name} does not have Constructor for [${parameterTypes.joinToString()}]")
            constructor.newInstance(*initArgs) as T
        } catch (exception: ClassNotFoundException) {
            throw UnsupportedOperationException("${type.name} a does not have implement", exception)
        } catch (exception: IllegalAccessException) {
            throw UnsupportedOperationException("${type.name} constructor is not visible")
        } catch (exception: InstantiationException) {
            throw UnsupportedOperationException("${type.name} is abstract class")
        } catch (exception: InvocationTargetException) {
            throw UnsupportedOperationException(
                "${type.name} has an error occurred while creating the instance",
                exception
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> loadNMS(type: Class<T>, vararg initArgs: Any? = emptyArray()): T {
        val packageName = type.`package`.name
        val className = "NMS${type.simpleName}"
        val parameterTypes = initArgs.map {
            it?.javaClass
        }.toTypedArray()

        val candidates = ArrayList<String>(2)
        candidates.add("$packageName.$libraryVersion.$className")

        val lastDot = packageName.lastIndexOf('.')
        if (lastDot > 0) {
            val superPackageName = packageName.substring(0, lastDot)
            val subPackageName = packageName.substring(lastDot + 1)
            candidates.add("$superPackageName.$libraryVersion.$subPackageName.$className")
        }

        return try {
            val nmsClass = candidates.firstNotNullOfOrNull { candidate ->
                try {
                    Class.forName(candidate, true, type.classLoader).asSubclass(type)
                } catch (exception: ClassNotFoundException) {
                    null
                }
            } ?: throw ClassNotFoundException("Not found nms library class: $candidates")
            val constructor = ConstructorUtils.getMatchingAccessibleConstructor(nmsClass, parameterTypes)
                ?: throw UnsupportedOperationException("${type.name} does not have Constructor for [${parameterTypes.joinToString()}]")
            constructor.newInstance(*initArgs) as T
        } catch (exception: ClassNotFoundException) {
            throw UnsupportedOperationException(
                "${type.name} does not support this version: $libraryVersion",
                exception
            )
        } catch (exception: IllegalAccessException) {
            throw UnsupportedOperationException("${type.name} constructor is not visible")
        } catch (exception: InstantiationException) {
            throw UnsupportedOperationException("${type.name} is abstract class")
        } catch (exception: InvocationTargetException) {
            throw UnsupportedOperationException(
                "${type.name} has an error occurred while creating the instance",
                exception
            )
        }
    }

    @Suppress("UNUSED")
    val bukkitVersion: String by lazy {
        with("v\\d+_\\d+_R\\d+".toPattern().matcher(Bukkit.getServer()::class.java.`package`.name)) {
            when {
                find() -> group()
                else -> throw NoSuchElementException("No such bukkit version exists")
            }
        }
    }

    private val minecraftVersion: String by lazy {
        with("(?<=\\(MC: )[\\d.]+?(?=\\))".toPattern().matcher(Bukkit.getVersion())) {
            when {
                find() -> group()
                else -> throw NoSuchElementException("No such minecraft version exists")
            }
        }
    }

    private val libraryVersion by lazy { "v${minecraftVersion.replace('.', '_')}" }
}