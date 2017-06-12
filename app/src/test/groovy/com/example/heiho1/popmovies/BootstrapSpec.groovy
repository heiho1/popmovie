package com.example.heiho1.popmovies

import spock.lang.Specification


/**
 * A simple bootstrap to verify that Spock is working
 */
class BootstrapSpec extends Specification {
    def "Addition is not broken"() {
        when: "Simple addition is performed"
        def testVal = 1 + 1

        then: "The value is as expected"
        testVal == 2
    }

}