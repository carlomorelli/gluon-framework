package com.csoft.gluon.spi

import mu.KotlinLogging
import java.util.*

class Router {

    private val logger = KotlinLogging.logger {}

    private val gets = HashMap<String, Controller>()
    private val deletes = HashMap<String, Controller>()
    private val posts = HashMap<String, Controller>()
    private val puts = HashMap<String, Controller>()

    operator fun get(path: String, controller: Controller): Router {
        logger.info("Registering path [{}] with [method=GET, controller={}]", path, controller.javaClass.canonicalName)
        gets[path] = controller
        return this
    }

    fun post(path: String, controller: Controller): Router {
        logger.info("Registering path [{}] with [method=POST, controller={}]", path, controller.javaClass.canonicalName)
        posts[path] = controller
        return this
    }

    fun put(path: String, controller: Controller): Router {
        logger.info("Registering path [{}] with [method=PUT, controller={}]", path, controller.javaClass.canonicalName)
        puts[path] = controller
        return this
    }

    fun delete(path: String, controller: Controller): Router {
        logger.info("Registering path [{}] with [method=DELETE, controller={}]", path, controller.javaClass.canonicalName)
        deletes[path] = controller
        return this
    }

    fun mapOfMethods(): Map<String, Set<String>> {
        val map = HashMap<String, Set<String>>()
        gets.forEach { k, _ -> addMethodToSet(map, k, "GET") }
        posts.forEach { k, _ -> addMethodToSet(map, k, "POST") }
        puts.forEach { k, _ -> addMethodToSet(map, k, "PUT") }
        deletes.forEach { k, _ -> addMethodToSet(map, k, "DELETE") }
        return map
    }

    fun mapOfControllers(): Map<String, Set<Controller>> {
        val map = HashMap<String, Set<Controller>>()
        gets.forEach { k, v -> addControllerToSet(map, k, v) }
        posts.forEach { k, v -> addControllerToSet(map, k, v) }
        puts.forEach { k, v -> addControllerToSet(map, k, v) }
        deletes.forEach { k, v -> addControllerToSet(map, k, v) }
        return map
    }

}

private fun addMethodToSet(map: MutableMap<String, Set<String>>, key: String, method: String) {
    val value = map.getOrDefault(key, HashSet()).plus(method)
    map[key] = value
}

private fun addControllerToSet(map: MutableMap<String, Set<Controller>>, key: String, controller: Controller) {
    val value = map.getOrDefault(key, HashSet()).plus(controller)
    map[key] = value
}
