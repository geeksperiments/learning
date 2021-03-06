class Roman(private val input: String) {
    private val rules = listOf(
            Rule("M", 1000),
            Rule("CM", 900),
            Rule("D", 500),
            Rule("CD", 400),
            Rule("C", 100),
            Rule("XC", 90),
            Rule("L", 50),
            Rule("XL", 40),
            Rule("X", 10),
            Rule("IX", 9),
            Rule("V", 5),
            Rule("IV", 4),
            Rule("I", 1)
    )

    fun toArabic(): Int = rules
            .fold(Conversion(input)) { acc, rule -> acc.apply(rule) }
            .failIfInputRemains()
            .sum
}