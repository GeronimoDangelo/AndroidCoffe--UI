package com.coffee.bookapp.data.remote

import com.example.bookapp.domain.model.ApiResponse
import com.example.bookapp.domain.model.Book
import java.io.IOException

class FakeBookApi2 : com.coffee.bookapp.data.remote.BookApi {


    private val books: Map<Int, List<Book>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3
        )
    }

    private var page1 = listOf(
        Book(
            id = 1,
            name = "Variables",
            image = "/images/variables.png",
            about = "Kotlin uses two different keywords to declare variables ( val and var). Use val for a variable whose value never changes. You can't reassign a value to a variable that was declared using val. And you can use var for a variable whose value can change.",
            rating = 0.5,
            level = "Beginner",
            timeToLearn = "Fast",
            tags = listOf(
                "val,",
                "var,",
                "const val,",
                "private val,",
                "private var."
            ),
        ),
        Book(
            id = 2,
            name = "Coroutines",
            image = "/images/coroutines.png",
            about = "A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously." +
                    "For example: " +
                    "fun main() = runBlocking {" +
                    "   launch {" +
                    "       delay(1000)" +
                    "       println('World')" +
                    "     }" +
                    "   println('Hello')" +
                    "In this case, we first start running the block and after 1 second it's going to say 'World', meanwhile the coroutine scope is running the code below ('Hello') is printing first and 1 second later the 'World'",

            rating = 5.0,
            level = "Oct",
            timeToLearn = "10th",
            tags = listOf(
                "Minato",
                "Kushina",
                "Boruto",
                "Himawari",
                "Hinata"
            ),
        ),
        Book(
            id = 3,
            name = "Flows",
            image = "/images/flows.png",
            about = "In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. For example, you can use a flow to receive live updates from a database.",
            rating = 5.0,
            level = "Mar",
            timeToLearn = "28th",
            tags = listOf(
                "Kizashi",
                "Mebuki",
                "Sarada",
                "Sasuke"
            ),
        )
    )
    private var page2 = listOf(
        Book(
            id = 4,
            name = "Sealed classes",
            image = "/images/sealed_clases.png",
            about = "Sealed classes and interfaces represent restricted class hierarchies that provide more control over inheritance. All direct subclasses of a sealed class are known at compile time. No other subclasses may appear outside a module within which the sealed class is defined",
            rating = 3.5,
            level = "Mar",
            timeToLearn = "27th",
            tags = listOf(
                "Naruto",
                "Hinata",
                "Hanabi",
                "Himawari",
                "Kawaki"
            ),
        ),
        Book(
            id = 5,
            name = "Interfaces",
            image = "/images/interface.png",
            about = "in Kotlin can contain declarations of abstract methods, as well as method implementations. What makes them different from abstract classes is that interfaces cannot store state. They can have properties, but these need to be abstract or provide accessor implementations.",
            rating = 4.9,
            level = "Mar",
            timeToLearn = "31st",
            tags = listOf(
                "Sasuke Uchiha",
                "Sakura Uchiha"
            ),
        ),
        Book(
            id = 6,
            name = "Data Class",
            image = "/images/dataclass.png",
            about = "It is not unusual to create classes whose main purpose is to hold data. In such classes, some standard functionality and some utility functions are often mechanically derivable from the data. In Kotlin, these are called data classes and are marked with data",
            rating = 2.7,
            level = "Jul",
            timeToLearn = "25th",
            tags = listOf(
                "Orochimaru",
                "Log"
            ),
        )
    )
    private var page3 = listOf(
        Book(
            id = 7,
            name = "Extensions",
            image = "/images/extensions.png",
            about = "Kotlin provides the ability to extend a class or an interface with new functionality without having to inherit from the class or use design patterns such as Decorator. This is done via special declarations called extensions. ",
            rating = 4.5,
            level = "Jan",
            timeToLearn = "1st",
            tags = listOf(
                "Kokatsu"
            ),
        ),
        Book(
            id = 8,
            name = "Constructors",
            image = "/images/constructors.png",
            about = "A Kotlin constructor is a special member function in a class that is invoked when an object is instantiated. Whenever an object is created, the defined constructor is called automatically which is used to initialize the properties of the class.",
            rating = 1.5,
            level = "Oct",
            timeToLearn = "27th",
            tags = listOf(
                "Mitsuki",
                "Log"
            ),

            ),
        Book(
            id = 9,
            name = "Objects",
            image = "/images/objects.png",
            about = "Kakashi Hatake (はたけカカシ, Hatake Kakashi) is a shinobi of Konohagakure's Hatake clan. Famed as Kakashi of the Sharingan (写輪眼のカカシ, Sharingan no Kakashi), he is one of Konoha's most talented ninja, regularly looked to for advice and leadership despite his personal dislike of responsibility. To his students on Team 7, Kakashi emphasises the importance of teamwork; he himself received this lesson, along with the Sharingan, from his childhood friend, Obito Uchiha.",
            rating = 0.5,
            level = "Sep",
            timeToLearn = "15th",
            tags = listOf(
                "Sakumo"
            ),
        )
    )


    fun cleanUp() {
        page1 = emptyList()
    }


    private var exeption = false

    fun addException(){
        exeption = true
    }

    override suspend fun getAllBooks(page: Int): ApiResponse {
        if (exeption) {
            throw IOException()
        }
        require(page in 1..3)
        return ApiResponse(
            success = true,
            message = "ok",
            prevPage = calculatePage(page = page)["prevPage"],
            nextPage = calculatePage(page = page)["nextPage"],
            books = books[page]!!
        )
    }


    private fun calculatePage(page: Int): Map<String, Int?> {

        if (page1.isEmpty()) {
            return mapOf("prevPage" to null, "nextPage" to null)
        }

        return mapOf(
            "prevPage" to if (page in 2..3) page.minus(1) else null,
            "nextPage" to if (page in 1..2) page.plus(1) else null
        )
    }

    override suspend fun searchBooks(name: String): ApiResponse {
        return ApiResponse(
            success = false
        )
    }

    override suspend fun getAllJetpacks(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchJetpack(name: String): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getAllXmls(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchXml(name: String): ApiResponse {
        TODO("Not yet implemented")
    }
}