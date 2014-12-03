package ua.krasnyanskiy.jpa.demo.dto.common;

import java.util.Collection;

/**
 * @author Alexander Krasnyanskiy
 */
public class YmlSqlConfigurationAdapter {

    private Collection<String> populate;
    private Collection<String> clean;

    public YmlSqlConfigurationAdapter() {
    }

    public Collection<String> getPopulate() {
        return populate;
    }

    public void setPopulate(Collection<String> populate) {
        this.populate = populate;
    }

    public Collection<String> getClean() {
        return clean;
    }

    public void setClean(Collection<String> clean) {
        this.clean = clean;
    }
}
