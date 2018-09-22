@file:Suppress("UNUSED_PARAMETER")
package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean
{
    val numbHalf1 = number % 100
    val numbHalf2 = number / 100

    val sum12 = numbHalf1 % 10 + numbHalf1 / 10
    val sum34 = numbHalf2 % 10 + numbHalf2 / 10

    if (sum12 == sum34)
    {
        return true
    }
    else
    {
        return false
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean
{
    if (x1 == x2 || y1 == y2 ||(abs((1.0 - y1) - x1) == abs(y2 - (x2 - 1.0))))
    {
        return true
    }
    else
    {
        return false
    }
}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int
{
    if (year % 4 == 0)
    {
        if (year % 100 != 0)
        {
            return when (month)
            {
                1, 3, 5, 7, 8, 10, 12 -> 31
                4, 6, 9, 11 -> 30
                2 -> 29
                else -> 0
            }
        }
        else
        {
            if (year % 400 == 0)
            {
                return when (month)
                {
                    1, 3, 5, 7, 8, 10, 12 -> 31
                    4, 6, 9, 11 -> 30
                    2 -> 29
                    else -> 0
                }
            }
            else
            {
                return when (month)
                {
                    1,3, 5, 7, 8, 10, 12 -> 31
                    4, 6, 9, 11 -> 30
                    2 -> 28
                    else -> 0
                }
            }
        }
    }
    else
    {
        return when (month)
        {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> 28
            else -> 0
        }
    }
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean
{
    if (sqrt(sqr(x2 - x1) + sqr(y2 - y1)) + r1 <= r2)
    {
        return true
    }
    else
    {
        return false
    }

}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean
{
    if (    (b <= r && c <= s) || (b <= s && c <= r) ||
            (a <= r && b <= s) || (a <= s && b <= r) ||
            (a <= r && c <= s) || (a <= s && c <= r)    )
    {
        return true
    }
    else
    {
        return false
    }
}
