plugins {
	id("com.github.johnrengelman.shadow") version("7.1.2")
	id("net.minecrell.plugin-yml.bukkit") version("0.5.2")
	`java-library`
	`maven-publish`
}

val directory = property("group") as String
val release = property("version") as String
val libraries = property("libs") as String

repositories {
	maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
	maven("https://jitpack.io/")
	maven("https://oss.sonatype.org/content/groups/public/")
	maven("https://repo.unnamed.team/repository/unnamed-public/")
	mavenCentral()
}

dependencies {
	compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
	
	compileOnly("com.github.MilkBowl:VaultAPI:1.7")
	
	implementation("net.objecthunter:exp4j:0.4.8")
	implementation("commons-lang:commons-lang:2.6")
	implementation("me.fixeddev:commandflow-universal:0.5.3")
	implementation("me.fixeddev:commandflow-universal:0.5.2")
	implementation("com.github.InitSync:XConfig:1.0.5")
}

tasks {
	shadowJar {
		archiveFileName.set("Workity-$release.jar")
		destinationDirectory.set(file("$rootDir/bin/"))
		minimize()
		
		relocate("org.apache.commons", "$libraries.commons")
		relocate("net.objecthunter.exp4j", "$libraries.exp4j")
		relocate("me.fixeddev.commandflow", "$libraries.commandflow")
		relocate("net.xconfig", "$libraries.xconfig")
	}
	
	withType<JavaCompile> {
		options.encoding = "UTF-8"
	}
	
	clean {
		delete("$rootDir/bin/")
	}
}

bukkit {
	name = "Workity"
	main = "$directory.Workity"
	authors = listOf("InitSync")
	
	apiVersion = "1.13"
	version = release
	
	softDepend = listOf("Vault")
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = "me.bryang.workity"
			artifactId = "workity"
			version = release
		}
	}
}
