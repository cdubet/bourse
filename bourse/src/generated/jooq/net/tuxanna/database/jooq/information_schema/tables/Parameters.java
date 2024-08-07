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
 * one row for each routine parameter
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Parameters extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.PARAMETERS</code>
     */
    public static final Parameters PARAMETERS = new Parameters();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.SPECIFIC_CATALOG</code>.
     */
    public final TableField<Record, String> SPECIFIC_CATALOG = createField(DSL.name("SPECIFIC_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.SPECIFIC_SCHEMA</code>.
     */
    public final TableField<Record, String> SPECIFIC_SCHEMA = createField(DSL.name("SPECIFIC_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.SPECIFIC_NAME</code>.
     */
    public final TableField<Record, String> SPECIFIC_NAME = createField(DSL.name("SPECIFIC_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.ORDINAL_POSITION</code>.
     */
    public final TableField<Record, Long> ORDINAL_POSITION = createField(DSL.name("ORDINAL_POSITION"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.PARAMETER_MODE</code>.
     */
    public final TableField<Record, String> PARAMETER_MODE = createField(DSL.name("PARAMETER_MODE"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.IS_RESULT</code>.
     */
    public final TableField<Record, String> IS_RESULT = createField(DSL.name("IS_RESULT"), net.tuxanna.database.jooq.information_schema.Domains.YES_OR_NO.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.AS_LOCATOR</code>.
     */
    public final TableField<Record, String> AS_LOCATOR = createField(DSL.name("AS_LOCATOR"), net.tuxanna.database.jooq.information_schema.Domains.YES_OR_NO.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.PARAMETER_NAME</code>.
     */
    public final TableField<Record, String> PARAMETER_NAME = createField(DSL.name("PARAMETER_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.FROM_SQL_SPECIFIC_CATALOG</code>.
     */
    public final TableField<Record, String> FROM_SQL_SPECIFIC_CATALOG = createField(DSL.name("FROM_SQL_SPECIFIC_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.FROM_SQL_SPECIFIC_SCHEMA</code>.
     */
    public final TableField<Record, String> FROM_SQL_SPECIFIC_SCHEMA = createField(DSL.name("FROM_SQL_SPECIFIC_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.FROM_SQL_SPECIFIC_NAME</code>.
     */
    public final TableField<Record, String> FROM_SQL_SPECIFIC_NAME = createField(DSL.name("FROM_SQL_SPECIFIC_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.TO_SQL_SPECIFIC_CATALOG</code>.
     */
    public final TableField<Record, String> TO_SQL_SPECIFIC_CATALOG = createField(DSL.name("TO_SQL_SPECIFIC_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.TO_SQL_SPECIFIC_SCHEMA</code>.
     */
    public final TableField<Record, String> TO_SQL_SPECIFIC_SCHEMA = createField(DSL.name("TO_SQL_SPECIFIC_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.TO_SQL_SPECIFIC_NAME</code>.
     */
    public final TableField<Record, String> TO_SQL_SPECIFIC_NAME = createField(DSL.name("TO_SQL_SPECIFIC_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.DATA_TYPE</code>.
     */
    public final TableField<Record, String> DATA_TYPE = createField(DSL.name("DATA_TYPE"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.CHARACTER_MAXIMUM_LENGTH</code>.
     */
    public final TableField<Record, Long> CHARACTER_MAXIMUM_LENGTH = createField(DSL.name("CHARACTER_MAXIMUM_LENGTH"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.CHARACTER_OCTET_LENGTH</code>.
     */
    public final TableField<Record, Long> CHARACTER_OCTET_LENGTH = createField(DSL.name("CHARACTER_OCTET_LENGTH"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.CHARACTER_SET_CATALOG</code>.
     */
    public final TableField<Record, String> CHARACTER_SET_CATALOG = createField(DSL.name("CHARACTER_SET_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.CHARACTER_SET_SCHEMA</code>.
     */
    public final TableField<Record, String> CHARACTER_SET_SCHEMA = createField(DSL.name("CHARACTER_SET_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.CHARACTER_SET_NAME</code>.
     */
    public final TableField<Record, String> CHARACTER_SET_NAME = createField(DSL.name("CHARACTER_SET_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.COLLATION_CATALOG</code>.
     */
    public final TableField<Record, String> COLLATION_CATALOG = createField(DSL.name("COLLATION_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.COLLATION_SCHEMA</code>.
     */
    public final TableField<Record, String> COLLATION_SCHEMA = createField(DSL.name("COLLATION_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.COLLATION_NAME</code>.
     */
    public final TableField<Record, String> COLLATION_NAME = createField(DSL.name("COLLATION_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.NUMERIC_PRECISION</code>.
     */
    public final TableField<Record, Long> NUMERIC_PRECISION = createField(DSL.name("NUMERIC_PRECISION"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.NUMERIC_PRECISION_RADIX</code>.
     */
    public final TableField<Record, Long> NUMERIC_PRECISION_RADIX = createField(DSL.name("NUMERIC_PRECISION_RADIX"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.NUMERIC_SCALE</code>.
     */
    public final TableField<Record, Long> NUMERIC_SCALE = createField(DSL.name("NUMERIC_SCALE"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.DATETIME_PRECISION</code>.
     */
    public final TableField<Record, Long> DATETIME_PRECISION = createField(DSL.name("DATETIME_PRECISION"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.INTERVAL_TYPE</code>.
     */
    public final TableField<Record, String> INTERVAL_TYPE = createField(DSL.name("INTERVAL_TYPE"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.INTERVAL_PRECISION</code>.
     */
    public final TableField<Record, Long> INTERVAL_PRECISION = createField(DSL.name("INTERVAL_PRECISION"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.UDT_CATALOG</code>.
     */
    public final TableField<Record, String> UDT_CATALOG = createField(DSL.name("UDT_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.UDT_SCHEMA</code>.
     */
    public final TableField<Record, String> UDT_SCHEMA = createField(DSL.name("UDT_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.UDT_NAME</code>.
     */
    public final TableField<Record, String> UDT_NAME = createField(DSL.name("UDT_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.SCOPE_CATALOG</code>.
     */
    public final TableField<Record, String> SCOPE_CATALOG = createField(DSL.name("SCOPE_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.SCOPE_SCHEMA</code>.
     */
    public final TableField<Record, String> SCOPE_SCHEMA = createField(DSL.name("SCOPE_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.SCOPE_NAME</code>.
     */
    public final TableField<Record, String> SCOPE_NAME = createField(DSL.name("SCOPE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.MAXIMUM_CARDINALITY</code>.
     */
    public final TableField<Record, Long> MAXIMUM_CARDINALITY = createField(DSL.name("MAXIMUM_CARDINALITY"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.DTD_IDENTIFIER</code>.
     */
    public final TableField<Record, String> DTD_IDENTIFIER = createField(DSL.name("DTD_IDENTIFIER"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.PARAMETERS.DECLARED_DATA_TYPE</code>.
     */
    public final TableField<Record, String> DECLARED_DATA_TYPE = createField(DSL.name("DECLARED_DATA_TYPE"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.DECLARED_NUMERIC_PRECISION</code>.
     */
    public final TableField<Record, Long> DECLARED_NUMERIC_PRECISION = createField(DSL.name("DECLARED_NUMERIC_PRECISION"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.PARAMETERS.DECLARED_NUMERIC_SCALE</code>.
     */
    public final TableField<Record, Long> DECLARED_NUMERIC_SCALE = createField(DSL.name("DECLARED_NUMERIC_SCALE"), net.tuxanna.database.jooq.information_schema.Domains.CARDINAL_NUMBER.getDataType(), this, "");

    private Parameters(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Parameters(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each routine parameter"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.PARAMETERS</code> table
     * reference
     */
    public Parameters(String alias) {
        this(DSL.name(alias), PARAMETERS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.PARAMETERS</code> table
     * reference
     */
    public Parameters(Name alias) {
        this(alias, PARAMETERS);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.PARAMETERS</code> table reference
     */
    public Parameters() {
        this(DSL.name("PARAMETERS"), null);
    }

    public <O extends Record> Parameters(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, PARAMETERS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public Parameters as(String alias) {
        return new Parameters(DSL.name(alias), this);
    }

    @Override
    public Parameters as(Name alias) {
        return new Parameters(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Parameters rename(String name) {
        return new Parameters(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Parameters rename(Name name) {
        return new Parameters(name, null);
    }
}
