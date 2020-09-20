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
 * one row for each table column usage in a routine
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoutineColumnUsage extends TableImpl<Record> {

    private static final long serialVersionUID = -1474256390;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE</code>
     */
    public static final RoutineColumnUsage ROUTINE_COLUMN_USAGE = new RoutineColumnUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE.SPECIFIC_CATALOG</code>.
     */
    public final TableField<Record, String> SPECIFIC_CATALOG = createField(DSL.name("SPECIFIC_CATALOG"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE.SPECIFIC_SCHEMA</code>.
     */
    public final TableField<Record, String> SPECIFIC_SCHEMA = createField(DSL.name("SPECIFIC_SCHEMA"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE.SPECIFIC_NAME</code>.
     */
    public final TableField<Record, String> SPECIFIC_NAME = createField(DSL.name("SPECIFIC_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE.ROUTINE_CATALOG</code>.
     */
    public final TableField<Record, String> ROUTINE_CATALOG = createField(DSL.name("ROUTINE_CATALOG"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE.ROUTINE_SCHEMA</code>.
     */
    public final TableField<Record, String> ROUTINE_SCHEMA = createField(DSL.name("ROUTINE_SCHEMA"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE.ROUTINE_NAME</code>.
     */
    public final TableField<Record, String> ROUTINE_NAME = createField(DSL.name("ROUTINE_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE.COLUMN_NAME</code>.
     */
    public final TableField<Record, String> COLUMN_NAME = createField(DSL.name("COLUMN_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * Create a <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE</code> table reference
     */
    public RoutineColumnUsage() {
        this(DSL.name("ROUTINE_COLUMN_USAGE"), null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE</code> table reference
     */
    public RoutineColumnUsage(String alias) {
        this(DSL.name(alias), ROUTINE_COLUMN_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.ROUTINE_COLUMN_USAGE</code> table reference
     */
    public RoutineColumnUsage(Name alias) {
        this(alias, ROUTINE_COLUMN_USAGE);
    }

    private RoutineColumnUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private RoutineColumnUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each table column usage in a routine"), TableOptions.table());
    }

    public <O extends Record> RoutineColumnUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, ROUTINE_COLUMN_USAGE);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public RoutineColumnUsage as(String alias) {
        return new RoutineColumnUsage(DSL.name(alias), this);
    }

    @Override
    public RoutineColumnUsage as(Name alias) {
        return new RoutineColumnUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RoutineColumnUsage rename(String name) {
        return new RoutineColumnUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RoutineColumnUsage rename(Name name) {
        return new RoutineColumnUsage(name, null);
    }
}