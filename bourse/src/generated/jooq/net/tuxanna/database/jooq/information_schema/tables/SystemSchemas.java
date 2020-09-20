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
 * the accessible schemas defined within this database
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemSchemas extends TableImpl<Record> {

    private static final long serialVersionUID = -68933329;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.SYSTEM_SCHEMAS</code>
     */
    public static final SystemSchemas SYSTEM_SCHEMAS = new SystemSchemas();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SCHEMAS.TABLE_SCHEM</code>. schema name
     */
    public final TableField<Record, String> TABLE_SCHEM = createField(DSL.name("TABLE_SCHEM"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "schema name");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SCHEMAS.TABLE_CATALOG</code>. catalog in which schema is defined
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "catalog in which schema is defined");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SCHEMAS.IS_DEFAULT</code>. whether the described schema is the default schema
     */
    public final TableField<Record, Boolean> IS_DEFAULT = createField(DSL.name("IS_DEFAULT"), org.jooq.impl.SQLDataType.BOOLEAN, this, "whether the described schema is the default schema");

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_SCHEMAS</code> table reference
     */
    public SystemSchemas() {
        this(DSL.name("SYSTEM_SCHEMAS"), null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_SCHEMAS</code> table reference
     */
    public SystemSchemas(String alias) {
        this(DSL.name(alias), SYSTEM_SCHEMAS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_SCHEMAS</code> table reference
     */
    public SystemSchemas(Name alias) {
        this(alias, SYSTEM_SCHEMAS);
    }

    private SystemSchemas(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemSchemas(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("the accessible schemas defined within this database"), TableOptions.table());
    }

    public <O extends Record> SystemSchemas(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_SCHEMAS);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemSchemas as(String alias) {
        return new SystemSchemas(DSL.name(alias), this);
    }

    @Override
    public SystemSchemas as(Name alias) {
        return new SystemSchemas(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemSchemas rename(String name) {
        return new SystemSchemas(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemSchemas rename(Name name) {
        return new SystemSchemas(name, null);
    }
}
