package filmclub.FilmClub.Kotlin.SpringBoot.service

fun updateWeather(degrees: Int) {
    val (description, colour) =
        when {
            degrees < 5 -> Pair("cold", "blue")
            degrees < 23 -> Pair("mild", "orange")
            else -> Pair("hot", "red")
        }
}
