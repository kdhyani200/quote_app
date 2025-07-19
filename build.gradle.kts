buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.3")
    }
}
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    kotlin("plugin.serialization") version "1.9.0"
}