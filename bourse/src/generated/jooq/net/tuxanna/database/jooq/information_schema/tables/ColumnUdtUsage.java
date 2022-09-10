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
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * one row for each usage of a user defined type as the type of a column
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ColumnUdtUsage extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.COLUMN_UDT_USAGE</code>
     */
    public static final ColumnUdtUsage COLUMN_UDT_USAGE = new ColumnUdtUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.COLUMN_UDT_USAGE.UDT_CATALOG</code>.
     */
    public final TableField<Record, String> UDT_CATALOG = createField(DSL.name("UDT_CATALOG"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLUMN_UDT_USAGE.UDT_SCHEMA</code>.
     */
    public final TableField<Record, String> UDT_SCHEMA = createField(DSL.name("UDT_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLUMN_UDT_USAGE.UDT_NAME</code>.
     */
    public final TableField<Record, String> UDT_NAME = createField(DSL.name("UDT_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.COLUMN_UDT_USAGE.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLUMN_UDT_USAGE.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLUMN_UDT_USAGE.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLUMN_UDT_USAGE.COLUMN_NAME</code>.
     */
    public final TableField<Record, String> COLUMN_NAME = createField(DSL.name("COLUMN_NAME"), SQLDataType.VARCHAR(128), this, "");

    private ColumnUdtUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private ColumnUdtUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each usage of a user defined type as the type of a column"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.COLUMN_UDT_USAGE</code> table
     * reference
     */
    public ColumnUdtUsage(String alias) {
        this(DSL.name(alias), COLUMN_UDT_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.COLUMN_UDT_USAGE</code> table
     * reference
     */
    public ColumnUdtUsage(Name alias) {
        this(alias, COLUMN_UDT_USAGE);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.COLUMN_UDT_USAGE</code> table reference
     */
    public ColumnUdtUsage() {
        this(DSL.name("COLUMN_UDT_USAGE"), null);
    }

    public <O extends Record> ColumnUdtUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, COLUMN_UDT_USAGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public ColumnUdtUsage as(String alias) {
        return new ColumnUdtUsage(DSL.name(alias), this);
    }

    @Override
    public ColumnUdtUsage as(Name alias) {
        return new ColumnUdtUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ColumnUdtUsage rename(String name) {
        return new ColumnUdtUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ColumnUdtUsage rename(Name name) {
        return new ColumnUdtUsage(name, null);
    }
}
