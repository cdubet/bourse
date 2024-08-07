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
 * one row for each usage privilege granted on a user defined type
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DataTypePrivileges extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.DATA_TYPE_PRIVILEGES</code>
     */
    public static final DataTypePrivileges DATA_TYPE_PRIVILEGES = new DataTypePrivileges();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.DATA_TYPE_PRIVILEGES.OBJECT_CATALOG</code>.
     */
    public final TableField<Record, String> OBJECT_CATALOG = createField(DSL.name("OBJECT_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.DATA_TYPE_PRIVILEGES.OBJECT_SCHEMA</code>.
     */
    public final TableField<Record, String> OBJECT_SCHEMA = createField(DSL.name("OBJECT_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.DATA_TYPE_PRIVILEGES.OBJECT_NAME</code>.
     */
    public final TableField<Record, String> OBJECT_NAME = createField(DSL.name("OBJECT_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.DATA_TYPE_PRIVILEGES.OBJECT_TYPE</code>.
     */
    public final TableField<Record, String> OBJECT_TYPE = createField(DSL.name("OBJECT_TYPE"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.DATA_TYPE_PRIVILEGES.DTD_IDENTIFIER</code>.
     */
    public final TableField<Record, String> DTD_IDENTIFIER = createField(DSL.name("DTD_IDENTIFIER"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    private DataTypePrivileges(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private DataTypePrivileges(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each usage privilege granted on a user defined type"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.DATA_TYPE_PRIVILEGES</code>
     * table reference
     */
    public DataTypePrivileges(String alias) {
        this(DSL.name(alias), DATA_TYPE_PRIVILEGES);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.DATA_TYPE_PRIVILEGES</code>
     * table reference
     */
    public DataTypePrivileges(Name alias) {
        this(alias, DATA_TYPE_PRIVILEGES);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.DATA_TYPE_PRIVILEGES</code> table
     * reference
     */
    public DataTypePrivileges() {
        this(DSL.name("DATA_TYPE_PRIVILEGES"), null);
    }

    public <O extends Record> DataTypePrivileges(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, DATA_TYPE_PRIVILEGES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public DataTypePrivileges as(String alias) {
        return new DataTypePrivileges(DSL.name(alias), this);
    }

    @Override
    public DataTypePrivileges as(Name alias) {
        return new DataTypePrivileges(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DataTypePrivileges rename(String name) {
        return new DataTypePrivileges(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DataTypePrivileges rename(Name name) {
        return new DataTypePrivileges(name, null);
    }
}
