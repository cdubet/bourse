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
 * one row for each usage privilege on a user defined type
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UdtPrivileges extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.UDT_PRIVILEGES</code>
     */
    public static final UdtPrivileges UDT_PRIVILEGES = new UdtPrivileges();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.UDT_PRIVILEGES.GRANTOR</code>.
     */
    public final TableField<Record, String> GRANTOR = createField(DSL.name("GRANTOR"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.UDT_PRIVILEGES.GRANTEE</code>.
     */
    public final TableField<Record, String> GRANTEE = createField(DSL.name("GRANTEE"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.UDT_PRIVILEGES.UDT_CATALOG</code>.
     */
    public final TableField<Record, String> UDT_CATALOG = createField(DSL.name("UDT_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.UDT_PRIVILEGES.UDT_SCHEMA</code>.
     */
    public final TableField<Record, String> UDT_SCHEMA = createField(DSL.name("UDT_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.UDT_PRIVILEGES.UDT_NAME</code>.
     */
    public final TableField<Record, String> UDT_NAME = createField(DSL.name("UDT_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.UDT_PRIVILEGES.PRIVILEGE_TYPE</code>.
     */
    public final TableField<Record, String> PRIVILEGE_TYPE = createField(DSL.name("PRIVILEGE_TYPE"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.UDT_PRIVILEGES.IS_GRANTABLE</code>.
     */
    public final TableField<Record, String> IS_GRANTABLE = createField(DSL.name("IS_GRANTABLE"), net.tuxanna.database.jooq.information_schema.Domains.YES_OR_NO.getDataType(), this, "");

    private UdtPrivileges(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private UdtPrivileges(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each usage privilege on a user defined type"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.UDT_PRIVILEGES</code> table
     * reference
     */
    public UdtPrivileges(String alias) {
        this(DSL.name(alias), UDT_PRIVILEGES);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.UDT_PRIVILEGES</code> table
     * reference
     */
    public UdtPrivileges(Name alias) {
        this(alias, UDT_PRIVILEGES);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.UDT_PRIVILEGES</code> table reference
     */
    public UdtPrivileges() {
        this(DSL.name("UDT_PRIVILEGES"), null);
    }

    public <O extends Record> UdtPrivileges(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, UDT_PRIVILEGES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public UdtPrivileges as(String alias) {
        return new UdtPrivileges(DSL.name(alias), this);
    }

    @Override
    public UdtPrivileges as(Name alias) {
        return new UdtPrivileges(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UdtPrivileges rename(String name) {
        return new UdtPrivileges(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UdtPrivileges rename(Name name) {
        return new UdtPrivileges(name, null);
    }
}
