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
 * one row for each privilege on a column granted to a role
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoleColumnGrants extends TableImpl<Record> {

    private static final long serialVersionUID = -1407375804;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS</code>
     */
    public static final RoleColumnGrants ROLE_COLUMN_GRANTS = new RoleColumnGrants();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS.GRANTOR</code>.
     */
    public final TableField<Record, String> GRANTOR = createField(DSL.name("GRANTOR"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS.GRANTEE</code>.
     */
    public final TableField<Record, String> GRANTEE = createField(DSL.name("GRANTEE"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS.COLUMN_NAME</code>.
     */
    public final TableField<Record, String> COLUMN_NAME = createField(DSL.name("COLUMN_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS.PRIVILEGE_TYPE</code>.
     */
    public final TableField<Record, String> PRIVILEGE_TYPE = createField(DSL.name("PRIVILEGE_TYPE"), org.jooq.impl.SQLDataType.VARCHAR(65536), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS.IS_GRANTABLE</code>.
     */
    public final TableField<Record, String> IS_GRANTABLE = createField(DSL.name("IS_GRANTABLE"), org.jooq.impl.SQLDataType.VARCHAR(3), this, "");

    /**
     * Create a <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS</code> table reference
     */
    public RoleColumnGrants() {
        this(DSL.name("ROLE_COLUMN_GRANTS"), null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS</code> table reference
     */
    public RoleColumnGrants(String alias) {
        this(DSL.name(alias), ROLE_COLUMN_GRANTS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.ROLE_COLUMN_GRANTS</code> table reference
     */
    public RoleColumnGrants(Name alias) {
        this(alias, ROLE_COLUMN_GRANTS);
    }

    private RoleColumnGrants(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private RoleColumnGrants(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each privilege on a column granted to a role"), TableOptions.table());
    }

    public <O extends Record> RoleColumnGrants(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, ROLE_COLUMN_GRANTS);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public RoleColumnGrants as(String alias) {
        return new RoleColumnGrants(DSL.name(alias), this);
    }

    @Override
    public RoleColumnGrants as(Name alias) {
        return new RoleColumnGrants(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RoleColumnGrants rename(String name) {
        return new RoleColumnGrants(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RoleColumnGrants rename(Name name) {
        return new RoleColumnGrants(name, null);
    }
}
