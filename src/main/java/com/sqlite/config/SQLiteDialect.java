package com.sqlite.config;

import java.sql.Types;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

public class SQLiteDialect extends Dialect {

	public SQLiteDialect() {
		registerColumnType(Types.NULL, "null");
		registerHibernateType(Types.NULL, "null");
		this.registerColumnType(-7, "integer");
		this.registerColumnType(-6, "tinyint");
		this.registerColumnType(5, "smallint");
		this.registerColumnType(4, "integer");
		this.registerColumnType(-5, "bigint");
		this.registerColumnType(6, "float");
		this.registerColumnType(7, "real");
		this.registerColumnType(8, "double");
		this.registerColumnType(2, "numeric");
		this.registerColumnType(3, "decimal");
		this.registerColumnType(1, "char");
		this.registerColumnType(12, "varchar");
		this.registerColumnType(-1, "longvarchar");
		this.registerColumnType(91, "date");
		this.registerColumnType(92, "time");
		this.registerColumnType(93, "timestamp");
		this.registerColumnType(-2, "blob");
		this.registerColumnType(-3, "blob");
		this.registerColumnType(-4, "blob");
		this.registerColumnType(2004, "blob");
		this.registerColumnType(2005, "clob");
		this.registerColumnType(16, "integer");
		this.registerFunction("concat", (SQLFunction) new VarArgsSQLFunction(
				(Type) StringType.INSTANCE, "", "||", ""));
		this.registerFunction("mod", (SQLFunction) new SQLFunctionTemplate(
				(Type) StringType.INSTANCE, "?1 % ?2"));
		this.registerFunction("substr", (SQLFunction) new StandardSQLFunction(
				"substr", (Type) StringType.INSTANCE));
		this.registerFunction("substring",
				(SQLFunction) new StandardSQLFunction("substr",
						(Type) StringType.INSTANCE));
	}

	public boolean supportsIdentityColumns() {
		return true;
	}

	public boolean hasDataTypeInIdentityColumn() {
		return false;
	}

	public String getIdentityColumnString() {
		return "integer";
	}

	public String getIdentitySelectString() {
		return "select last_insert_rowid()";
	}

	public boolean supportsLimit() {
		return false;
	}

	protected String getLimitString(String query, boolean hasOffset) {
		return new StringBuffer(query.length() + 20).append(query)
				.append(hasOffset ? " limit ? offset ?" : " limit ?")
				.toString();
	}

	public boolean supportsTemporaryTables() {
		return true;
	}

	public String getCreateTemporaryTableString() {
		return "create temporary table if not exists";
	}

	public boolean dropTemporaryTableAfterUse() {
		return false;
	}

	public boolean supportsCurrentTimestampSelection() {
		return true;
	}

	public boolean isCurrentTimestampSelectStringCallable() {
		return false;
	}

	public String getCurrentTimestampSelectString() {
		return "select current_timestamp";
	}

	public boolean supportsUnionAll() {
		return true;
	}

	public boolean hasAlterTable() {
		return false;
	}

	public boolean dropConstraints() {
		return false;
	}

	public String getAddColumnString() {
		return "add column";
	}

	public String getForUpdateString() {
		return "";
	}

	public boolean supportsOuterJoinForUpdate() {
		return false;
	}

	public String getDropForeignKeyString() {
		throw new UnsupportedOperationException(
				"No drop foreign key syntax supported by SQLiteDialect");
	}

	public String getAddForeignKeyConstraintString(String constraintName,
			String[] foreignKey, String referencedTable, String[] primaryKey,
			boolean referencesPrimaryKey) {
		throw new UnsupportedOperationException(
				"No add foreign key syntax supported by SQLiteDialect");
	}

	public String getAddPrimaryKeyConstraintString(String constraintName) {
		throw new UnsupportedOperationException(
				"No add primary key syntax supported by SQLiteDialect");
	}

	public boolean supportsIfExistsBeforeTableName() {
		return true;
	}

	public boolean supportsCascadeDelete() {
		return false;
	}
}
