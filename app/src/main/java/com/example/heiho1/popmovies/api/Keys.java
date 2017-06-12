package com.example.heiho1.popmovies.api;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Accessor to provide API keys for the common pattern of
 * data access by authorization token
 */
public class Keys {
    private static Properties knownKeys;

    static {
        try {
            knownKeys = new Properties();
            knownKeys.load(Keys.class.getResourceAsStream("/keys.properties"));
        } catch (IOException e) {
            throw new IllegalStateException("keys.properties not found on the classpath");
        }
    }

    private Keys() {
        // intended for static usage
    }

    /**
     * Enumeration of supported keys
     */
    public enum Types {
        the_movie_db
    }

    public static String keyFor(Types toRetrieve) {
        if (!knownKeys.containsKey(toRetrieve.name())) {
            throw new NoSuchElementException(String.format("Unsupported key type %s.  Please configure keys.properties in src/main/resources", toRetrieve.name()));
        }
        return knownKeys.getProperty(toRetrieve.name());
    }
}

