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
 * names of indexes generated by the system for each constraint
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemKeyIndexUsage extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.SYSTEM_KEY_INDEX_USAGE</code>
     */
    public static final SystemKeyIndexUsage SYSTEM_KEY_INDEX_USAGE = new SystemKeyIndexUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_KEY_INDEX_USAGE.CONSTRAINT_CATALOG</code>.
     */
    public final TableField<Record, String> CONSTRAINT_CATALOG = createField(DSL.name("CONSTRAINT_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_KEY_INDEX_USAGE.CONSTRAINT_SCHEMA</code>.
     */
    public final TableField<Record, String> CONSTRAINT_SCHEMA = createField(DSL.name("CONSTRAINT_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_KEY_INDEX_USAGE.CONSTRAINT_NAME</code>.
     */
    public final TableField<Record, String> CONSTRAINT_NAME = createField(DSL.name("CONSTRAINT_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_KEY_INDEX_USAGE.INDEX_CATALOG</code>.
     */
    public final TableField<Record, String> INDEX_CATALOG = createField(DSL.name("INDEX_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_KEY_INDEX_USAGE.INDEX_SCHEMA</code>.
     */
    public final TableField<Record, String> INDEX_SCHEMA = createField(DSL.name("INDEX_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_KEY_INDEX_USAGE.INDEX_NAME</code>.
     */
    public final TableField<Record, String> INDEX_NAME = createField(DSL.name("INDEX_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    private SystemKeyIndexUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemKeyIndexUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("names of indexes generated by the system for each constraint"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_KEY_INDEX_USAGE</code>
     * table reference
     */
    public SystemKeyIndexUsage(String alias) {
        this(DSL.name(alias), SYSTEM_KEY_INDEX_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_KEY_INDEX_USAGE</code>
     * table reference
     */
    public SystemKeyIndexUsage(Name alias) {
        this(alias, SYSTEM_KEY_INDEX_USAGE);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_KEY_INDEX_USAGE</code> table
     * reference
     */
    public SystemKeyIndexUsage() {
        this(DSL.name("SYSTEM_KEY_INDEX_USAGE"), null);
    }

    public <O extends Record> SystemKeyIndexUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_KEY_INDEX_USAGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemKeyIndexUsage as(String alias) {
        return new SystemKeyIndexUsage(DSL.name(alias), this);
    }

    @Override
    public SystemKeyIndexUsage as(Name alias) {
        return new SystemKeyIndexUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemKeyIndexUsage rename(String name) {
        return new SystemKeyIndexUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemKeyIndexUsage rename(Name name) {
        return new SystemKeyIndexUsage(name, null);
    }
}
