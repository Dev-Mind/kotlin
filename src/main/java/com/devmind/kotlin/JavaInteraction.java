package com.devmind.kotlin;

/**
 * @author devmind
 */
public class JavaInteraction {

    /**
     * Kotlin est complétement interopérable avec Java
     * @param args
     */
    public static void main(String[] args) {
        Speaker speaker = new Speaker("Guillaume", "EHRET", 42, "USA");

        System.out.println("Hello Devoxx " + speaker);
    }
}
