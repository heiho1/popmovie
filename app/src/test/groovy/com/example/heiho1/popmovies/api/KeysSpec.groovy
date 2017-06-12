package com.example.heiho1.popmovies.api

import spock.lang.Specification


/**
 * Specifies the expected behavior of the api.Keys class
 */
class KeysSpec extends Specification {
    def "There is a the_movie_db key"() {
        when: "A movie db key is retrieved"
        String key = Keys.keyFor(Keys.Types.the_movie_db)

        then: "A key exists"
        key
    }
}