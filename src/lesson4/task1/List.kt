@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.*
import java.lang.Math.pow


/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.sumByDouble { it * it })

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double
{
    val average = list.sum() / list.size
    return if (list.isEmpty()) 0.0
    else average
}


/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double>
{
    if (list.isNotEmpty()) {
        val averageValue = mean(list)
        list.replaceAll{ it - averageValue }
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double =
        if (a.isNotEmpty()) a.zip(b).fold(0.0) {prevResult, (a, b) -> prevResult + (a * b)} else 0.0


/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double =
        if (p.isNotEmpty()) p.mapIndexed { index, value -> value * pow(x, index.toDouble()) }.sum() else 0.0


/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double>
{
    if (list.isNotEmpty()) {
        var sum = 0.0
        list.replaceAll {
            val element = sum
            sum += it
            it + element
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int>
{
    val dividers = mutableListOf<Int>()
    var numb = n
    var divider = 2
    while(numb > 1)
    {
        while (numb % divider == 0)
        {
            dividers.add(divider)
            numb /= divider
        }
        divider++
    }
    return dividers
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")


/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int>
{
    val digits = mutableListOf<Int>()
    var numb = n
    if (n != 0) {
        while (numb > 0) {
            val digit = numb % base
            digits.add(digit)
            numb /= base
        }

    }
    else digits.add(0)
    return digits.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String
{
    var numb = n
    var answer = ""
    do
    {
        val digit = numb % base
        if (digit <= 9) answer += digit.toString() else answer += 'a' + (digit - 10)
        numb /= base
    } while (numb > 0)
    return answer.reversed()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
 fun decimal(digits: List<Int>, base: Int): Int =
        digits.reversed().foldRightIndexed(0) { index, element, sum ->
        sum + (element * pow(base.toDouble(), index.toDouble())).toInt()
    }


/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int
{
    val answer = mutableListOf<Int>()
    for (i in str.length - 1 downTo 0)
    {
        when {
            str[i] <= '9' -> answer.add(str[i].toInt() - 48)
            else -> answer.add(10 + (str[i] - 'a'))
        }
    }
    return decimal(answer.reversed(), base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val romanLetters: Map<Int, String> = mapOf(1000 to "M", 900 to "CM", 500 to "D", 400 to "CD", 100 to "C", 90 to "XC", 50
            to "L", 40 to "XL", 10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I")
    val inRoman = mutableListOf<String>()
    var numb = n
    for (key in romanLetters.keys) {
        while (numb >= key) {
            inRoman.add(romanLetters[key].toString())
            numb -= key
        }
    }
    return inRoman.joinToString(separator = "")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val units: Map<Int, String> = mapOf(
            0 to "",
            1 to "один ",
            2 to "два ",
            3 to "три ",
            4 to "четыре ",
            5 to "пять ",
            6 to "шесть ",
            7 to "семь ",
            8 to "восемь ",
            9 to "девять "
    )
    val fromElevenToNineteen: Map<Int, String> = mapOf(
            11 to "одиннадцать ",
            12 to "двенадцать ",
            13 to "тринадцать ",
            14 to "четырнадцать ",
            15 to "пятнадцать ",
            16 to "шестнадцать ",
            17 to "семнадцать ",
            18 to "восемнадцать ",
            19 to "девятнадцать "
    )
    val decades: Map<Int, String> = mapOf(
            0 to "",
            1 to "десять ",
            2 to "двадцать ",
            3 to "тридцать ",
            4 to "сорок ",
            5 to "пятьдесят ",
            6 to "шестьдесят ",
            7 to "семьдесят ",
            8 to "восемьдесят ",
            9 to "девяносто "
    )
    val hundreds: Map<Int, String> = mapOf(
            0 to "",
            1 to "сто ",
            2 to "двести ",
            3 to "триста ",
            4 to "четыреста ",
            5 to "пятьсот ",
            6 to "шестьсот ",
            7 to "семьсот ",
            8 to "восемьсот ",
            9 to "девятьсот "
    )
    val endsOfThousand: Map<Int, String> = mapOf(
            0 to "тысяч ",
            1 to "тысяча ",
            2 to "тысячи ",
            3 to "тысячи ",
            4 to "тысячи ",
            5 to "тысяч ",
            6 to "тысяч ",
            7 to "тысяч ",
            8 to "тысяч ",
            9 to "тысяч ",
            10 to "тысяч ",
            11 to "тысяч ",
            12 to "тысяч ",
            13 to "тысяч ",
            14 to "тысяч ",
            15 to "тысяч ",
            16 to "тысяч ",
            17 to "тысяч ",
            18 to "тысяч ",
            19 to "тысяч "
    )
    val endsOfUnits: Map<Int, String> = mapOf(
            0 to "",
            1 to "одна ",
            2 to "две ",
            3 to "три ",
            4 to "четыре ",
            5 to "пять ",
            6 to "шесть ",
            7 to "семь ",
            8 to "восемь ",
            9 to "девять "
    )

    val digits = mutableListOf<Int>()
    var numb = n

    while (numb > 0) {
        digits.add(numb % 10)
        numb /= 10
    }
    digits.reverse()
    return when {
        n > 99999 && (n / 1000) % 100 in 19 downTo 11 && n % 100 in 19 downTo  11 -> hundreds[digits[0]] +
                                                                                     fromElevenToNineteen[n / 1000 % 100] +
                                                                                     endsOfThousand[5] + hundreds[digits[3]] +
                                                                                     fromElevenToNineteen[n % 100]
        n > 99999 && n % 100 in 19 downTo  11 -> hundreds[digits[0]] + decades[digits[1]] + endsOfUnits[digits[2]] +
                                                 endsOfThousand[digits[2]] + hundreds[digits[3]] + fromElevenToNineteen[n % 100]
        n > 99999 && (n / 1000) % 100 in 19 downTo 11 -> hundreds[digits[0]] + fromElevenToNineteen[n / 1000 % 100] +
                                                         endsOfThousand[5] + hundreds[digits[3]] + decades[digits[4]] +
                                                         units[digits[5]]
        n > 99999 -> hundreds[digits[0]] + decades[digits[1]] + endsOfUnits[digits[2]] +
                     endsOfThousand[digits[2]] + hundreds[digits[3]] + decades[digits[4]] + units[digits[5]]
        n in 99999 downTo 10000 && n / 1000 in 19 downTo 11 && n % 100 in 19 downTo  11 -> fromElevenToNineteen[n / 1000] +
                                                                                           endsOfThousand[n / 1000] +
                                                                                           hundreds[digits[2]] +
                                                                                           fromElevenToNineteen[n % 100]
        n in 99999 downTo 10000 && n / 1000 in 19 downTo 11 -> fromElevenToNineteen[n / 1000] +
                                                               endsOfThousand[n / 1000] + hundreds[digits[2]] +
                                                               decades[digits[3]] + units[digits[4]]
        n in 99999 downTo 10000 && n % 100 in 19 downTo  11 -> decades[digits[0]] + endsOfUnits[digits[1]] +
                                                               endsOfThousand[digits[1]] + hundreds[digits[2]] +
                                                               fromElevenToNineteen[n % 100]
        n in 99999 downTo 10000  -> decades[digits[0]] + endsOfUnits[digits[1]] +
                                    endsOfThousand[digits[1]] + hundreds[digits[2]] + decades[digits[3]] + units[digits[4]]
        n in 9999 downTo 1000 && n % 100 in 19 downTo  11 -> endsOfUnits[digits[0]] + endsOfThousand[digits[0]] +
                                                             hundreds[digits[1]] + fromElevenToNineteen[n % 100]
        n in 9999 downTo 1000 -> endsOfUnits[digits[0]] +
                                 endsOfThousand[digits[0]] + hundreds[digits[1]] + decades[digits[2]] + units[digits[3]]
        n in 999 downTo 100 && n % 100 in 19 downTo 11 -> hundreds[digits[0]] + fromElevenToNineteen[n % 100]
        n in 999 downTo 100 -> hundreds[digits[0]] + decades[digits[1]] + units[digits[2]]
        n in 99 downTo  20 ->  decades[digits[0]] + units[digits[1]]
        n in 19 downTo 11 -> fromElevenToNineteen[n].toString()
        n in 9 downTo 1 -> units[n].toString()
        n == 10 -> decades[1].toString()
        else -> "ноль"
    }.trim()
}


