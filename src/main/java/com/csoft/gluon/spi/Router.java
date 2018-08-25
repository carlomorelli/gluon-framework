package com.csoft.gluon.spi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Router {

    private Map<String, Controller> gets = new HashMap<>();
    private Map<String, Controller> deletes = new HashMap<>();
    private Map<String, Controller> posts = new HashMap<>();
    private Map<String, Controller> puts = new HashMap<>();

    private Router() {
    }

    public static Router newGluonRouter() {
        return new Router();
    }

    public Router get(final String path, final Controller controller) {
        gets.put(path, controller);
        return this;
    }

    public Router post(final String path, final Controller controller) {
        posts.put(path, controller);
        return this;
    }

    public Router put(final String path, final Controller controller) {
        puts.put(path, controller);
        return this;
    }

    public Router delete(final String path, final Controller controller) {
        deletes.put(path, controller);
        return this;
    }

    public Map<String, Set<String>> mapOfMethods() {
        Map<String, Set<String>> map = new HashMap<>();
        gets.forEach((k, v) -> addToSet(map, k, "GET"));
        posts.forEach((k, v) -> addToSet(map, k, "POST"));
        puts.forEach((k, v) -> addToSet(map, k, "PUT"));
        deletes.forEach((k, v) -> addToSet(map, k, "DELETE"));
        return map;
    }



    private static void addToSet(Map<String, Set<String>> map, String key, String method) {
        Set<String> value = map.getOrDefault(key, new HashSet<>());
        value.add(method);
        map.put(key, value);
    }

}
