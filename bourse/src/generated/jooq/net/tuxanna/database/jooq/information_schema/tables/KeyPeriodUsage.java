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
 * one row for each period used in s primary key or unique constraint
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class KeyPeriodUsage extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.KEY_PERIOD_USAGE</code>
     */
    public static final KeyPeriodUsage KEY_PERIOD_USAGE = new KeyPeriodUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.KEY_PERIOD_USAGE.CONSTRAINT_CATALOG</code>.
     */
    public final TableField<Record, String> CONSTRAINT_CATALOG = createField(DSL.name("CONSTRAINT_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.KEY_PERIOD_USAGE.CONSTRAINT_SCHEMA</code>.
     */
    public final TableField<Record, String> CONSTRAINT_SCHEMA = createField(DSL.name("CONSTRAINT_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.KEY_PERIOD_USAGE.CONSTRAINT_NAME</code>.
     */
    public final TableField<Record, String> CONSTRAINT_NAME = createField(DSL.name("CONSTRAINT_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.KEY_PERIOD_USAGE.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.KEY_PERIOD_USAGE.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.KEY_PERIOD_USAGE.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.KEY_PERIOD_USAGE.PERIOD_NAME</code>.
     */
    public final TableField<Record, String> PERIOD_NAME = createField(DSL.name("PERIOD_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    private KeyPeriodUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private KeyPeriodUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each period used in s primary key or unique constraint"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.KEY_PERIOD_USAGE</code> table
     * reference
     */
    public KeyPeriodUsage(String alias) {
        this(DSL.name(alias), KEY_PERIOD_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.KEY_PERIOD_USAGE</code> table
     * reference
     */
    public KeyPeriodUsage(Name alias) {
        this(alias, KEY_PERIOD_USAGE);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.KEY_PERIOD_USAGE</code> table reference
     */
    public KeyPeriodUsage() {
        this(DSL.name("KEY_PERIOD_USAGE"), null);
    }

    public <O extends Record> KeyPeriodUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, KEY_PERIOD_USAGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public KeyPeriodUsage as(String alias) {
        return new KeyPeriodUsage(DSL.name(alias), this);
    }

    @Override
    public KeyPeriodUsage as(Name alias) {
        return new KeyPeriodUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public KeyPeriodUsage rename(String name) {
        return new KeyPeriodUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public KeyPeriodUsage rename(Name name) {
        return new KeyPeriodUsage(name, null);
    }
}
