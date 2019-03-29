package com.devmind.kotlin

import java.time.LocalDate
import java.util.*

/**
 * @author devmind
 *
 *  fun pour déclarer une fonction (on peut déclarer en dehors des classes
 *  pas besoin de dire public pas de point virgule
 */
fun main1(){
    val speaker = Speaker("Guillaume", "EHRET", "1977-05-09".toAge)
    println("Hello Devoxx $speaker")

    val speaker2 = speaker.copy(lastname = "HAASE", country = "USA")
    println("Hello Devoxx $speaker2")

    val speaker3 = speaker.copy()
    // == correspond au equals Java
    println(speaker == speaker3)
    println(speaker === speaker3)
 }

/**
 * On peut écrire des fonctions ou des propriétés d'ordre supérieur
 */
val String.toAge
    get() = LocalDate.now().year - LocalDate.parse(this).year


/*
 * Les data classes permettent de simplifier la création de POJO
 */
data class Speaker(val firstname: String,
              val lastname: String,
              // Kotlin par construction interdit les null. Vous devez être explicite quand une valeur est nullable
              val age: Int? = null,
              // Les paramètres par défaut
              val country: String = "France"){

    init {
        // Utilisation d'une fonction d'ordre supérieur
        require(age ?:20 > 10){
            "Age must be greater than 10"
        }
    }
}


fun main2(){
    val speaker = Speaker("Guillaume", "EHRET", "1977-05-09".toAge)
    val speaker2 = speaker.copy(lastname = "HAASE", country = "USA")

    logger{
        println("Hello Devoxx $speaker")
        println("Hello Devoxx $speaker2")
    }
}

/**
 * Définition d'une fonction d'ordre supérieur (utile pour les DSL)
 */
fun logger(cb : () -> Unit) {
    println("======================")
    cb()
}


/**
 * Les collections en Kotlin
 */
fun main3(){
    val speaker = Speaker("Guillaume", "EHRET", "1977-05-09".toAge)
    val speaker2 = speaker.copy(lastname = "HAASE", country = "USA")
    val speaker3 = speaker.copy()

    val speakerService = Factory.instance.speakerService

    speakerService.speakers.add(speaker)
    speakerService.speakers.add(speaker2)
    speakerService.speakers.add(speaker3)

    println(speakerService.speakers)
    // Des méthodes utilitaires pour la gestion des streams
    println(speakerService.speakers.filter { it.lastname == "HAASE" })
}



class SpeakerService{
    val speakers = mutableListOf<Speaker>()
}

/**
 * Comment mettre en place une factory d'objet
 */
class Factory{

    val speakerService by lazy{
        SpeakerService()
    }

    companion object {
        val instance = Factory()
    }

}
