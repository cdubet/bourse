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
 * one row for each usage of a table column in a trigger definition
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TriggerColumnUsage extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.TRIGGER_COLUMN_USAGE</code>
     */
    public static final TriggerColumnUsage TRIGGER_COLUMN_USAGE = new TriggerColumnUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_COLUMN_USAGE.TRIGGER_CATALOG</code>.
     */
    public final TableField<Record, String> TRIGGER_CATALOG = createField(DSL.name("TRIGGER_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_COLUMN_USAGE.TRIGGER_SCHEMA</code>.
     */
    public final TableField<Record, String> TRIGGER_SCHEMA = createField(DSL.name("TRIGGER_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_COLUMN_USAGE.TRIGGER_NAME</code>.
     */
    public final TableField<Record, String> TRIGGER_NAME = createField(DSL.name("TRIGGER_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_COLUMN_USAGE.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_COLUMN_USAGE.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_COLUMN_USAGE.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_COLUMN_USAGE.COLUMN_NAME</code>.
     */
    public final TableField<Record, String> COLUMN_NAME = createField(DSL.name("COLUMN_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    private TriggerColumnUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private TriggerColumnUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each usage of a table column in a trigger definition"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.TRIGGER_COLUMN_USAGE</code>
     * table reference
     */
    public TriggerColumnUsage(String alias) {
        this(DSL.name(alias), TRIGGER_COLUMN_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.TRIGGER_COLUMN_USAGE</code>
     * table reference
     */
    public TriggerColumnUsage(Name alias) {
        this(alias, TRIGGER_COLUMN_USAGE);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.TRIGGER_COLUMN_USAGE</code> table
     * reference
     */
    public TriggerColumnUsage() {
        this(DSL.name("TRIGGER_COLUMN_USAGE"), null);
    }

    public <O extends Record> TriggerColumnUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, TRIGGER_COLUMN_USAGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public TriggerColumnUsage as(String alias) {
        return new TriggerColumnUsage(DSL.name(alias), this);
    }

    @Override
    public TriggerColumnUsage as(Name alias) {
        return new TriggerColumnUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TriggerColumnUsage rename(String name) {
        return new TriggerColumnUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TriggerColumnUsage rename(Name name) {
        return new TriggerColumnUsage(name, null);
    }
}
