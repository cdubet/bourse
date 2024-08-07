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
 * one row for each usage of a domain as the type of a column
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ColumnDomainUsage extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.COLUMN_DOMAIN_USAGE</code>
     */
    public static final ColumnDomainUsage COLUMN_DOMAIN_USAGE = new ColumnDomainUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.COLUMN_DOMAIN_USAGE.DOMAIN_CATALOG</code>.
     */
    public final TableField<Record, String> DOMAIN_CATALOG = createField(DSL.name("DOMAIN_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.COLUMN_DOMAIN_USAGE.DOMAIN_SCHEMA</code>.
     */
    public final TableField<Record, String> DOMAIN_SCHEMA = createField(DSL.name("DOMAIN_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.COLUMN_DOMAIN_USAGE.DOMAIN_NAME</code>.
     */
    public final TableField<Record, String> DOMAIN_NAME = createField(DSL.name("DOMAIN_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.COLUMN_DOMAIN_USAGE.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.COLUMN_DOMAIN_USAGE.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.COLUMN_DOMAIN_USAGE.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.COLUMN_DOMAIN_USAGE.COLUMN_NAME</code>.
     */
    public final TableField<Record, String> COLUMN_NAME = createField(DSL.name("COLUMN_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    private ColumnDomainUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private ColumnDomainUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each usage of a domain as the type of a column"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.COLUMN_DOMAIN_USAGE</code>
     * table reference
     */
    public ColumnDomainUsage(String alias) {
        this(DSL.name(alias), COLUMN_DOMAIN_USAGE);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.COLUMN_DOMAIN_USAGE</code>
     * table reference
     */
    public ColumnDomainUsage(Name alias) {
        this(alias, COLUMN_DOMAIN_USAGE);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.COLUMN_DOMAIN_USAGE</code> table
     * reference
     */
    public ColumnDomainUsage() {
        this(DSL.name("COLUMN_DOMAIN_USAGE"), null);
    }

    public <O extends Record> ColumnDomainUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, COLUMN_DOMAIN_USAGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public ColumnDomainUsage as(String alias) {
        return new ColumnDomainUsage(DSL.name(alias), this);
    }

    @Override
    public ColumnDomainUsage as(Name alias) {
        return new ColumnDomainUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ColumnDomainUsage rename(String name) {
        return new ColumnDomainUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ColumnDomainUsage rename(Name name) {
        return new ColumnDomainUsage(name, null);
    }
}
