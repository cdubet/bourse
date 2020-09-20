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
 * one row for each column of a table that is explicitly or implicitly referenced 
 * in the &lt;query expression&gt; of the view being described
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ViewColumnUsage extends TableImpl<Record> {

    private static final long serialVersionUID = -1145493127;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.VIEW_COLUMN_USAGE</code>
     */
    public static final ViewColumnUsage VIEW_COLUMN_USAGE = new ViewColumnUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_COLUMN_USAGE.VIEW_CATALOG</code>.
     */
    public final TableField<Record, String> VIEW_CATALOG = createField(DSL.name("VIEW_CATALOG"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_COLUMN_USAGE.VIEW_SCHEMA</code>.
     */
    public final TableField<Record, String> VIEW_SCHEMA = createField(DSL.name("VIEW_SCHEMA"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_COLUMN_USAGE.VIEW_NAME</code>.
     */
    public final TableField<Record, String> VIEW_NAME = createField(DSL.name("VIEW_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_COLUMN_USAGE.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_COLUMN_USAGE.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_COLUMN_USAGE.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.VIEW_COLUMN_USAGE.COLUMN_NAME</code>.
     */
    public final TableField<Record, String> COLUMN_NAME = createField(DSL.name("COLUMN_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * Create a <code>INFORMATION_SCHEMA.VIEW_COLUMN_USAGE</code> table reference
     */
    public ViewColumnUsage() {
        this(DSL.name("VIEW_COLUMN_USAGE"), null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.VIEW_COLUMN_USAGE</code> table reference
     */
    public ViewColumnUsage(String alias) {
        this(DSL.name(alias), VIEW_COLUMN_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.VIEW_COLUMN_USAGE</code> table reference
     */
    public ViewColumnUsage(Name alias) {
        this(alias, VIEW_COLUMN_USAGE);
    }

    private ViewColumnUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private ViewColumnUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each column of a table that is explicitly or implicitly referenced in the <query expression> of the view being described"), TableOptions.table());
    }

    public <O extends Record> ViewColumnUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, VIEW_COLUMN_USAGE);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public ViewColumnUsage as(String alias) {
        return new ViewColumnUsage(DSL.name(alias), this);
    }

    @Override
    public ViewColumnUsage as(Name alias) {
        return new ViewColumnUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewColumnUsage rename(String name) {
        return new ViewColumnUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ViewColumnUsage rename(Name name) {
        return new ViewColumnUsage(name, null);
    }
}
