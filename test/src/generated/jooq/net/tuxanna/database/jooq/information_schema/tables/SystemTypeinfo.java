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
 * a description of predefined table column data types known to this database
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemTypeinfo extends TableImpl<Record> {

    private static final long serialVersionUID = -1925473927;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO</code>
     */
    public static final SystemTypeinfo SYSTEM_TYPEINFO = new SystemTypeinfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.TYPE_NAME</code>. the HSQLDB-specific data type name; this is the canonical name used in CREATE TABLE and ALTER TABLE statements.
     */
    public final TableField<Record, String> TYPE_NAME = createField(DSL.name("TYPE_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "the HSQLDB-specific data type name; this is the canonical name used in CREATE TABLE and ALTER TABLE statements.");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.DATA_TYPE</code>. SQL data type.  This may be a java.sql.Types data type, a SQL 200n data type or an HSQLDB-specific data type.  For datetime or interval data types, this column returns the concise data type (such as SQL_­TYPE_­TIME or SQL_­INTERVAL_­YEAR_­TO_­MONTH).
     */
    public final TableField<Record, Short> DATA_TYPE = createField(DSL.name("DATA_TYPE"), org.jooq.impl.SQLDataType.SMALLINT, this, "SQL data type.  This may be a java.sql.Types data type, a SQL 200n data type or an HSQLDB-specific data type.  For datetime or interval data types, this column returns the concise data type (such as SQL_­TYPE_­TIME or SQL_­INTERVAL_­YEAR_­TO_­MONTH).");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.PRECISION</code>. The maximum column size for this data type.  For numeric data, this is the maximum precision.  For string data, this is the length in characters.  For datetime data types, this is the length in characters of the string representation (assuming the maximum allowed precision of the fractional seconds component).  NULL for data types where column size is not applicable.  For interval data types, this is the number of characters in the character representation of the interval literal (as defined by the interval leading precision).
     */
    public final TableField<Record, Integer> PRECISION = createField(DSL.name("PRECISION"), org.jooq.impl.SQLDataType.INTEGER, this, "The maximum column size for this data type.  For numeric data, this is the maximum precision.  For string data, this is the length in characters.  For datetime data types, this is the length in characters of the string representation (assuming the maximum allowed precision of the fractional seconds component).  NULL for data types where column size is not applicable.  For interval data types, this is the number of characters in the character representation of the interval literal (as defined by the interval leading precision).");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.LITERAL_PREFIX</code>. the character or characters used to prefix a literal of this type; for example, a single quotation mark (') for character data types; NULL for data types where a literal prefix is not applicable
     */
    public final TableField<Record, String> LITERAL_PREFIX = createField(DSL.name("LITERAL_PREFIX"), org.jooq.impl.SQLDataType.VARCHAR(65536), this, "the character or characters used to prefix a literal of this type; for example, a single quotation mark (') for character data types; NULL for data types where a literal prefix is not applicable");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.LITERAL_SUFFIX</code>. the character or characters used to terminate a literal of this type; for example, a single quotation mark (') for character data types; NULL for data types where a literal suffix is not applicable
     */
    public final TableField<Record, String> LITERAL_SUFFIX = createField(DSL.name("LITERAL_SUFFIX"), org.jooq.impl.SQLDataType.VARCHAR(65536), this, "the character or characters used to terminate a literal of this type; for example, a single quotation mark (') for character data types; NULL for data types where a literal suffix is not applicable");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.CREATE_PARAMS</code>. A list of keywords, separated by commas, corresponding to each parameter that may be specified in parentheses when issuing DDL relative to the data type.  The keywords in the list, in the language of the current Locale, may be any of the following:  length, precision, scale.  They appear in the order that the syntax requires that they be used.  For example, CREATE_PARAMS for DECIMAL with an English locale would be "precision,scale"; CREATE_PARAMS for VARCHAR would be "length".  The value is NULL if there are no parameters for the data type definition; for example, INTEGER.
     */
    public final TableField<Record, String> CREATE_PARAMS = createField(DSL.name("CREATE_PARAMS"), org.jooq.impl.SQLDataType.VARCHAR(65536), this, "A list of keywords, separated by commas, corresponding to each parameter that may be specified in parentheses when issuing DDL relative to the data type.  The keywords in the list, in the language of the current Locale, may be any of the following:  length, precision, scale.  They appear in the order that the syntax requires that they be used.  For example, CREATE_PARAMS for DECIMAL with an English locale would be \"precision,scale\"; CREATE_PARAMS for VARCHAR would be \"length\".  The value is NULL if there are no parameters for the data type definition; for example, INTEGER.");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.NULLABLE</code>. NULL values allowed for this type?: { No Nulls | Nullable | Unknown }
     */
    public final TableField<Record, Short> NULLABLE = createField(DSL.name("NULLABLE"), org.jooq.impl.SQLDataType.SMALLINT, this, "NULL values allowed for this type?: { No Nulls | Nullable | Unknown }");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.CASE_SENSITIVE</code>. TRUE if the type is case-sensitive in collations and comparisons; FALSE otherwise
     */
    public final TableField<Record, Boolean> CASE_SENSITIVE = createField(DSL.name("CASE_SENSITIVE"), org.jooq.impl.SQLDataType.BOOLEAN, this, "TRUE if the type is case-sensitive in collations and comparisons; FALSE otherwise");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.SEARCHABLE</code>. use of WHERE?: { None | Char (Only WHERE .. LIKE) | Basic (Except WHERE .. LIKE) | Searchable (All forms of WHERE...) }
     */
    public final TableField<Record, Integer> SEARCHABLE = createField(DSL.name("SEARCHABLE"), org.jooq.impl.SQLDataType.INTEGER, this, "use of WHERE?: { None | Char (Only WHERE .. LIKE) | Basic (Except WHERE .. LIKE) | Searchable (All forms of WHERE...) }");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.UNSIGNED_ATTRIBUTE</code>. TRUE if the data type is unsigned; NULL the attribute is not applicable to the data type or the data type is not numeric.
     */
    public final TableField<Record, Boolean> UNSIGNED_ATTRIBUTE = createField(DSL.name("UNSIGNED_ATTRIBUTE"), org.jooq.impl.SQLDataType.BOOLEAN, this, "TRUE if the data type is unsigned; NULL the attribute is not applicable to the data type or the data type is not numeric.");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.FIXED_PREC_SCALE</code>. TRUE if the data type has predefined fixed precision and scale , like a money data type.  NULL for non-numeric types.
     */
    public final TableField<Record, Boolean> FIXED_PREC_SCALE = createField(DSL.name("FIXED_PREC_SCALE"), org.jooq.impl.SQLDataType.BOOLEAN, this, "TRUE if the data type has predefined fixed precision and scale , like a money data type.  NULL for non-numeric types.");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.AUTO_INCREMENT</code>. NULL if the attribute is not applicable to the data type or the data type is not numeric.  If TRUE, this indicates that when an insert is made, a unique value is inserted into the column at insert time. The increment is not defined. An application should not assume that auto-increment values start at any particular point or increment by any particular value.
     */
    public final TableField<Record, Boolean> AUTO_INCREMENT = createField(DSL.name("AUTO_INCREMENT"), org.jooq.impl.SQLDataType.BOOLEAN, this, "NULL if the attribute is not applicable to the data type or the data type is not numeric.  If TRUE, this indicates that when an insert is made, a unique value is inserted into the column at insert time. The increment is not defined. An application should not assume that auto-increment values start at any particular point or increment by any particular value.");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.LOCAL_TYPE_NAME</code>. Localized version of the name of the data type; NULL if a localized name is not supported.  This name is intended for display only, such as in dialog boxes
     */
    public final TableField<Record, String> LOCAL_TYPE_NAME = createField(DSL.name("LOCAL_TYPE_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "Localized version of the name of the data type; NULL if a localized name is not supported.  This name is intended for display only, such as in dialog boxes");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.MINIMUM_SCALE</code>. minimum scale supported
     */
    public final TableField<Record, Short> MINIMUM_SCALE = createField(DSL.name("MINIMUM_SCALE"), org.jooq.impl.SQLDataType.SMALLINT, this, "minimum scale supported");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.MAXIMUM_SCALE</code>. maximum scale supported
     */
    public final TableField<Record, Short> MAXIMUM_SCALE = createField(DSL.name("MAXIMUM_SCALE"), org.jooq.impl.SQLDataType.SMALLINT, this, "maximum scale supported");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.SQL_DATA_TYPE</code>. The value of the SQL data type as it would appear in the SQL CLI SQL_DESC_TYPE field of the SQLDA.
     */
    public final TableField<Record, Integer> SQL_DATA_TYPE = createField(DSL.name("SQL_DATA_TYPE"), org.jooq.impl.SQLDataType.INTEGER, this, "The value of the SQL data type as it would appear in the SQL CLI SQL_DESC_TYPE field of the SQLDA.");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.SQL_DATETIME_SUB</code>. When the value of SQL_DATA_TYPE is SQL_DATETIME or SQL_INTERVAL, this column contains the datetime/interval subcode.  For data types other than datetime and interval, this column is NULL.
     */
    public final TableField<Record, Integer> SQL_DATETIME_SUB = createField(DSL.name("SQL_DATETIME_SUB"), org.jooq.impl.SQLDataType.INTEGER, this, "When the value of SQL_DATA_TYPE is SQL_DATETIME or SQL_INTERVAL, this column contains the datetime/interval subcode.  For data types other than datetime and interval, this column is NULL.");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.NUM_PREC_RADIX</code>. For numeric types, this column contains the value 10 to indicate that COLUMN_SIZE specifies a number of decimal digits.  Otherwise, this column is NULL.
     */
    public final TableField<Record, Integer> NUM_PREC_RADIX = createField(DSL.name("NUM_PREC_RADIX"), org.jooq.impl.SQLDataType.INTEGER, this, "For numeric types, this column contains the value 10 to indicate that COLUMN_SIZE specifies a number of decimal digits.  Otherwise, this column is NULL.");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO.INTERVAL_PRECISION</code>. if the data type is an interval data type, then this column contains the value of the interval leading precision.  Otherwise, this column is NULL.
     */
    public final TableField<Record, Integer> INTERVAL_PRECISION = createField(DSL.name("INTERVAL_PRECISION"), org.jooq.impl.SQLDataType.INTEGER, this, "if the data type is an interval data type, then this column contains the value of the interval leading precision.  Otherwise, this column is NULL.");

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO</code> table reference
     */
    public SystemTypeinfo() {
        this(DSL.name("SYSTEM_TYPEINFO"), null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO</code> table reference
     */
    public SystemTypeinfo(String alias) {
        this(DSL.name(alias), SYSTEM_TYPEINFO);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_TYPEINFO</code> table reference
     */
    public SystemTypeinfo(Name alias) {
        this(alias, SYSTEM_TYPEINFO);
    }

    private SystemTypeinfo(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemTypeinfo(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("a description of predefined table column data types known to this database"), TableOptions.table());
    }

    public <O extends Record> SystemTypeinfo(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_TYPEINFO);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemTypeinfo as(String alias) {
        return new SystemTypeinfo(DSL.name(alias), this);
    }

    @Override
    public SystemTypeinfo as(Name alias) {
        return new SystemTypeinfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemTypeinfo rename(String name) {
        return new SystemTypeinfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemTypeinfo rename(Name name) {
        return new SystemTypeinfo(name, null);
    }
}