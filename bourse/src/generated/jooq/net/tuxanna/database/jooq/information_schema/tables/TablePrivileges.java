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
 * the visible user level access permissions for each accessible table defined
 * within this database
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TablePrivileges extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES</code>
     */
    public static final TablePrivileges TABLE_PRIVILEGES = new TablePrivileges();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES.GRANTOR</code>.
     */
    public final TableField<Record, String> GRANTOR = createField(DSL.name("GRANTOR"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES.GRANTEE</code>.
     */
    public final TableField<Record, String> GRANTEE = createField(DSL.name("GRANTEE"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES.PRIVILEGE_TYPE</code>.
     */
    public final TableField<Record, String> PRIVILEGE_TYPE = createField(DSL.name("PRIVILEGE_TYPE"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES.IS_GRANTABLE</code>.
     */
    public final TableField<Record, String> IS_GRANTABLE = createField(DSL.name("IS_GRANTABLE"), net.tuxanna.database.jooq.information_schema.Domains.YES_OR_NO.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES.WITH_HIERARCHY</code>.
     */
    public final TableField<Record, String> WITH_HIERARCHY = createField(DSL.name("WITH_HIERARCHY"), net.tuxanna.database.jooq.information_schema.Domains.YES_OR_NO.getDataType(), this, "");

    private TablePrivileges(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private TablePrivileges(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("the visible user level access permissions for each accessible table defined within this database"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES</code> table
     * reference
     */
    public TablePrivileges(String alias) {
        this(DSL.name(alias), TABLE_PRIVILEGES);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES</code> table
     * reference
     */
    public TablePrivileges(Name alias) {
        this(alias, TABLE_PRIVILEGES);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.TABLE_PRIVILEGES</code> table reference
     */
    public TablePrivileges() {
        this(DSL.name("TABLE_PRIVILEGES"), null);
    }

    public <O extends Record> TablePrivileges(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, TABLE_PRIVILEGES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public TablePrivileges as(String alias) {
        return new TablePrivileges(DSL.name(alias), this);
    }

    @Override
    public TablePrivileges as(Name alias) {
        return new TablePrivileges(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TablePrivileges rename(String name) {
        return new TablePrivileges(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TablePrivileges rename(Name name) {
        return new TablePrivileges(name, null);
    }
}
