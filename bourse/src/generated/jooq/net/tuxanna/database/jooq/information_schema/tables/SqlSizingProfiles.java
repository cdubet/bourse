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
 * list of supported SQL Standard packages
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SqlSizingProfiles extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.SQL_SIZING_PROFILES</code>
     */
    public static final SqlSizingProfiles SQL_SIZING_PROFILES = new SqlSizingProfiles();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_SIZING_PROFILES.SIZING_ID</code>.
     */
    public final TableField<Record, Long> SIZING_ID = createField(DSL.name("SIZING_ID"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_SIZING_PROFILES.SIZING_NAME</code>.
     */
    public final TableField<Record, String> SIZING_NAME = createField(DSL.name("SIZING_NAME"), SQLDataType.VARCHAR(65536), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_SIZING_PROFILES.PROFILE_ID</code>.
     */
    public final TableField<Record, Long> PROFILE_ID = createField(DSL.name("PROFILE_ID"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_SIZING_PROFILES.PROFILE_NAME</code>.
     */
    public final TableField<Record, String> PROFILE_NAME = createField(DSL.name("PROFILE_NAME"), SQLDataType.VARCHAR(65536), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_SIZING_PROFILES.REQUIRED_VALUE</code>.
     */
    public final TableField<Record, Long> REQUIRED_VALUE = createField(DSL.name("REQUIRED_VALUE"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SQL_SIZING_PROFILES.COMMENTS</code>.
     */
    public final TableField<Record, String> COMMENTS = createField(DSL.name("COMMENTS"), SQLDataType.VARCHAR(65536), this, "");

    private SqlSizingProfiles(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SqlSizingProfiles(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("list of supported SQL Standard packages"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SQL_SIZING_PROFILES</code> table reference
     */
    public SqlSizingProfiles(String alias) {
        this(DSL.name(alias), SQL_SIZING_PROFILES);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SQL_SIZING_PROFILES</code> table reference
     */
    public SqlSizingProfiles(Name alias) {
        this(alias, SQL_SIZING_PROFILES);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SQL_SIZING_PROFILES</code> table reference
     */
    public SqlSizingProfiles() {
        this(DSL.name("SQL_SIZING_PROFILES"), null);
    }

    public <O extends Record> SqlSizingProfiles(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SQL_SIZING_PROFILES);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SqlSizingProfiles as(String alias) {
        return new SqlSizingProfiles(DSL.name(alias), this);
    }

    @Override
    public SqlSizingProfiles as(Name alias) {
        return new SqlSizingProfiles(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SqlSizingProfiles rename(String name) {
        return new SqlSizingProfiles(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SqlSizingProfiles rename(Name name) {
        return new SqlSizingProfiles(name, null);
    }
}
