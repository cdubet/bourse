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
 * the current state of the system row caching mechanism
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemCacheinfo extends TableImpl<Record> {

    private static final long serialVersionUID = -2081050720;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.SYSTEM_CACHEINFO</code>
     */
    public static final SystemCacheinfo SYSTEM_CACHEINFO = new SystemCacheinfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_CACHEINFO.CACHE_FILE</code>. absolute path of the file underlying the cache object
     */
    public final TableField<Record, String> CACHE_FILE = createField(DSL.name("CACHE_FILE"), org.jooq.impl.SQLDataType.VARCHAR(65536), this, "absolute path of the file underlying the cache object");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_CACHEINFO.MAX_CACHE_COUNT</code>. maximum number of rows that will be buffered in memory by this cache
     */
    public final TableField<Record, Long> MAX_CACHE_COUNT = createField(DSL.name("MAX_CACHE_COUNT"), org.jooq.impl.SQLDataType.BIGINT, this, "maximum number of rows that will be buffered in memory by this cache");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_CACHEINFO.MAX_CACHE_BYTES</code>. approximate maximum size, in bytes, of row data that will be buffered in memory by this cache
     */
    public final TableField<Record, Long> MAX_CACHE_BYTES = createField(DSL.name("MAX_CACHE_BYTES"), org.jooq.impl.SQLDataType.BIGINT, this, "approximate maximum size, in bytes, of row data that will be buffered in memory by this cache");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_CACHEINFO.CACHE_SIZE</code>. number of rows currently cached
     */
    public final TableField<Record, Long> CACHE_SIZE = createField(DSL.name("CACHE_SIZE"), org.jooq.impl.SQLDataType.BIGINT, this, "number of rows currently cached");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_CACHEINFO.CACHE_BYTES</code>. approximate number of row data bytes currently cached
     */
    public final TableField<Record, Long> CACHE_BYTES = createField(DSL.name("CACHE_BYTES"), org.jooq.impl.SQLDataType.BIGINT, this, "approximate number of row data bytes currently cached");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_CACHEINFO.FILE_LOST_BYTES</code>.
     */
    public final TableField<Record, Long> FILE_LOST_BYTES = createField(DSL.name("FILE_LOST_BYTES"), org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_CACHEINFO.FILE_FREE_POS</code>. one greater than largest file position known to be allocated
     */
    public final TableField<Record, Long> FILE_FREE_POS = createField(DSL.name("FILE_FREE_POS"), org.jooq.impl.SQLDataType.BIGINT, this, "one greater than largest file position known to be allocated");

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_CACHEINFO</code> table reference
     */
    public SystemCacheinfo() {
        this(DSL.name("SYSTEM_CACHEINFO"), null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_CACHEINFO</code> table reference
     */
    public SystemCacheinfo(String alias) {
        this(DSL.name(alias), SYSTEM_CACHEINFO);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_CACHEINFO</code> table reference
     */
    public SystemCacheinfo(Name alias) {
        this(alias, SYSTEM_CACHEINFO);
    }

    private SystemCacheinfo(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemCacheinfo(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("the current state of the system row caching mechanism"), TableOptions.table());
    }

    public <O extends Record> SystemCacheinfo(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_CACHEINFO);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemCacheinfo as(String alias) {
        return new SystemCacheinfo(DSL.name(alias), this);
    }

    @Override
    public SystemCacheinfo as(Name alias) {
        return new SystemCacheinfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemCacheinfo rename(String name) {
        return new SystemCacheinfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemCacheinfo rename(Name name) {
        return new SystemCacheinfo(name, null);
    }
}
