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
 * one row for each system time or application period
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Periods extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.PERIODS</code>
     */
    public static final Periods PERIODS = new Periods();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.PERIODS.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PERIODS.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PERIODS.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PERIODS.PERIOD_NAME</code>.
     */
    public final TableField<Record, String> PERIOD_NAME = createField(DSL.name("PERIOD_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PERIODS.START_COLUMN_NAME</code>.
     */
    public final TableField<Record, String> START_COLUMN_NAME = createField(DSL.name("START_COLUMN_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PERIODS.END_COLUMN_NAME</code>.
     */
    public final TableField<Record, String> END_COLUMN_NAME = createField(DSL.name("END_COLUMN_NAME"), SQLDataType.VARCHAR(128), this, "");

    private Periods(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Periods(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each system time or application period"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.PERIODS</code> table reference
     */
    public Periods(String alias) {
        this(DSL.name(alias), PERIODS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.PERIODS</code> table reference
     */
    public Periods(Name alias) {
        this(alias, PERIODS);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.PERIODS</code> table reference
     */
    public Periods() {
        this(DSL.name("PERIODS"), null);
    }

    public <O extends Record> Periods(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, PERIODS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public Periods as(String alias) {
        return new Periods(DSL.name(alias), this);
    }

    @Override
    public Periods as(Name alias) {
        return new Periods(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Periods rename(String name) {
        return new Periods(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Periods rename(Name name) {
        return new Periods(name, null);
    }
}
