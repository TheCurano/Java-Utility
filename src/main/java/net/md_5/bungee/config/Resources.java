package net.md_5.bungee.config;

import java.io.InputStream;

public class Resources {

    public final InputStream getResourceAsStream(String name)
    {
        return getClass().getClassLoader().getResourceAsStream( name );
    }

}
