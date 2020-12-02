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
 * the visible sequences in this database
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemSequences extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES</code>
     */
    public static final SystemSequences SYSTEM_SEQUENCES = new SystemSequences();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.SEQUENCE_CATALOG</code>. sequence catalog name
     */
    public final TableField<Record, String> SEQUENCE_CATALOG = createField(DSL.name("SEQUENCE_CATALOG"), SQLDataType.VARCHAR(128), this, "sequence catalog name");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.SEQUENCE_SCHEMA</code>. sequence schema name
     */
    public final TableField<Record, String> SEQUENCE_SCHEMA = createField(DSL.name("SEQUENCE_SCHEMA"), SQLDataType.VARCHAR(128), this, "sequence schema name");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.SEQUENCE_NAME</code>. sequence identifier
     */
    public final TableField<Record, String> SEQUENCE_NAME = createField(DSL.name("SEQUENCE_NAME"), SQLDataType.VARCHAR(128), this, "sequence identifier");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.DATA_TYPE</code>.
     */
    public final TableField<Record, String> DATA_TYPE = createField(DSL.name("DATA_TYPE"), SQLDataType.VARCHAR(65536), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.NUMERIC_PRECISION</code>.
     */
    public final TableField<Record, Long> NUMERIC_PRECISION = createField(DSL.name("NUMERIC_PRECISION"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.NUMERIC_PRECISION_RADIX</code>.
     */
    public final TableField<Record, Long> NUMERIC_PRECISION_RADIX = createField(DSL.name("NUMERIC_PRECISION_RADIX"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.NUMERIC_SCALE</code>.
     */
    public final TableField<Record, Long> NUMERIC_SCALE = createField(DSL.name("NUMERIC_SCALE"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.MAXIMUM_VALUE</code>. sequence maximum value
     */
    public final TableField<Record, String> MAXIMUM_VALUE = createField(DSL.name("MAXIMUM_VALUE"), SQLDataType.VARCHAR(65536), this, "sequence maximum value");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.MINIMUM_VALUE</code>. sequence minimum value
     */
    public final TableField<Record, String> MINIMUM_VALUE = createField(DSL.name("MINIMUM_VALUE"), SQLDataType.VARCHAR(65536), this, "sequence minimum value");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.INCREMENT</code>. sequence increment
     */
    public final TableField<Record, String> INCREMENT = createField(DSL.name("INCREMENT"), SQLDataType.VARCHAR(65536), this, "sequence increment");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.CYCLE_OPTION</code>. sequence cycle option ('YES' | 'NO')
     */
    public final TableField<Record, String> CYCLE_OPTION = createField(DSL.name("CYCLE_OPTION"), SQLDataType.VARCHAR(3), this, "sequence cycle option ('YES' | 'NO')");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.DECLARED_DATA_TYPE</code>.
     */
    public final TableField<Record, String> DECLARED_DATA_TYPE = createField(DSL.name("DECLARED_DATA_TYPE"), SQLDataType.VARCHAR(65536), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.DECLARED_NUMERIC_PRECISION</code>.
     */
    public final TableField<Record, Long> DECLARED_NUMERIC_PRECISION = createField(DSL.name("DECLARED_NUMERIC_PRECISION"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.DECLARED_NUMERIC_SCALE</code>.
     */
    public final TableField<Record, Long> DECLARED_NUMERIC_SCALE = createField(DSL.name("DECLARED_NUMERIC_SCALE"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.START_WITH</code>. sequence start with value
     */
    public final TableField<Record, String> START_WITH = createField(DSL.name("START_WITH"), SQLDataType.VARCHAR(65536), this, "sequence start with value");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES.NEXT_VALUE</code>.
     */
    public final TableField<Record, String> NEXT_VALUE = createField(DSL.name("NEXT_VALUE"), SQLDataType.VARCHAR(65536), this, "");

    private SystemSequences(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemSequences(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("the visible sequences in this database"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES</code> table reference
     */
    public SystemSequences(String alias) {
        this(DSL.name(alias), SYSTEM_SEQUENCES);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES</code> table reference
     */
    public SystemSequences(Name alias) {
        this(alias, SYSTEM_SEQUENCES);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_SEQUENCES</code> table reference
     */
    public SystemSequences() {
        this(DSL.name("SYSTEM_SEQUENCES"), null);
    }

    public <O extends Record> SystemSequences(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_SEQUENCES);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemSequences as(String alias) {
        return new SystemSequences(DSL.name(alias), this);
    }

    @Override
    public SystemSequences as(Name alias) {
        return new SystemSequences(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemSequences rename(String name) {
        return new SystemSequences(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemSequences rename(Name name) {
        return new SystemSequences(name, null);
    }
}
