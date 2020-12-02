/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.information_schema.tables;


import java.time.LocalDateTime;

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
 * the visible sessions open in this database
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemSessions extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS</code>
     */
    public static final SystemSessions SYSTEM_SESSIONS = new SystemSessions();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.SESSION_ID</code>. session identifier
     */
    public final TableField<Record, Long> SESSION_ID = createField(DSL.name("SESSION_ID"), SQLDataType.BIGINT, this, "session identifier");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.CONNECTED</code>. time at which session connected to database
     */
    public final TableField<Record, LocalDateTime> CONNECTED = createField(DSL.name("CONNECTED"), SQLDataType.LOCALDATETIME(6), this, "time at which session connected to database");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.USER_NAME</code>. name of session user, as known to the database
     */
    public final TableField<Record, String> USER_NAME = createField(DSL.name("USER_NAME"), SQLDataType.VARCHAR(128), this, "name of session user, as known to the database");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.IS_ADMIN</code>. is session user an admin user?
     */
    public final TableField<Record, Boolean> IS_ADMIN = createField(DSL.name("IS_ADMIN"), SQLDataType.BOOLEAN, this, "is session user an admin user?");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.AUTOCOMMIT</code>. is session in autocommit mode?
     */
    public final TableField<Record, Boolean> AUTOCOMMIT = createField(DSL.name("AUTOCOMMIT"), SQLDataType.BOOLEAN, this, "is session in autocommit mode?");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.READONLY</code>. is session in read-only mode?
     */
    public final TableField<Record, Boolean> READONLY = createField(DSL.name("READONLY"), SQLDataType.BOOLEAN, this, "is session in read-only mode?");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.LAST_IDENTITY</code>. what is the last identity value used by this session?
     */
    public final TableField<Record, Long> LAST_IDENTITY = createField(DSL.name("LAST_IDENTITY"), SQLDataType.BIGINT, this, "what is the last identity value used by this session?");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.SCHEMA</code>. this session's current default schema
     */
    public final TableField<Record, String> SCHEMA = createField(DSL.name("SCHEMA"), SQLDataType.VARCHAR(128), this, "this session's current default schema");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.TRANSACTION</code>.
     */
    public final TableField<Record, Boolean> TRANSACTION = createField(DSL.name("TRANSACTION"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.TRANSACTION_SIZE</code>. how many undo items are there in this session's transaction buffer?
     */
    public final TableField<Record, Long> TRANSACTION_SIZE = createField(DSL.name("TRANSACTION_SIZE"), SQLDataType.BIGINT, this, "how many undo items are there in this session's transaction buffer?");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.WAITING_FOR_THIS</code>.
     */
    public final TableField<Record, String> WAITING_FOR_THIS = createField(DSL.name("WAITING_FOR_THIS"), SQLDataType.VARCHAR(65536), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.THIS_WAITING_FOR</code>.
     */
    public final TableField<Record, String> THIS_WAITING_FOR = createField(DSL.name("THIS_WAITING_FOR"), SQLDataType.VARCHAR(65536), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.CURRENT_STATEMENT</code>.
     */
    public final TableField<Record, String> CURRENT_STATEMENT = createField(DSL.name("CURRENT_STATEMENT"), SQLDataType.VARCHAR(65536), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS.LATCH_COUNT</code>.
     */
    public final TableField<Record, Long> LATCH_COUNT = createField(DSL.name("LATCH_COUNT"), SQLDataType.BIGINT, this, "");

    private SystemSessions(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemSessions(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("the visible sessions open in this database"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS</code> table reference
     */
    public SystemSessions(String alias) {
        this(DSL.name(alias), SYSTEM_SESSIONS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS</code> table reference
     */
    public SystemSessions(Name alias) {
        this(alias, SYSTEM_SESSIONS);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_SESSIONS</code> table reference
     */
    public SystemSessions() {
        this(DSL.name("SYSTEM_SESSIONS"), null);
    }

    public <O extends Record> SystemSessions(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_SESSIONS);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemSessions as(String alias) {
        return new SystemSessions(DSL.name(alias), this);
    }

    @Override
    public SystemSessions as(Name alias) {
        return new SystemSessions(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemSessions rename(String name) {
        return new SystemSessions(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemSessions rename(Name name) {
        return new SystemSessions(name, null);
    }
}
