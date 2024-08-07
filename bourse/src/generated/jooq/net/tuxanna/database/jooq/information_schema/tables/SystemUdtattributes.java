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
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemUdtattributes extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES</code>
     */
    public static final SystemUdtattributes SYSTEM_UDTATTRIBUTES = new SystemUdtattributes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.TYPE_CAT</code>.
     */
    public final TableField<Record, String> TYPE_CAT = createField(DSL.name("TYPE_CAT"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.TYPE_SCHEM</code>.
     */
    public final TableField<Record, String> TYPE_SCHEM = createField(DSL.name("TYPE_SCHEM"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.TYPE_NAME</code>.
     */
    public final TableField<Record, String> TYPE_NAME = createField(DSL.name("TYPE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.ATTR_NAME</code>.
     */
    public final TableField<Record, String> ATTR_NAME = createField(DSL.name("ATTR_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.DATA_TYPE</code>.
     */
    public final TableField<Record, Short> DATA_TYPE = createField(DSL.name("DATA_TYPE"), SQLDataType.SMALLINT, this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.ATTR_TYPE_NAME</code>.
     */
    public final TableField<Record, String> ATTR_TYPE_NAME = createField(DSL.name("ATTR_TYPE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.ATTR_SIZE</code>.
     */
    public final TableField<Record, Integer> ATTR_SIZE = createField(DSL.name("ATTR_SIZE"), SQLDataType.INTEGER, this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.DECIMAL_DIGITS</code>.
     */
    public final TableField<Record, Integer> DECIMAL_DIGITS = createField(DSL.name("DECIMAL_DIGITS"), SQLDataType.INTEGER, this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.NUM_PREC_RADIX</code>.
     */
    public final TableField<Record, Integer> NUM_PREC_RADIX = createField(DSL.name("NUM_PREC_RADIX"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.NULLABLE</code>.
     */
    public final TableField<Record, Integer> NULLABLE = createField(DSL.name("NULLABLE"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.REMARKS</code>.
     */
    public final TableField<Record, String> REMARKS = createField(DSL.name("REMARKS"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.ATTR_DEF</code>.
     */
    public final TableField<Record, String> ATTR_DEF = createField(DSL.name("ATTR_DEF"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.SQL_DATA_TYPE</code>.
     */
    public final TableField<Record, Integer> SQL_DATA_TYPE = createField(DSL.name("SQL_DATA_TYPE"), SQLDataType.INTEGER, this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.SQL_DATETIME_SUB</code>.
     */
    public final TableField<Record, Integer> SQL_DATETIME_SUB = createField(DSL.name("SQL_DATETIME_SUB"), SQLDataType.INTEGER, this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.CHAR_OCTET_LENGTH</code>.
     */
    public final TableField<Record, Integer> CHAR_OCTET_LENGTH = createField(DSL.name("CHAR_OCTET_LENGTH"), SQLDataType.INTEGER, this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.ORDINAL_POSITION</code>.
     */
    public final TableField<Record, Integer> ORDINAL_POSITION = createField(DSL.name("ORDINAL_POSITION"), SQLDataType.INTEGER, this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.IS_NULLABLE</code>.
     */
    public final TableField<Record, String> IS_NULLABLE = createField(DSL.name("IS_NULLABLE"), net.tuxanna.database.jooq.information_schema.Domains.YES_OR_NO.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.SCOPE_CATALOG</code>.
     */
    public final TableField<Record, String> SCOPE_CATALOG = createField(DSL.name("SCOPE_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.SCOPE_SCHEMA</code>.
     */
    public final TableField<Record, String> SCOPE_SCHEMA = createField(DSL.name("SCOPE_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.SCOPE_TABLE</code>.
     */
    public final TableField<Record, String> SCOPE_TABLE = createField(DSL.name("SCOPE_TABLE"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES.SOURCE_DATA_TYPE</code>.
     */
    public final TableField<Record, Short> SOURCE_DATA_TYPE = createField(DSL.name("SOURCE_DATA_TYPE"), SQLDataType.SMALLINT, this, "");

    private SystemUdtattributes(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemUdtattributes(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES</code>
     * table reference
     */
    public SystemUdtattributes(String alias) {
        this(DSL.name(alias), SYSTEM_UDTATTRIBUTES);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES</code>
     * table reference
     */
    public SystemUdtattributes(Name alias) {
        this(alias, SYSTEM_UDTATTRIBUTES);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_UDTATTRIBUTES</code> table
     * reference
     */
    public SystemUdtattributes() {
        this(DSL.name("SYSTEM_UDTATTRIBUTES"), null);
    }

    public <O extends Record> SystemUdtattributes(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_UDTATTRIBUTES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemUdtattributes as(String alias) {
        return new SystemUdtattributes(DSL.name(alias), this);
    }

    @Override
    public SystemUdtattributes as(Name alias) {
        return new SystemUdtattributes(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemUdtattributes rename(String name) {
        return new SystemUdtattributes(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemUdtattributes rename(Name name) {
        return new SystemUdtattributes(name, null);
    }
}
