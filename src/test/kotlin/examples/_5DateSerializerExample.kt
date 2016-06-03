package ru.yole.jkid.examples.customSerializerTest

import org.junit.Test
import ru.yole.jkid.JsonSerializer
import ru.yole.jkid.ValueSerializer
import ru.yole.jkid.examples.jsonSerializerTest.testJsonSerializer
import ru.yole.jkid.serialization.serialize
import java.text.SimpleDateFormat
import java.util.*

object DateSerializer : ValueSerializer<Date> {
    private val dateFormat = SimpleDateFormat("dd-mm-yyyy")

    override fun toJsonValue(value: Date): Any? =
            dateFormat.format(value)

    override fun fromJsonValue(jsonValue: Any?): Date =
            dateFormat.parse(jsonValue as String)
}

data class Person(
        val name: String,
        @JsonSerializer(DateSerializer::class) val birthDate: Date
)

class DateSerializerTest {
    @Test fun test() = testJsonSerializer(
            value = Person("Alice", SimpleDateFormat("dd-mm-yyyy").parse("13-02-1987")),
            json = """{"birthDate": "13-02-1987", "name": "Alice"}"""
    )
}

fun main(args: Array<String>) {
    val birthDate = SimpleDateFormat("dd-mm-yyyy").parse("13-02-1987")
    println(serialize(Person("Alice", birthDate)))
}