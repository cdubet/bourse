/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq;


import java.util.Arrays;
import java.util.List;

import net.tuxanna.database.jooq.information_schema.InformationSchema;
import net.tuxanna.database.jooq.public_.Public;
import net.tuxanna.database.jooq.system_lobs.SystemLobs;

import org.jooq.Schema;
import org.jooq.impl.CatalogImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultCatalog extends CatalogImpl {

    private static final long serialVersionUID = 1765718209;

    /**
     * The reference instance of <code>DEFAULT_CATALOG</code>
     */
    public static final DefaultCatalog DEFAULT_CATALOG = new DefaultCatalog();

    /**
     * The schema <code>INFORMATION_SCHEMA</code>.
     */
    public final InformationSchema INFORMATION_SCHEMA = InformationSchema.INFORMATION_SCHEMA;

    /**
     * The schema <code>PUBLIC</code>.
     */
    public final Public PUBLIC = Public.PUBLIC;

    /**
     * The schema <code>SYSTEM_LOBS</code>.
     */
    public final SystemLobs SYSTEM_LOBS = SystemLobs.SYSTEM_LOBS;

    /**
     * No further instances allowed
     */
    private DefaultCatalog() {
        super("");
    }

    @Override
    public final List<Schema> getSchemas() {
        return Arrays.<Schema>asList(
            InformationSchema.INFORMATION_SCHEMA,
            Public.PUBLIC,
            SystemLobs.SYSTEM_LOBS);
    }
}