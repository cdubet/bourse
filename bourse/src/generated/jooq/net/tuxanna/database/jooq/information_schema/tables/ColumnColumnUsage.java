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
 * one row for each usage of a column in a generated column definition
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ColumnColumnUsage extends TableImpl<Record> {

    private static final long serialVersionUID = -1933736862;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.COLUMN_COLUMN_USAGE</code>
     */
    public static final ColumnColumnUsage COLUMN_COLUMN_USAGE = new ColumnColumnUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.COLUMN_COLUMN_USAGE.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLUMN_COLUMN_USAGE.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLUMN_COLUMN_USAGE.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLUMN_COLUMN_USAGE.COLUMN_NAME</code>.
     */
    public final TableField<Record, String> COLUMN_NAME = createField(DSL.name("COLUMN_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLUMN_COLUMN_USAGE.DEPENDENT_COLUMN</code>.
     */
    public final TableField<Record, String> DEPENDENT_COLUMN = createField(DSL.name("DEPENDENT_COLUMN"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * Create a <code>INFORMATION_SCHEMA.COLUMN_COLUMN_USAGE</code> table reference
     */
    public ColumnColumnUsage() {
        this(DSL.name("COLUMN_COLUMN_USAGE"), null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.COLUMN_COLUMN_USAGE</code> table reference
     */
    public ColumnColumnUsage(String alias) {
        this(DSL.name(alias), COLUMN_COLUMN_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.COLUMN_COLUMN_USAGE</code> table reference
     */
    public ColumnColumnUsage(Name alias) {
        this(alias, COLUMN_COLUMN_USAGE);
    }

    private ColumnColumnUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private ColumnColumnUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each usage of a column in a generated column definition"), TableOptions.table());
    }

    public <O extends Record> ColumnColumnUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, COLUMN_COLUMN_USAGE);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public ColumnColumnUsage as(String alias) {
        return new ColumnColumnUsage(DSL.name(alias), this);
    }

    @Override
    public ColumnColumnUsage as(Name alias) {
        return new ColumnColumnUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ColumnColumnUsage rename(String name) {
        return new ColumnColumnUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ColumnColumnUsage rename(Name name) {
        return new ColumnColumnUsage(name, null);
    }
}
