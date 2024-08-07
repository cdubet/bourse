/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.information_schema.tables;


import net.tuxanna.database.jooq.information_schema.InformationSchema;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JarJarUsage extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.JAR_JAR_USAGE</code>
     */
    public static final JarJarUsage JAR_JAR_USAGE = new JarJarUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.JAR_JAR_USAGE.PATH_JAR_CATALOG</code>.
     */
    public final TableField<Record, String> PATH_JAR_CATALOG = createField(DSL.name("PATH_JAR_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.JAR_JAR_USAGE.PATH_JAR_SCHAMA</code>.
     */
    public final TableField<Record, String> PATH_JAR_SCHAMA = createField(DSL.name("PATH_JAR_SCHAMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.JAR_JAR_USAGE.PATH_JAR_NAME</code>.
     */
    public final TableField<Record, String> PATH_JAR_NAME = createField(DSL.name("PATH_JAR_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.JAR_JAR_USAGE.JAR_CATALOG</code>.
     */
    public final TableField<Record, String> JAR_CATALOG = createField(DSL.name("JAR_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.JAR_JAR_USAGE.JAR_SCHEMA</code>.
     */
    public final TableField<Record, String> JAR_SCHEMA = createField(DSL.name("JAR_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.JAR_JAR_USAGE.JAR_NAME</code>.
     */
    public final TableField<Record, String> JAR_NAME = createField(DSL.name("JAR_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    private JarJarUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private JarJarUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.JAR_JAR_USAGE</code> table
     * reference
     */
    public JarJarUsage(String alias) {
        this(DSL.name(alias), JAR_JAR_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.JAR_JAR_USAGE</code> table
     * reference
     */
    public JarJarUsage(Name alias) {
        this(alias, JAR_JAR_USAGE);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.JAR_JAR_USAGE</code> table reference
     */
    public JarJarUsage() {
        this(DSL.name("JAR_JAR_USAGE"), null);
    }

    public <O extends Record> JarJarUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, JAR_JAR_USAGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public JarJarUsage as(String alias) {
        return new JarJarUsage(DSL.name(alias), this);
    }

    @Override
    public JarJarUsage as(Name alias) {
        return new JarJarUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public JarJarUsage rename(String name) {
        return new JarJarUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public JarJarUsage rename(Name name) {
        return new JarJarUsage(name, null);
    }
}
