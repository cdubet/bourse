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
 * the visible columns of the primary key of each accessible table defined
 * within this database
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemPrimarykeys extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.SYSTEM_PRIMARYKEYS</code>
     */
    public static final SystemPrimarykeys SYSTEM_PRIMARYKEYS = new SystemPrimarykeys();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_PRIMARYKEYS.TABLE_CAT</code>.
     * catalog in which table containing primary key is defined
     */
    public final TableField<Record, String> TABLE_CAT = createField(DSL.name("TABLE_CAT"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "catalog in which table containing primary key is defined");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_PRIMARYKEYS.TABLE_SCHEM</code>. schema in
     * which table containing primary key is defined
     */
    public final TableField<Record, String> TABLE_SCHEM = createField(DSL.name("TABLE_SCHEM"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "schema in which table containing primary key is defined");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_PRIMARYKEYS.TABLE_NAME</code>.
     * simple name of table containing primary key
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "simple name of table containing primary key");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_PRIMARYKEYS.COLUMN_NAME</code>. simple
     * name of column participating in primary key
     */
    public final TableField<Record, String> COLUMN_NAME = createField(DSL.name("COLUMN_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "simple name of column participating in primary key");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_PRIMARYKEYS.KEY_SEQ</code>.
     * sequence number of column within primary key
     */
    public final TableField<Record, Short> KEY_SEQ = createField(DSL.name("KEY_SEQ"), SQLDataType.SMALLINT, this, "sequence number of column within primary key");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_PRIMARYKEYS.PK_NAME</code>.
     * primary key name
     */
    public final TableField<Record, String> PK_NAME = createField(DSL.name("PK_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "primary key name");

    private SystemPrimarykeys(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemPrimarykeys(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("the visible columns of the primary key of each accessible table defined within this database"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_PRIMARYKEYS</code>
     * table reference
     */
    public SystemPrimarykeys(String alias) {
        this(DSL.name(alias), SYSTEM_PRIMARYKEYS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_PRIMARYKEYS</code>
     * table reference
     */
    public SystemPrimarykeys(Name alias) {
        this(alias, SYSTEM_PRIMARYKEYS);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_PRIMARYKEYS</code> table
     * reference
     */
    public SystemPrimarykeys() {
        this(DSL.name("SYSTEM_PRIMARYKEYS"), null);
    }

    public <O extends Record> SystemPrimarykeys(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_PRIMARYKEYS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemPrimarykeys as(String alias) {
        return new SystemPrimarykeys(DSL.name(alias), this);
    }

    @Override
    public SystemPrimarykeys as(Name alias) {
        return new SystemPrimarykeys(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemPrimarykeys rename(String name) {
        return new SystemPrimarykeys(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemPrimarykeys rename(Name name) {
        return new SystemPrimarykeys(name, null);
    }
}
