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
 * list of supported SQL Standard packages
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SqlPackages extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.SQL_PACKAGES</code>
     */
    public static final SqlPackages SQL_PACKAGES = new SqlPackages();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_PACKAGES.ID</code>.
     */
    public final TableField<Record, String> ID = createField(DSL.name("ID"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_PACKAGES.NAME</code>.
     */
    public final TableField<Record, String> NAME = createField(DSL.name("NAME"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_PACKAGES.IS_SUPPORTED</code>.
     */
    public final TableField<Record, String> IS_SUPPORTED = createField(DSL.name("IS_SUPPORTED"), net.tuxanna.database.jooq.information_schema.Domains.YES_OR_NO.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_PACKAGES.IS_VERIFIED_BY</code>.
     */
    public final TableField<Record, String> IS_VERIFIED_BY = createField(DSL.name("IS_VERIFIED_BY"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_PACKAGES.COMMENTS</code>.
     */
    public final TableField<Record, String> COMMENTS = createField(DSL.name("COMMENTS"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    private SqlPackages(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SqlPackages(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("list of supported SQL Standard packages"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SQL_PACKAGES</code> table
     * reference
     */
    public SqlPackages(String alias) {
        this(DSL.name(alias), SQL_PACKAGES);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SQL_PACKAGES</code> table
     * reference
     */
    public SqlPackages(Name alias) {
        this(alias, SQL_PACKAGES);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SQL_PACKAGES</code> table reference
     */
    public SqlPackages() {
        this(DSL.name("SQL_PACKAGES"), null);
    }

    public <O extends Record> SqlPackages(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SQL_PACKAGES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SqlPackages as(String alias) {
        return new SqlPackages(DSL.name(alias), this);
    }

    @Override
    public SqlPackages as(Name alias) {
        return new SqlPackages(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SqlPackages rename(String name) {
        return new SqlPackages(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SqlPackages rename(Name name) {
        return new SqlPackages(name, null);
    }
}
