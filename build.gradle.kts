plugins {
    id("java-library")
}

group = "cn.tofucat.gdx"
version = project.properties["gdxVersion"]!!

repositories {
    mavenCentral()
}

dependencies {
    api("com.badlogicgames.gdx:gdx:${project.properties["gdxVersion"]}")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}