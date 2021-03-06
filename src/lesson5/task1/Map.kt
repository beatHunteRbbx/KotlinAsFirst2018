@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import kotlin.math.max

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
        shoppingList: List<String>,
        costs: Map<String, Double>): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
        phoneBook: MutableMap<String, String>,
        countryCode: String) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
        text: List<String>,
        vararg fillerWords: String): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val workMap = mapA.toMutableMap()
    mapB.forEach {
        val mapAValue = mapA[it.key]
        if (mapAValue != it.value && mapAValue != null)
            workMap[it.key] = "${mapAValue}, ${it.value}"
        else workMap[it.key] = it.value
    }
    return workMap
}

/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val workMap = mutableMapOf<Int, MutableList<String>>()
    for ((student, mark) in grades) {
        workMap.getOrPut(mark, ::mutableListOf).add(student)
    }
    workMap.forEach { it.value.sortDescending() }
    return workMap
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean = a.all { b[it.key] == it.value }


/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val answerMap = mutableMapOf<String, Double>()
    val numberOfProducts = mutableMapOf<String, Int>()
    val pricesOfProducts = mutableMapOf<String, Double>()
    for ((product, price) in stockPrices) {
        numberOfProducts[product] = (numberOfProducts[product] ?: 0) + 1
        pricesOfProducts[product] = (pricesOfProducts[product] ?: 0.0) + price
    }
    numberOfProducts.forEach {
        if (pricesOfProducts.contains(it.key)) answerMap[it.key] = pricesOfProducts[it.key]!! / it.value
    }
    return answerMap
}


/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var price = Double.POSITIVE_INFINITY
    var name: String? = null
    stuff.forEach {
        if (it.value.first == kind && price > it.value.second) {
            price = it.value.second
            name = it.key
        }
    }
    return name
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    //вершины - ключи. добавляем ключ и их значения в массив, если определенное имя уже есть, то не добавляем его. Нужно сделать проверку на это
    //поробовать обход графа в глубину
    if (friends.isEmpty()) return friends
    val answerMap = friends.toMutableMap()
    for (pair in friends) {
        for (friend in pair.value) {
            if (!friends.contains(friend)) answerMap[friend] = emptySet()
        }
    }
    val nodes = mutableMapOf<String, Boolean>() //Создаем мапу nodes
    friends.forEach { nodes[it.key] = false }   //заполняем nodes: ключ - Имя (узел графа), значение - узел не посещён

    for (pair in friends) {
        nodes[pair.key] = true           //помечаем что узел, который мы только что выбрали, посещён
        for (friend in pair.value) {
            if (friend == pair.key) break
            else if (friends[friend] != null) {
                answerMap[pair.key] = pair.value.union(friends[friend]!!)
                nodes[friend] = true
            }
        }
    }
    for (pair in answerMap) {
        for (person in pair.value) {
            if (person == pair.key) answerMap[pair.key] = pair.value - person
        }
    }
    return answerMap

    /*var friendsMap = friends.toMutableMap()
    val answerMap = friends.toMutableMap()
    for (person in friendsMap) {
        for (friend in person.value) {
            if (!friendsMap.contains(friend)) answerMap[friend] = emptySet()
        }
    }
    friendsMap = answerMap
    for (person in friendsMap) {
        for (friend in person.value) if (friendsMap.containsKey(friend)) answerMap[person.key] = person.value.union(friendsMap[friend]!!) //нужно сравнивать не через одно рукопожатие, а через любое количество
    }
    friendsMap = answerMap
    for ((person, comrades) in friendsMap) {
        for (friend in comrades) if (friend == person) answerMap[person] = answerMap[person]!! - friend
    }
    return answerMap*/
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit =
        b.forEach { if (a[it.key] == b[it.key]) a.remove(it.key) }

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> = a.toSet().intersect(b).toList()

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean =
        word.all { chars.joinToString().toLowerCase().toSet().contains(it.toLowerCase()) }

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val repeats = mutableMapOf<String, Int>()
    for (letter in list) {
        repeats[letter] = repeats.getOrDefault(letter, 0) + 1
    }
    return repeats.filter { it.value > 1 }
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    val wordsSorted = words.map { it.toList().sorted() }
    for (i in 0 until words.size) {
        for (j in i + 1 until wordsSorted.size) {
            if (wordsSorted[i] == wordsSorted[j]) {
                return true
            }
        }
    }
    return false
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    //Принцип алгоритма такой:
    //Дан список: 8, 4, 3, 12, 11, 6, 21     number = 7
    //Идем по списку с number
    //Из 7 вычитаем 8. Получается '-1'. '-1' мы не видели в мапе, значит добавляем 8 в мапу: ключ - '8', значение - индекс '8' списке. Идём дальше...
    //Из 7 вычитаем 4. Получается 3. '3' мы не видели в мапе, значит добавляем 4 в мапу
    //Из 7 вычитаем 3. Получается 4. '4' мы ВИДЕЛИ в мапе, значит в этой паре в мапе содержится идекс подходящей пары для тройки.
    //Pair.first = индекс 4; Pair.second = индекс 3
    val mapOfDifferences = mutableMapOf<Int, Int>()
    var answer = Pair(-1, -1)
    for (i in 0 until list.size) {
        if (mapOfDifferences.contains(number - list[i])) answer = Pair(mapOfDifferences[number - list[i]]!!, i)
        else mapOfDifferences[list[i]] = i
    }
    return answer
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    if (treasures.isNotEmpty()) {
        val suitableTreasures = Array(capacity + 1) { Array(treasures.size + 1) { 0 } } //двумерный массив для заполнения таблицы
        val answerSet = mutableSetOf<String>()
        //Создаём список имён и заполняем его именами предметов из ассоциативного массива
        val nameOfTreasures = mutableListOf<String>()
        for (treasure in treasures) {
            nameOfTreasures.add(treasure.key)
        }
        //------------------------------------------------------------------------------------------------------------
        //Заполняем первый столбец двойного массива
        for (weight in 1..capacity) {
            if (treasures[nameOfTreasures[0]]!!.first <= weight) {
                suitableTreasures[weight][0] = treasures[nameOfTreasures[0]]!!.second
                answerSet.add(nameOfTreasures[0])
            }
        }
        //---------------------------------
        var volume = 0
        var n = 0 //n - номер предмета. нужен для использования в двумерном массиве
        for (treasure in treasures) {
            var hasFilling = false
            n++
            for (weight in 1..capacity) {
                if (treasure.value.first <= weight && volume + treasure.value.first <= capacity) {
                    val a = suitableTreasures[weight - treasure.value.first][n - 1].toDouble() + treasure.value.second.toDouble()
                    val b = suitableTreasures[weight][n - 1].toDouble()
                    suitableTreasures[weight][n] = max(a, b).toInt()
                    if (max(a, b) == a) {
                        answerSet.add(treasure.key)
                        hasFilling = true
                    }
                }
            }
            if (hasFilling) volume += treasure.value.first
        }
        return answerSet
    } else return emptySet()
}
