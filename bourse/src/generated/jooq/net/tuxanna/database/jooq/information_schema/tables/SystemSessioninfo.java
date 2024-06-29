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
 * information about the current database session
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemSessioninfo extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.SYSTEM_SESSIONINFO</code>
     */
    public static final SystemSessioninfo SYSTEM_SESSIONINFO = new SystemSessioninfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONINFO.KEY</code>. KEY: {
     * SESSION_ID | AUTOCOMMIT | USER | CONNECTION_READONLY | DATABASE_READONLY
     * | MAXROWS | DATABASE | IDENTITY ... }
     */
    public final TableField<Record, String> KEY = createField(DSL.name("KEY"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "KEY: { SESSION_ID | AUTOCOMMIT | USER | CONNECTION_READONLY | DATABASE_READONLY | MAXROWS | DATABASE | IDENTITY ... }");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONINFO.VALUE</code>.
     * VALUE: the value corresponding to the indicated key (see JavaDocs)
     */
    public final TableField<Record, String> VALUE = createField(DSL.name("VALUE"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "VALUE: the value corresponding to the indicated key (see JavaDocs)");

    private SystemSessioninfo(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemSessioninfo(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("information about the current database session"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_SESSIONINFO</code>
     * table reference
     */
    public SystemSessioninfo(String alias) {
        this(DSL.name(alias), SYSTEM_SESSIONINFO);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_SESSIONINFO</code>
     * table reference
     */
    public SystemSessioninfo(Name alias) {
        this(alias, SYSTEM_SESSIONINFO);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_SESSIONINFO</code> table
     * reference
     */
    public SystemSessioninfo() {
        this(DSL.name("SYSTEM_SESSIONINFO"), null);
    }

    public <O extends Record> SystemSessioninfo(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_SESSIONINFO);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemSessioninfo as(String alias) {
        return new SystemSessioninfo(DSL.name(alias), this);
    }

    @Override
    public SystemSessioninfo as(Name alias) {
        return new SystemSessioninfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemSessioninfo rename(String name) {
        return new SystemSessioninfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemSessioninfo rename(Name name) {
        return new SystemSessioninfo(name, null);
    }
}
