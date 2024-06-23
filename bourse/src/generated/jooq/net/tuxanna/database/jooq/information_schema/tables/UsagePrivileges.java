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
 * one row for each usage privilege granted on character set or domain
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UsagePrivileges extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES</code>
     */
    public static final UsagePrivileges USAGE_PRIVILEGES = new UsagePrivileges();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES.GRANTOR</code>.
     */
    public final TableField<Record, String> GRANTOR = createField(DSL.name("GRANTOR"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES.GRANTEE</code>.
     */
    public final TableField<Record, String> GRANTEE = createField(DSL.name("GRANTEE"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES.OBJECT_CATALOG</code>.
     */
    public final TableField<Record, String> OBJECT_CATALOG = createField(DSL.name("OBJECT_CATALOG"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES.OBJECT_SCHEMA</code>.
     */
    public final TableField<Record, String> OBJECT_SCHEMA = createField(DSL.name("OBJECT_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES.OBJECT_NAME</code>.
     */
    public final TableField<Record, String> OBJECT_NAME = createField(DSL.name("OBJECT_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES.OBJECT_TYPE</code>.
     */
    public final TableField<Record, String> OBJECT_TYPE = createField(DSL.name("OBJECT_TYPE"), SQLDataType.VARCHAR(65536), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES.PRIVILEGE_TYPE</code>.
     */
    public final TableField<Record, String> PRIVILEGE_TYPE = createField(DSL.name("PRIVILEGE_TYPE"), SQLDataType.VARCHAR(65536), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES.IS_GRANTABLE</code>.
     */
    public final TableField<Record, String> IS_GRANTABLE = createField(DSL.name("IS_GRANTABLE"), SQLDataType.VARCHAR(3), this, "");

    private UsagePrivileges(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private UsagePrivileges(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each usage privilege granted on character set or domain"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES</code> table
     * reference
     */
    public UsagePrivileges(String alias) {
        this(DSL.name(alias), USAGE_PRIVILEGES);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES</code> table
     * reference
     */
    public UsagePrivileges(Name alias) {
        this(alias, USAGE_PRIVILEGES);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.USAGE_PRIVILEGES</code> table reference
     */
    public UsagePrivileges() {
        this(DSL.name("USAGE_PRIVILEGES"), null);
    }

    public <O extends Record> UsagePrivileges(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, USAGE_PRIVILEGES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public UsagePrivileges as(String alias) {
        return new UsagePrivileges(DSL.name(alias), this);
    }

    @Override
    public UsagePrivileges as(Name alias) {
        return new UsagePrivileges(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UsagePrivileges rename(String name) {
        return new UsagePrivileges(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UsagePrivileges rename(Name name) {
        return new UsagePrivileges(name, null);
    }
}
