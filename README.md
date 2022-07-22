# Java-Utility

## Jitpack

### Gradle

```gradle
plugins {
    id 'java'
    id "com.github.johnrengelman.shadow" version "7.1.2"
}
repositories {
	maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.TheCurano:Java-Utility:Tag'
}
```

### Maven
    
```maven
<repositories>
    <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
    </repository>
</repositories>
	
<dependency>
    <groupId>com.github.TheCurano</groupId>
	<artifactId>Java-Utility</artifactId>
	<version>Tag</version>
</dependency>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.2.4</version>
    <executions>
        <execution>
        <phase>package</phase>
            <goals>
                <goal>shade</goal>
             </goals>
        </execution>
    </executions>
</plugin>
```

## YamlConfiguration
From: https://www.spigotmc.org/wiki/using-the-bungee-configuration-system/

### Loading a Configuration

```java
Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File("./", "config.yml"));
```

### Saving a Configuration

```java
ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File("./", "config.yml"));
```

### Load from resources (Jar)

```java
// Args: Path, Filename
File configFile = new File("./", "config.yml");

if (!configFile.getParentFile().exists()) {
    configFile.getParentFile().mkdir();
}

if (!configFile.exists()) {
    try (InputStream in = new Resources().getResourceAsStream("config.yml")) {
        Files.copy(in, configFile.toPath());
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```