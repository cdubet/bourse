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
 * one row for each usage of a routine in a trigger definition
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TriggerRoutineUsage extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.TRIGGER_ROUTINE_USAGE</code>
     */
    public static final TriggerRoutineUsage TRIGGER_ROUTINE_USAGE = new TriggerRoutineUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_ROUTINE_USAGE.TRIGGER_CATALOG</code>.
     */
    public final TableField<Record, String> TRIGGER_CATALOG = createField(DSL.name("TRIGGER_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_ROUTINE_USAGE.TRIGGER_SCHEMA</code>.
     */
    public final TableField<Record, String> TRIGGER_SCHEMA = createField(DSL.name("TRIGGER_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_ROUTINE_USAGE.TRIGGER_NAME</code>.
     */
    public final TableField<Record, String> TRIGGER_NAME = createField(DSL.name("TRIGGER_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_ROUTINE_USAGE.SPECIFIC_CATALOG</code>.
     */
    public final TableField<Record, String> SPECIFIC_CATALOG = createField(DSL.name("SPECIFIC_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_ROUTINE_USAGE.SPECIFIC_SCHEMA</code>.
     */
    public final TableField<Record, String> SPECIFIC_SCHEMA = createField(DSL.name("SPECIFIC_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TRIGGER_ROUTINE_USAGE.SPECIFIC_NAME</code>.
     */
    public final TableField<Record, String> SPECIFIC_NAME = createField(DSL.name("SPECIFIC_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    private TriggerRoutineUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private TriggerRoutineUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each usage of a routine in a trigger definition"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.TRIGGER_ROUTINE_USAGE</code>
     * table reference
     */
    public TriggerRoutineUsage(String alias) {
        this(DSL.name(alias), TRIGGER_ROUTINE_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.TRIGGER_ROUTINE_USAGE</code>
     * table reference
     */
    public TriggerRoutineUsage(Name alias) {
        this(alias, TRIGGER_ROUTINE_USAGE);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.TRIGGER_ROUTINE_USAGE</code> table
     * reference
     */
    public TriggerRoutineUsage() {
        this(DSL.name("TRIGGER_ROUTINE_USAGE"), null);
    }

    public <O extends Record> TriggerRoutineUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, TRIGGER_ROUTINE_USAGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public TriggerRoutineUsage as(String alias) {
        return new TriggerRoutineUsage(DSL.name(alias), this);
    }

    @Override
    public TriggerRoutineUsage as(Name alias) {
        return new TriggerRoutineUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TriggerRoutineUsage rename(String name) {
        return new TriggerRoutineUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TriggerRoutineUsage rename(Name name) {
        return new TriggerRoutineUsage(name, null);
    }
}
