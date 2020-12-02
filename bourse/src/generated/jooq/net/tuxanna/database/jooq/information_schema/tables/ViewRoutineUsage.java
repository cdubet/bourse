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
 * one row for each SQL-invoked routine identified as the subject routine 
 * of either a &lt;routine invocation&gt;, a &lt;method reference&gt;, a &lt;method 
 * invocation&gt;, or a &lt;static method invocation&gt; contained in a &lt;view 
 * definition&gt;
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewRoutineUsage extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.VIEW_ROUTINE_USAGE</code>
     */
    public static final ViewRoutineUsage VIEW_ROUTINE_USAGE = new ViewRoutineUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_ROUTINE_USAGE.VIEW_CATALOG</code>.
     */
    public final TableField<Record, String> VIEW_CATALOG = createField(DSL.name("VIEW_CATALOG"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_ROUTINE_USAGE.VIEW_SCHEMA</code>.
     */
    public final TableField<Record, String> VIEW_SCHEMA = createField(DSL.name("VIEW_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_ROUTINE_USAGE.VIEW_NAME</code>.
     */
    public final TableField<Record, String> VIEW_NAME = createField(DSL.name("VIEW_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_ROUTINE_USAGE.SPECIFIC_CATALOG</code>.
     */
    public final TableField<Record, String> SPECIFIC_CATALOG = createField(DSL.name("SPECIFIC_CATALOG"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_ROUTINE_USAGE.SPECIFIC_SCHEMA</code>.
     */
    public final TableField<Record, String> SPECIFIC_SCHEMA = createField(DSL.name("SPECIFIC_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_ROUTINE_USAGE.SPECIFIC_NAME</code>.
     */
    public final TableField<Record, String> SPECIFIC_NAME = createField(DSL.name("SPECIFIC_NAME"), SQLDataType.VARCHAR(128), this, "");

    private ViewRoutineUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private ViewRoutineUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each SQL-invoked routine identified as the subject routine of either a <routine invocation>, a <method reference>, a <method invocation>, or a <static method invocation> contained in a <view definition>"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.VIEW_ROUTINE_USAGE</code> table reference
     */
    public ViewRoutineUsage(String alias) {
        this(DSL.name(alias), VIEW_ROUTINE_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.VIEW_ROUTINE_USAGE</code> table reference
     */
    public ViewRoutineUsage(Name alias) {
        this(alias, VIEW_ROUTINE_USAGE);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.VIEW_ROUTINE_USAGE</code> table reference
     */
    public ViewRoutineUsage() {
        this(DSL.name("VIEW_ROUTINE_USAGE"), null);
    }

    public <O extends Record> ViewRoutineUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, VIEW_ROUTINE_USAGE);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public ViewRoutineUsage as(String alias) {
        return new ViewRoutineUsage(DSL.name(alias), this);
    }

    @Override
    public ViewRoutineUsage as(Name alias) {
        return new ViewRoutineUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewRoutineUsage rename(String name) {
        return new ViewRoutineUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewRoutineUsage rename(Name name) {
        return new ViewRoutineUsage(name, null);
    }
}
