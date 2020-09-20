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
public class Quotes extends TableImpl<Record> {

    private static final long serialVersionUID = -208778895;

    /**
     * The reference instance of <code>PUBLIC.QUOTES</code>
     */
    public static final Quotes QUOTES = new Quotes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>PUBLIC.QUOTES.IDQUOTES</code>.
     */
    public final TableField<Record, Integer> IDQUOTES = createField(DSL.name("IDQUOTES"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.QUOTES.IDSHARE</code>.
     */
    public final TableField<Record, Integer> IDSHARE = createField(DSL.name("IDSHARE"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.DATEQUOTE</code>.
     */
    public final TableField<Record, LocalDateTime> DATEQUOTE = createField(DSL.name("DATEQUOTE"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.QUOTES.LASTTRADEDPRICE</code>.
     */
    public final TableField<Record, Double> LASTTRADEDPRICE = createField(DSL.name("LASTTRADEDPRICE"), org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.QUOTES.CHANGEINPRICE</code>.
     */
    public final TableField<Record, Double> CHANGEINPRICE = createField(DSL.name("CHANGEINPRICE"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.OPENPRICE</code>.
     */
    public final TableField<Record, Double> OPENPRICE = createField(DSL.name("OPENPRICE"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.HIGHPRICE</code>.
     */
    public final TableField<Record, Double> HIGHPRICE = createField(DSL.name("HIGHPRICE"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.LOWPRICE</code>.
     */
    public final TableField<Record, Double> LOWPRICE = createField(DSL.name("LOWPRICE"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.VOLUME</code>.
     */
    public final TableField<Record, Double> VOLUME = createField(DSL.name("VOLUME"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.LOW52WEEK</code>.
     */
    public final TableField<Record, Double> LOW52WEEK = createField(DSL.name("LOW52WEEK"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.HIGH52WEEK</code>.
     */
    public final TableField<Record, Double> HIGH52WEEK = createField(DSL.name("HIGH52WEEK"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.MOBILEAVERAGE50DAYS</code>.
     */
    public final TableField<Record, Double> MOBILEAVERAGE50DAYS = createField(DSL.name("MOBILEAVERAGE50DAYS"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.MOBILEAVERAGE200DAYS</code>.
     */
    public final TableField<Record, Double> MOBILEAVERAGE200DAYS = createField(DSL.name("MOBILEAVERAGE200DAYS"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.PREVIOUSCLOSE</code>.
     */
    public final TableField<Record, Double> PREVIOUSCLOSE = createField(DSL.name("PREVIOUSCLOSE"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.PERATIO</code>.
     */
    public final TableField<Record, Double> PERATIO = createField(DSL.name("PERATIO"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.QUOTES.SHORTRATIO</code>.
     */
    public final TableField<Record, Double> SHORTRATIO = createField(DSL.name("SHORTRATIO"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * Create a <code>PUBLIC.QUOTES</code> table reference
     */
    public Quotes() {
        this(DSL.name("QUOTES"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.QUOTES</code> table reference
     */
    public Quotes(String alias) {
        this(DSL.name(alias), QUOTES);
    }

    /**
     * Create an aliased <code>PUBLIC.QUOTES</code> table reference
     */
    public Quotes(Name alias) {
        this(alias, QUOTES);
    }

    private Quotes(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Quotes(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Quotes(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, QUOTES);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<Record, Integer> getIdentity() {
        return Keys.IDENTITY_QUOTES;
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.SYS_PK_10104;
    }

    @Override
    public List<UniqueKey<Record>> getKeys() {
        return Arrays.<UniqueKey<Record>>asList(Keys.SYS_PK_10104);
    }

    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.<ForeignKey<Record, ?>>asList(Keys.SYS_FK_10107);
    }

    public Shares shares() {
        return new Shares(this, Keys.SYS_FK_10107);
    }

    @Override
    public Quotes as(String alias) {
        return new Quotes(DSL.name(alias), this);
    }

    @Override
    public Quotes as(Name alias) {
        return new Quotes(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Quotes rename(String name) {
        return new Quotes(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Quotes rename(Name name) {
        return new Quotes(name, null);
    }
}
