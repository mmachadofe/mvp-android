package br.uol.ps.cards

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)



        //var -> variavel mutavel
        //val-> imutavel     (final do java)
        //late init -> pode inciar somente uma vez

        val animal = Animal("Rex", 3, "")

        animal.age
        animal.name

        //BOLEANOS
        if(animal.age >18){
            //todo
        }else{

        }

        when(animal.name){
            "rex" -> print("rex detected")
            "bolinha" -> {

            }
            else -> print("else")
        }

        //INTERADORES

        // for ()
        //     while ()
        //         do{
        //
        //         }while ()


        val lista = arrayListOf<String>()

        lista.forEach {
            print(it)
        }


        //KOTLIN IS NULL SAFETY
        var name: String? = null

         print(name?.length)

        name?.let {

        }?: run {

        }


        if(name!=null){

        }else{

        }

    }

}