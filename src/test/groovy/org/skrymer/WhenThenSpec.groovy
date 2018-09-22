package org.skrymer

import spock.lang.Specification

import static org.skrymer.whenthen.WhenThen.unless
import static org.skrymer.whenthen.WhenThen.when

class WhenThenSpec extends Specification {

    def "when true then execute code" () {
        given:
        def codeWasExecuted = false
        when(true)
            .then({-> codeWasExecuted = true})

        expect:
        codeWasExecuted == true
    }

    def "unless true then execute code" () {
        given:
        def codeWasExecuted = false
        unless(false)
                .then({-> codeWasExecuted = true})

        expect:
        codeWasExecuted == true
    }

    def "when true and true then code is executed" () {
        given:
        def codeWasExecuted = false
        when(true)
            .and(true)
            .and({-> true})
            .then({-> codeWasExecuted = true})

        expect:
        codeWasExecuted == true
    }

    def "when false then code is NOT executed" () {
        when:
        def codeWasExecuted = false
        when(false)
            .then({-> codeWasExecuted = true})

        then:
        codeWasExecuted == false
    }

    def "when true and false then code is NOT executed" () {
        when:
        def codeWasExecuted = false
        when(true)
            .and(false)
            .then({-> codeWasExecuted = true})

        then:
        codeWasExecuted == false
    }

    def "when true then exception is thrown" () {
        when: "true"
        when(true)
            .thenThrow({-> new RuntimeException()})

        then:
        thrown(RuntimeException)
    }

    def "when false then exception is NOT thrown" () {
        when: "true"
        when(false)
                .thenThrow({-> new RuntimeException()})

        then:
        notThrown(RuntimeException)
    }

    def "when true then IllegalArgumentException is thrown" () {
        when: "true"
        when(true)
            .thenThrowIllegalArgument('argName')

        then:
        thrown(IllegalArgumentException)
    }

    def "when false then IllegalArgumentException is NOT thrown" () {
        when: "true"
        when(false)
                .thenThrowIllegalArgument('argName')

        then:
        notThrown(IllegalArgumentException)
    }

    def "when true then return Optional with String" () {
        when: "true"
        def optionalString = when(true)
                .thenReturn({-> "Some string"})

        then:
        optionalString.isPresent()
        optionalString.get() == "Some string"
    }

    def "when false then empty Optional" () {
        when: "false"
        def optionalString = when(false)
                .thenReturn({-> "Some string"})

        then:
        optionalString.isPresent() == false
    }
}


