package ua.krasnyanskiy.jpa.demo.dto.common;

import java.util.Collection;

/**
 * @author Alexander Krasnyanskiy
 * @since 1.0
 */
public class YmlSqlConfigurationAdapter {

    private Collection<String> populate;
    private Collection<String> clean;

    public YmlSqlConfigurationAdapter() {
    }

    public Collection<String> getPopulateQueries() {
        return populate;
    }

    public void setPopulate(Collection<String> populate) {
        this.populate = populate;
    }

    public Collection<String> getCleanQueries() {
        return clean;
    }

    public void setClean(Collection<String> clean) {
        this.clean = clean;
    }
}
