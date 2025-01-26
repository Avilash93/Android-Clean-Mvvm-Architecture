package com.example.cleanmvvmarchitecture.core.domain.model

data class Language(
    val code: String,
    val name: String,
    val flag: String
)

val supportedLanguages = listOf(
    Language("en", "English", "🇺🇸"),
    Language("es", "Español", "🇪🇸"),
    Language("fr", "Français", "🇫🇷"),
    Language("de", "Deutsch", "🇩🇪"),
    Language("it", "Italiano", "🇮🇹"),
    Language("ja", "日本語", "🇯🇵")
) 