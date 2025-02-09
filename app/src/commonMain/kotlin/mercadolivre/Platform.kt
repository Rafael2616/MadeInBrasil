package mercadolivre

expect val platform: Platform

enum class Platform {
    Android,
    Wasm
}