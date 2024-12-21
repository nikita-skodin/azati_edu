package com.app.azati_edu.sandbox

import com.app.azati_edu.getClassLogger
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class MainSandbox {

    @JvmOverloads
    fun defParamsMethod(name: String = "User", age: Int = 18) {
        logger.info("Hello, $name! You are $age years old.")
    }

    fun logMethodName(name: String, func: () -> Unit) {
        logger.info("$name starts")
        func.invoke()
        logger.info("$name end")
    }

    @PostConstruct
    fun start() {
        logMethodName("scopeFunctions", ::scopeFunctions)
        logMethodName("customDelegates", ::customDelegates)
    }

    fun customDelegates() {
        logger.info("customDelegates start")

        class NotEmptyDelegate(private var value: String) {
            operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): String {
                logger.info("getting value $value for property ${property.name}")
                return value
            }

            operator fun setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, newValue: String) {
                if (newValue.isBlank()) {
                    throw IllegalArgumentException("${property.name} can't be empty.")
                }
                value = newValue
            }
        }

        data class User(var username: String, var email: String) {
            var password: String? = null

            var usernameDelegate by NotEmptyDelegate(username)
            var emailDelegate by NotEmptyDelegate(email)

            override fun toString(): String {
                return "User(username='$usernameDelegate', email='$emailDelegate', password=$password)"
            }
        }


        val user = User("John", "john@example.com")
        logger.info(user.toString())

        try {
            user.usernameDelegate = ""
        } catch (e: IllegalArgumentException) {
            logger.warn(e.message)
        }



        logger.info("customDelegates end")
    }

    fun scopeFunctions() {
        logger.info("scopeFunctions start")

        data class User(var username: String, var email: String) {
            var password: String? = null
            override fun toString(): String {
                return "User(username='$username', email='$email', password=$password)"
            }

        }

        val userMap = User("john_doe", "john@example.com")

        // для проверки на null или доработки объекта, returns результат лямбды
        val emailLength = userMap.let {
            logger.info("Username is: ${it.username}")
            it.password = "password"
            it.email.length
        }
        logger.info("email length is: $emailLength")
        userMap.password?.let { logger.info("Password is: $it") }

        // для доработки объекта, returns сам объект
        val userApply = User("john_doe", "john@example.com").apply {
            password = "12345678"
        }
        logger.info("userApply is : $userApply")

        // для побочных действий, returns сам объект
        val userAlso = User("john_doe", "john@example.com").also {
            logger.info("Creating user with email: ${it.email}")
        }

        // для блока когда с объектом, returns результат последнего выражения
        val userRun = User("john_doe", "john@example.com")
        val upperEmail = userRun.run {
            logger.info("Username is: $username")
            email.uppercase()
        }

        // как run только метод
        val userWith = User("john_doe", "john@example.com")
        val lowerEmail = with(userWith) {
            logger.info("Username is: $username")
            email.lowercase()
        }

        logger.info("scopeFunctions end")
    }

    companion object {
        private val logger = getClassLogger<MainSandbox>()

        @JvmStatic
        fun staticMethod() {
            logger.info("This is a static method")
        }
    }

}