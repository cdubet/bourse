/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.public_.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import net.tuxanna.database.jooq.public_.Keys;
import net.tuxanna.database.jooq.public_.Public;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sell extends TableImpl<Record> {

    private static final long serialVersionUID = 1098357969;

    /**
     * The reference instance of <code>PUBLIC.SELL</code>
     */
    public static final Sell SELL = new Sell();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>PUBLIC.SELL.IDSELL</code>.
     */
    public final TableField<Record, Integer> IDSELL = createField(DSL.name("IDSELL"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.SELL.IDSHARE</code>.
     */
    public final TableField<Record, Integer> IDSHARE = createField(DSL.name("IDSHARE"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.SELL.IDACCOUNT</code>.
     */
    public final TableField<Record, Integer> IDACCOUNT = createField(DSL.name("IDACCOUNT"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.SELL.QTE</code>.
     */
    public final TableField<Record, Double> QTE = createField(DSL.name("QTE"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.SELL.UNITPRICEREQUESTED</code>.
     */
    public final TableField<Record, Double> UNITPRICEREQUESTED = createField(DSL.name("UNITPRICEREQUESTED"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.SELL.UNITPRICESOLD</code>.
     */
    public final TableField<Record, Double> UNITPRICESOLD = createField(DSL.name("UNITPRICESOLD"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.SELL.STATE</code>.
     */
    public final TableField<Record, Integer> STATE = createField(DSL.name("STATE"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.SELL.DATEEXPIRATION</code>.
     */
    public final TableField<Record, LocalDateTime> DATEEXPIRATION = createField(DSL.name("DATEEXPIRATION"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.SELL</code> table reference
     */
    public Sell() {
        this(DSL.name("SELL"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.SELL</code> table reference
     */
    public Sell(String alias) {
        this(DSL.name(alias), SELL);
    }

    /**
     * Create an aliased <code>PUBLIC.SELL</code> table reference
     */
    public Sell(Name alias) {
        this(alias, SELL);
    }

    private Sell(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Sell(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Sell(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SELL);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<Record, Integer> getIdentity() {
        return Keys.IDENTITY_SELL;
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.SYS_PK_10133;
    }

    @Override
    public List<UniqueKey<Record>> getKeys() {
        return Arrays.<UniqueKey<Record>>asList(Keys.SYS_PK_10133);
    }

    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.<ForeignKey<Record, ?>>asList(Keys.SYS_FK_10138, Keys.SYS_FK_10139);
    }

    public Shares shares() {
        return new Shares(this, Keys.SYS_FK_10138);
    }

    public Account account() {
        return new Account(this, Keys.SYS_FK_10139);
    }

    @Override
    public Sell as(String alias) {
        return new Sell(DSL.name(alias), this);
    }

    @Override
    public Sell as(Name alias) {
        return new Sell(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Sell rename(String name) {
        return new Sell(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Sell rename(Name name) {
        return new Sell(name, null);
    }
}
