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
 * the name of the sequences used by autogenerated columns
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemColumnSequenceUsage extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.SYSTEM_COLUMN_SEQUENCE_USAGE</code>
     */
    public static final SystemColumnSequenceUsage SYSTEM_COLUMN_SEQUENCE_USAGE = new SystemColumnSequenceUsage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_COLUMN_SEQUENCE_USAGE.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_COLUMN_SEQUENCE_USAGE.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_COLUMN_SEQUENCE_USAGE.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_COLUMN_SEQUENCE_USAGE.COLUMN_NAME</code>.
     */
    public final TableField<Record, String> COLUMN_NAME = createField(DSL.name("COLUMN_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_COLUMN_SEQUENCE_USAGE.SEQUENCE_CATALOG</code>.
     */
    public final TableField<Record, String> SEQUENCE_CATALOG = createField(DSL.name("SEQUENCE_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_COLUMN_SEQUENCE_USAGE.SEQUENCE_SCHEMA</code>.
     */
    public final TableField<Record, String> SEQUENCE_SCHEMA = createField(DSL.name("SEQUENCE_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_COLUMN_SEQUENCE_USAGE.SEQUENCE_NAME</code>.
     */
    public final TableField<Record, String> SEQUENCE_NAME = createField(DSL.name("SEQUENCE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    private SystemColumnSequenceUsage(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemColumnSequenceUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("the name of the sequences used by autogenerated columns"), TableOptions.table());
    }

    /**
     * Create an aliased
     * <code>INFORMATION_SCHEMA.SYSTEM_COLUMN_SEQUENCE_USAGE</code> table
     * reference
     */
    public SystemColumnSequenceUsage(String alias) {
        this(DSL.name(alias), SYSTEM_COLUMN_SEQUENCE_USAGE);
    }

    /**
     * Create an aliased
     * <code>INFORMATION_SCHEMA.SYSTEM_COLUMN_SEQUENCE_USAGE</code> table
     * reference
     */
    public SystemColumnSequenceUsage(Name alias) {
        this(alias, SYSTEM_COLUMN_SEQUENCE_USAGE);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_COLUMN_SEQUENCE_USAGE</code>
     * table reference
     */
    public SystemColumnSequenceUsage() {
        this(DSL.name("SYSTEM_COLUMN_SEQUENCE_USAGE"), null);
    }

    public <O extends Record> SystemColumnSequenceUsage(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_COLUMN_SEQUENCE_USAGE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemColumnSequenceUsage as(String alias) {
        return new SystemColumnSequenceUsage(DSL.name(alias), this);
    }

    @Override
    public SystemColumnSequenceUsage as(Name alias) {
        return new SystemColumnSequenceUsage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemColumnSequenceUsage rename(String name) {
        return new SystemColumnSequenceUsage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemColumnSequenceUsage rename(Name name) {
        return new SystemColumnSequenceUsage(name, null);
    }
}
