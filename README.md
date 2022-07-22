# Java-Utility

## Jitpack

### Gradle

```gradle
Coming soon...
```

### Maven
    
```maven
Coming soon...
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