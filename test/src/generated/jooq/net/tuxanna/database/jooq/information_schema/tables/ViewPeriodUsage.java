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
 * one row for each usage of period in a view
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewPeriodUsage extends TableImpl<Record> {

    private static final long serialVersionUID = 1646620682;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.VIEW_PERIOD_USAGE</code>
     */
    public static final ViewPeriodUsage VIEW_PERIOD_USAGE = new ViewPeriodUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_PERIOD_USAGE.VIEW_CATALOG</code>.
     */
    public final TableField<Record, String> VIEW_CATALOG = createField(DSL.name("VIEW_CATALOG"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_PERIOD_USAGE.VIEW_SCHEMA</code>.
     */
    public final TableField<Record, String> VIEW_SCHEMA = createField(DSL.name("VIEW_SCHEMA"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_PERIOD_USAGE.VIEW_NAME</code>.
     */
    public final TableField<Record, String> VIEW_NAME = createField(DSL.name("VIEW_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_PERIOD_USAGE.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_PERIOD_USAGE.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_PERIOD_USAGE.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_PERIOD_USAGE.PERIOD_NAME</code>.
     */
    public final TableField<Record, String> PERIOD_NAME = createField(DSL.name("PERIOD_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * Create a <code>INFORMATION_SCHEMA.VIEW_PERIOD_USAGE</code> table reference
     */
    public ViewPeriodUsage() {
        this(DSL.name("VIEW_PERIOD_USAGE"), null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.VIEW_PERIOD_USAGE</code> table reference
     */
    public ViewPeriodUsage(String alias) {
        this(DSL.name(alias), VIEW_PERIOD_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.VIEW_PERIOD_USAGE</code> table reference
     */
    public ViewPeriodUsage(Name alias) {
        this(alias, VIEW_PERIOD_USAGE);
    }

    private ViewPeriodUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private ViewPeriodUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each usage of period in a view"), TableOptions.table());
    }

    public <O extends Record> ViewPeriodUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, VIEW_PERIOD_USAGE);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public ViewPeriodUsage as(String alias) {
        return new ViewPeriodUsage(DSL.name(alias), this);
    }

    @Override
    public ViewPeriodUsage as(Name alias) {
        return new ViewPeriodUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewPeriodUsage rename(String name) {
        return new ViewPeriodUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewPeriodUsage rename(Name name) {
        return new ViewPeriodUsage(name, null);
    }
}