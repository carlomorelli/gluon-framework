package com.csoft.gluon.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Router {

    private static final Logger LOGGER = LoggerFactory.getLogger(Router.class);

    private Map<String, Controller> gets = new HashMap<>();
    private Map<String, Controller> deletes = new HashMap<>();
    private Map<String, Controller> posts = new HashMap<>();
    private Map<String, Controller> puts = new HashMap<>();

    private Router() {
    }

    public static Router newGluonRouter() {
        return new Router();
    }

    private static void addMethodToSet(Map<String, Set<String>> map, String key, String method) {
        Set<String> value = map.getOrDefault(key, new HashSet<>());
        value.add(method);
        map.put(key, value);
    }

    private static void addControllerToSet(Map<String, Set<Controller>> map, String key, Controller controller) {
        Set<Controller> value = map.getOrDefault(key, new HashSet<>());
        value.add(controller);
        map.put(key, value);
    }

    public Router get(final String path, final Controller controller) {
        LOGGER.info("Registering path [{}] with [method=GET, controller={}]", path, controller.getClass().getCanonicalName());
        gets.put(path, controller);
        return this;
    }

    public Router post(final String path, final Controller controller) {
        LOGGER.info("Registering path [{}] with [method=POST, controller={}]", path, controller.getClass().getCanonicalName());
        posts.put(path, controller);
        return this;
    }

    public Router put(final String path, final Controller controller) {
        LOGGER.info("Registering path [{}] with [method=PUT, controller={}]", path, controller.getClass().getCanonicalName());
        puts.put(path, controller);
        return this;
    }

    public Router delete(final String path, final Controller controller) {
        LOGGER.info("Registering path [{}] with [method=DELETE, controller={}]", path, controller.getClass().getCanonicalName());
        deletes.put(path, controller);
        return this;
    }

    public Map<String, Set<String>> mapOfMethods() {
        Map<String, Set<String>> map = new HashMap<>();
        gets.forEach((k, v) -> addMethodToSet(map, k, "GET"));
        posts.forEach((k, v) -> addMethodToSet(map, k, "POST"));
        puts.forEach((k, v) -> addMethodToSet(map, k, "PUT"));
        deletes.forEach((k, v) -> addMethodToSet(map, k, "DELETE"));
        return map;
    }

    public Map<String, Set<Controller>> mapOfControllers() {
        Map<String, Set<Controller>> map = new HashMap<>();
        gets.forEach((k, v) -> addControllerToSet(map, k, v));
        posts.forEach((k, v) -> addControllerToSet(map, k, v));
        puts.forEach((k, v) -> addControllerToSet(map, k, v));
        deletes.forEach((k, v) -> addControllerToSet(map, k, v));
        return map;
    }

}
