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
 * list of implementation specific limits
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SqlImplementationInfo extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.SQL_IMPLEMENTATION_INFO</code>
     */
    public static final SqlImplementationInfo SQL_IMPLEMENTATION_INFO = new SqlImplementationInfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SQL_IMPLEMENTATION_INFO.IMPLEMENTATION_INFO_ID</code>.
     */
    public final TableField<Record, Long> IMPLEMENTATION_INFO_ID = createField(DSL.name("IMPLEMENTATION_INFO_ID"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SQL_IMPLEMENTATION_INFO.IMPLEMENTATION_INFO_NAME</code>.
     */
    public final TableField<Record, String> IMPLEMENTATION_INFO_NAME = createField(DSL.name("IMPLEMENTATION_INFO_NAME"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SQL_IMPLEMENTATION_INFO.INTEGER_VALUE</code>.
     */
    public final TableField<Record, Long> INTEGER_VALUE = createField(DSL.name("INTEGER_VALUE"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SQL_IMPLEMENTATION_INFO.CHARACTER_VALUE</code>.
     */
    public final TableField<Record, String> CHARACTER_VALUE = createField(DSL.name("CHARACTER_VALUE"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SQL_IMPLEMENTATION_INFO.COMMENTS</code>.
     */
    public final TableField<Record, String> COMMENTS = createField(DSL.name("COMMENTS"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    private SqlImplementationInfo(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SqlImplementationInfo(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("list of implementation specific limits"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SQL_IMPLEMENTATION_INFO</code>
     * table reference
     */
    public SqlImplementationInfo(String alias) {
        this(DSL.name(alias), SQL_IMPLEMENTATION_INFO);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SQL_IMPLEMENTATION_INFO</code>
     * table reference
     */
    public SqlImplementationInfo(Name alias) {
        this(alias, SQL_IMPLEMENTATION_INFO);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SQL_IMPLEMENTATION_INFO</code> table
     * reference
     */
    public SqlImplementationInfo() {
        this(DSL.name("SQL_IMPLEMENTATION_INFO"), null);
    }

    public <O extends Record> SqlImplementationInfo(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SQL_IMPLEMENTATION_INFO);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SqlImplementationInfo as(String alias) {
        return new SqlImplementationInfo(DSL.name(alias), this);
    }

    @Override
    public SqlImplementationInfo as(Name alias) {
        return new SqlImplementationInfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SqlImplementationInfo rename(String name) {
        return new SqlImplementationInfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SqlImplementationInfo rename(Name name) {
        return new SqlImplementationInfo(name, null);
    }
}
