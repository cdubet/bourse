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
 * one row for each usage grant on a routine to a role
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoleRoutineGrants extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS</code>
     */
    public static final RoleRoutineGrants ROLE_ROUTINE_GRANTS = new RoleRoutineGrants();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS.GRANTOR</code>.
     */
    public final TableField<Record, String> GRANTOR = createField(DSL.name("GRANTOR"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS.GRANTEE</code>.
     */
    public final TableField<Record, String> GRANTEE = createField(DSL.name("GRANTEE"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS.SPECIFIC_CATALOG</code>.
     */
    public final TableField<Record, String> SPECIFIC_CATALOG = createField(DSL.name("SPECIFIC_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS.SPECIFIC_SCHEMA</code>.
     */
    public final TableField<Record, String> SPECIFIC_SCHEMA = createField(DSL.name("SPECIFIC_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS.SPECIFIC_NAME</code>.
     */
    public final TableField<Record, String> SPECIFIC_NAME = createField(DSL.name("SPECIFIC_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS.ROUTINE_CATALOG</code>.
     */
    public final TableField<Record, String> ROUTINE_CATALOG = createField(DSL.name("ROUTINE_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS.ROUTINE_SCHEMA</code>.
     */
    public final TableField<Record, String> ROUTINE_SCHEMA = createField(DSL.name("ROUTINE_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS.ROUTINE_NAME</code>.
     */
    public final TableField<Record, String> ROUTINE_NAME = createField(DSL.name("ROUTINE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS.PRIVILEGE_TYPE</code>.
     */
    public final TableField<Record, String> PRIVILEGE_TYPE = createField(DSL.name("PRIVILEGE_TYPE"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS.IS_GRANTABLE</code>.
     */
    public final TableField<Record, String> IS_GRANTABLE = createField(DSL.name("IS_GRANTABLE"), net.tuxanna.database.jooq.information_schema.Domains.YES_OR_NO.getDataType(), this, "");

    private RoleRoutineGrants(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private RoleRoutineGrants(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each usage grant on a routine to a role"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS</code>
     * table reference
     */
    public RoleRoutineGrants(String alias) {
        this(DSL.name(alias), ROLE_ROUTINE_GRANTS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS</code>
     * table reference
     */
    public RoleRoutineGrants(Name alias) {
        this(alias, ROLE_ROUTINE_GRANTS);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.ROLE_ROUTINE_GRANTS</code> table
     * reference
     */
    public RoleRoutineGrants() {
        this(DSL.name("ROLE_ROUTINE_GRANTS"), null);
    }

    public <O extends Record> RoleRoutineGrants(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, ROLE_ROUTINE_GRANTS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public RoleRoutineGrants as(String alias) {
        return new RoleRoutineGrants(DSL.name(alias), this);
    }

    @Override
    public RoleRoutineGrants as(Name alias) {
        return new RoleRoutineGrants(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RoleRoutineGrants rename(String name) {
        return new RoleRoutineGrants(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RoleRoutineGrants rename(Name name) {
        return new RoleRoutineGrants(name, null);
    }
}
