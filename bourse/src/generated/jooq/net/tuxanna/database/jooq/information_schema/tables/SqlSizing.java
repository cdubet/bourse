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
 * list of size limits for names and database objects
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SqlSizing extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.SQL_SIZING</code>
     */
    public static final SqlSizing SQL_SIZING = new SqlSizing();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_SIZING.SIZING_ID</code>.
     */
    public final TableField<Record, Long> SIZING_ID = createField(DSL.name("SIZING_ID"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_SIZING.SIZING_NAME</code>.
     */
    public final TableField<Record, String> SIZING_NAME = createField(DSL.name("SIZING_NAME"), SQLDataType.VARCHAR(65536), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_SIZING.SUPPORTED_VALUE</code>.
     */
    public final TableField<Record, Long> SUPPORTED_VALUE = createField(DSL.name("SUPPORTED_VALUE"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_SIZING.COMMENTS</code>.
     */
    public final TableField<Record, String> COMMENTS = createField(DSL.name("COMMENTS"), SQLDataType.VARCHAR(65536), this, "");

    private SqlSizing(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SqlSizing(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("list of size limits for names and database objects"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SQL_SIZING</code> table
     * reference
     */
    public SqlSizing(String alias) {
        this(DSL.name(alias), SQL_SIZING);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SQL_SIZING</code> table
     * reference
     */
    public SqlSizing(Name alias) {
        this(alias, SQL_SIZING);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SQL_SIZING</code> table reference
     */
    public SqlSizing() {
        this(DSL.name("SQL_SIZING"), null);
    }

    public <O extends Record> SqlSizing(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SQL_SIZING);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SqlSizing as(String alias) {
        return new SqlSizing(DSL.name(alias), this);
    }

    @Override
    public SqlSizing as(Name alias) {
        return new SqlSizing(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SqlSizing rename(String name) {
        return new SqlSizing(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SqlSizing rename(Name name) {
        return new SqlSizing(name, null);
    }
}
