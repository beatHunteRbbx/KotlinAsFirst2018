@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1
import lesson2.task2.daysInMonth


/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        }
        else {
            println("Прошло секунд с начала суток: $seconds")
        }
    }
    else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val months = mapOf(
            "января" to 1,
            "февраля" to 2,
            "марта" to 3,
            "апреля" to 4,
            "мая" to 5,
            "июня" to 6,
            "июля" to 7,
            "августа" to 8,
            "сентября" to 9,
            "октября" to 10,
            "ноября" to 11,
            "декабря" to 12
    )
    val parts = str.split(" ")
    val exception = NumberFormatException()
    return try {
        if (    (parts.size != 3) ||
                (!months.contains(parts[1])) ||
                (parts[0].toInt() > 31) ||
                (parts[0].toInt() > daysInMonth(months[parts[1]]!!, parts[2].toInt()))) throw exception
        else String.format("%02d.%02d.%d", parts[0].toInt(), months[parts[1]], parts[2].toInt())
    }
    catch (e: NumberFormatException) {
        ""
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val months = mapOf(
            "01" to "января",
            "02" to "февраля",
            "03" to "марта",
            "04" to "апреля",
            "05" to "мая",
            "06" to "июня",
            "07" to "июля",
            "08" to "августа",
            "09" to "сентября",
            "10" to "октября",
            "11" to "ноября",
            "12" to "декабря"
    )
    val parts = digital.split(".")
    val exception = NumberFormatException()
    return try {
        if (    (parts.size != 3) ||
                (!months.contains(parts[1])) ||
                (parts[0].toInt() > 31) ||
                (parts[0].toInt() > daysInMonth(parts[1].toInt(), parts[2].toInt()))) throw exception
        else String.format("%d %s %d", parts[0].toInt(), months[parts[1]], parts[2].toInt())
    }
    catch (exception: NumberFormatException) {
        ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String =
    if (    Regex("""[^0-9+\-()\s]|\s$""").containsMatchIn(phone) ||
            !Regex("""[0-9]""").containsMatchIn(phone) ||
            phone == " " ||
            phone.isEmpty()) ""
    else phone.replace(Regex("""^|[^0-9]"""), "")

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var jumpsList = listOf<String>()
    var longestJump = -1
    if (Regex("""[^0-9%\-\s]""").containsMatchIn(jumps) ||
                !Regex("""[0-9]""").containsMatchIn(jumps)) -1
    else jumpsList = jumps.replace(Regex("""[^0-9\s]"""), "").split(" ")
    for (jump in jumpsList) {
        if (jump == "") continue
        else if (jump.toInt() > longestJump) longestJump = jump.toInt()
    }
    return longestJump
}


/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var jumpsList = listOf<String>()
    var highestJump = -1
    if (Regex("""[^0-9+%\-\s]""").containsMatchIn(jumps) ||
                !Regex("""[0-9]""").containsMatchIn(jumps)) -1
    else jumpsList = jumps.replace(Regex("""\d+\s[%\-]+(\s|$)"""), "").replace(Regex("""[^0-9\s]"""), "").split(" ")
    for (jump in jumpsList) {
        if (jump == "") continue
        else if (jump.toInt() > highestJump) highestJump = jump.toInt()
    }
    return highestJump
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int =
    if (    Regex("""[^\s\d+-]""").containsMatchIn(expression) ||
            Regex("""\+\d|\d\+|-\d|\d-""").containsMatchIn(expression) ||
            expression.isEmpty()) throw  IllegalArgumentException()
    else {
        val expressionWithoutSpaces = expression.split(" ")
        if (!Regex("""[0-9]""").containsMatchIn(expression)) throw IllegalArgumentException()
        var answer = expressionWithoutSpaces[0].toInt()
        for (i in 1 until expressionWithoutSpaces.size - 1 step 2) {
            if (expressionWithoutSpaces[i] == "+") answer += expressionWithoutSpaces[i + 1].toInt()
            if (expressionWithoutSpaces[i] == "-") answer -= expressionWithoutSpaces[i + 1].toInt()
        }
        answer
    }

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val strWords = str.toLowerCase().split(" ")
    var index = 0
    var hasDuplicate = false
    for (i in 0 until strWords.size - 1) {
        if (strWords[i] == strWords[i + 1]) {
            hasDuplicate = true
            break
        }
        index += strWords[i].length + 1
    }
    return if (hasDuplicate) index
    else -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    var maxPrice = -1.0
    var mostExpensiveProduct = ""
    val productsWithPrice = description.split(";")
    var (product, price) = Pair("", 0.0)
    try {
        for (member in productsWithPrice) {
            if (member.trim().split(" ").size % 2 != 0) throw NumberFormatException()
            product = member.trim().split(" ")[0]
            price = member.trim().split(" ")[1].toDouble()
            if (price < 0) throw NumberFormatException()
            if (price > maxPrice) {
                mostExpensiveProduct = product
                maxPrice = price
            }
        }
    }
    catch (e: NumberFormatException){
        ""
    }
    return mostExpensiveProduct
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    if (roman.isNotEmpty()) {
        val oneRomanLetter: Map<String, Int> = mapOf(
                "M" to 1000,
                "D" to 500,
                "C" to 100,
                "L" to 50,
                "X" to 10,
                "V" to 5,
                "I" to 1)
        var answer = 0
        try {
            for (number in roman) {
                if (oneRomanLetter.contains(number.toString())) {
                    answer += oneRomanLetter[number.toString()]!!
                } else throw NumberFormatException()
            }
        } catch (e: NumberFormatException) {
            return -1
        }
        if (Regex("""CM""").containsMatchIn(roman)) answer -= 200
        if (Regex("""CD""").containsMatchIn(roman)) answer -= 200
        if (Regex("""XC""").containsMatchIn(roman)) answer -= 20
        if (Regex("""XL""").containsMatchIn(roman)) answer -= 20
        if (Regex("""IX""").containsMatchIn(roman)) answer -= 2
        if (Regex("""IV""").containsMatchIn(roman)) answer -= 2
        return answer
    }
    else return -1
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    var cellsList = mutableListOf<Int>()        //создал лист для ячеек
    for (i in 0 until cells) cellsList.add(0)   //заполняю лист ячеек нулями
    if (Regex("[^\\s><+-\\[\\]]|[0-9]").containsMatchIn(commands) ||
            commands.count { it == ']' } != commands.count { it == '[' }) throw IllegalArgumentException()
    var cellsCounter = cells / 2 //счётчик ячеек, нужен чтобы обращаться по индексу к определённой ячейке
    var command = 0   //счетчик команд, нужен чтобы обращаться по индексу к определенной команде в commands
    var allCommands = 0 //счётчик общего числа команд для цикла while
    var notToDo = false //флаг выполнять следующие команды или нет. используется если команда = '[' символ != 0, тогда цикл [] должен пропускаться
    var haveLoop = false //флаг чтобы программа понимала что у нас в данный момент есть цикл и прибавляла returns
    var loopCounter = 0  //чётчик циклов, чтобы понимать, делать ли haveLoop = false или нет
    var wasExtraPlus = false
    var returns = mutableListOf<Int>()    //кол-во операций в цикле [] на которые надо будет возвращаться. создал лист, так как может быть бесконечное кол-во вложенных циклов
    //УБРАТЬ ПОСЛЕ ПОЛНОГО ВЫПОЛНЕНИЯ
    var reached = false
    //
    try {
        while (allCommands < limit && command < commands.length) {
            wasExtraPlus = false
            cellsList[cellsCounter] //вызов текущей ячейки для проверки, не вышел ли конвейер за границы
            when (commands[command]) {
                '+' -> {
                    if (!notToDo) cellsList[cellsCounter]++
                    if (haveLoop) returns[loopCounter - 1]++
                }
                '-' -> {
                    if (!notToDo) cellsList[cellsCounter]--
                    if (haveLoop) returns[loopCounter - 1]++
                }
                '<' -> {
                    if (!notToDo) cellsCounter--
                    if (haveLoop) returns[loopCounter - 1]++
                }
                '>' -> {
                    if (!notToDo) cellsCounter++
                    if (haveLoop) returns[loopCounter - 1]++
                }
                ' ' -> if (haveLoop) returns[loopCounter - 1]++
                '[' -> if (cellsList[cellsCounter] != 0) {
                    haveLoop = true     //если ячейка не равна 0, то начинается цикл
                    returns.add(0)
                    loopCounter++
                    returns[loopCounter - 1] = 0
                } else {
                    notToDo = true
                    if (haveLoop) returns[loopCounter - 1]++
                }
                ']' -> {
                    if (notToDo && haveLoop) {
                        returns[loopCounter - 1]++
                        notToDo = false
                        command++
                        wasExtraPlus = true
                    }
                    else {
                        if (cellsList[cellsCounter] == 0 && !notToDo && haveLoop) {
                            loopCounter--
                            returns.remove(returns.last())
                        }
                        notToDo = false
                        if (loopCounter == 0) haveLoop = false  //если ячейка равна 0 и больше нет никаких вложенных циклов, то общий цикл заканчивается
                    }
                }
            }
            if (haveLoop && commands[command] == ']') {
                command -= returns[loopCounter - 1]      //если операции идут в [], то возвращаемся назад
                returns[loopCounter - 1] = 0
            } else if (!wasExtraPlus) command++                   //если нет, то переходим к следующей операции
            allCommands++
            //УБРАТЬ ПОСЛЕ ПОЛНОГО ВЫПОЛНЕНИЯ
            if (command == 42) {
                reached = true
            }
            //
        }
    } catch (e: IndexOutOfBoundsException) {   //если происходит заход за границу конвейера, то ловим исключение
        throw IllegalStateException()       //и бросаем нужное нам исключение, чтобы программа вернула его
    }
    return cellsList
}
