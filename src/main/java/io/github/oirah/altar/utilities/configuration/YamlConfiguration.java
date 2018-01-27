/*
 * Copyright <2018> <Ben Ortiz>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.oirah.altar.utilities.configuration;

import io.github.oirah.altar.AltarPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class YamlConfiguration implements IConfiguration {

    private AltarPlugin altar = AltarPlugin.getInstance();

    private File file;
    private org.bukkit.configuration.file.YamlConfiguration configuration;

    public YamlConfiguration(String name) {
        this.file = new File(this.altar.getDataFolder(), name);

        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            this.altar.saveResource(name, false);
        }

        try {
            this.configuration = new org.bukkit.configuration.file.YamlConfiguration();
            this.configuration.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Object get(String path) {
        return this.configuration.get(path);
    }

    @Override
    public String getRawString(String path) {
        return this.configuration.getString(path);
    }

    @Override
    public String getString(String path) {
        return ChatColor.translateAlternateColorCodes('&', this.getRawString(path));
    }

    @Override
    public List<String> getStringList(String path) {
        return this.configuration.getStringList(path);
    }

    @Override
    public int getInteger(String path) {
        return this.configuration.getInt(path);
    }

    @Override
    public List<Integer> getIntegerList(String path) {
        return this.configuration.getIntegerList(path);
    }

    @Override
    public double getDouble(String path) {
        return this.configuration.getDouble(path);
    }

    @Override
    public List<Double> getDoubleList(String path) {
        return this.configuration.getDoubleList(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return this.configuration.getBoolean(path);
    }

    @Override
    public List<Boolean> getBooleanList(String path) {
        return this.configuration.getBooleanList(path);
    }

    @Override
    public void set(String path, Object object) {
        this.configuration.set(path, object);
    }

    @Override
    public void save() {
        this.altar.saveResource(this.file.getName(), false);
    }
}
