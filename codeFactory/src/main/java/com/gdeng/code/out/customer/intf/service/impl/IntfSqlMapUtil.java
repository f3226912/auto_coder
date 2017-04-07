package com.gdeng.code.out.customer.intf.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.gdeng.code.common.Constant;

class IntfSqlMapUtil implements Constant {
	private static final Logger log = Logger.getLogger(IntfSqlMapUtil.class);

	public static String genInsertSql(List<String> clumns, List<String> fileds, String entityTableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ").append(entityTableName).append("(").append("\n\t");
		;
		for (int i = 0, size = clumns.size(); i < size; i++) {
			String clumn = clumns.get(i);
			if (i < size - 1) {
				sql.append(clumn).append(",");
			} else {
				sql.append(clumn);
			}
			if (i > 0 && i % 6 == 0) {
				sql.append("\n\t");
			}
		}
		sql.append("\n\t");
		sql.append(") VALUES (");
		sql.append("\n\t");
		for (int i = 0, size = fileds.size(); i < size; i++) {
			String filed = fileds.get(i);
			if (i < size - 1) {
				sql.append(":").append(filed).append(",");
			} else {
				sql.append(":").append(filed);
			}
			if (i > 0 && i % 6 == 0) {
				sql.append("\n\t");
			}
		}
		sql.append("\n\t");
		sql.append(")");
		return sql.toString();
	}

	public static String genUpdateSql(List<String> clumns, List<String> fileds, String entityTableName, String pkName, String pkClumn) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE ").append(entityTableName).append(" SET ").append("\n\t");
		for (int i = 0, size = clumns.size(); i < size; i++) {
			String clumn = clumns.get(i);
			String field = fileds.get(i);
			StringBuilder ifSql = new StringBuilder();
			ifSql.append("<#if %s?exists && %s!=\"\" >\n\t");
			ifSql.append("\t%s=:%s,\n\t");
			ifSql.append("</#if>\t");
			sql.append(String.format(ifSql.toString(), field, field, clumn, field));
			sql.append("\n\t");
		}
		sql.append("\tupdateTime = SYSDATE()\n\t");
		sql.append("WHERE ").append(pkClumn).append("=:").append(pkName);
		return sql.toString();
	}

	public static String genGetByIdSql(List<String> clumns, List<String> fileds, String entityTableName, String pkName, String pkClumn) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT \n\t");
		for (int i = 0, size = clumns.size(); i < size; i++) {
			String clumn = clumns.get(i);
			String field = fileds.get(i);
			if (clumn.equalsIgnoreCase(field)) {
				sql.append(clumn);
			} else {
				sql.append(clumn).append(" AS ").append(field);
			}
			if (i < size - 1) {
				sql.append(",");
			}
			if (i > 0 && i % 6 == 0) {
				sql.append("\n\t");
			}
		}
		sql.append("\n\tFROM ").append(entityTableName).append(" WHERE ").append(pkClumn).append("=:").append(pkName);
		return sql.toString();
	}

	public static String genGetListSql(List<String> clumns, List<String> fileds, String entityTableName, String pkName, String pkClumn) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT \n\t");
		for (int i = 0, size = clumns.size(); i < size; i++) {
			String clumn = clumns.get(i);
			String field = fileds.get(i);
			if (clumn.equalsIgnoreCase(field)) {
				sql.append(clumn);
			} else {
				sql.append(clumn).append(" AS ").append(field);
			}
			if (i < size - 1) {
				sql.append(",");
			}
			if (i > 0 && i % 6 == 0) {
				sql.append("\n\t");
			}
		}
		sql.append("\n\tFROM ").append(entityTableName).append(" WHERE 1=1");
		for (int i = 0, size = clumns.size(); i < size; i++) {
			String clumn = clumns.get(i);
			String field = fileds.get(i);
			StringBuilder ifSql = new StringBuilder();
			ifSql.append("\n\t<#if %s?exists && %s!=\"\" >\n");
			if (i < size - 1) {
				ifSql.append("\t\tAND %s=:%s\n");
			} else {
				ifSql.append("\t\tAND %s=:%s\n");
			}
			ifSql.append("\t</#if>");
			sql.append(String.format(ifSql.toString(), field, field, clumn, field));
		}
		return sql.toString();
	}

	public static String genGetTotalSql(List<String> clumns, List<String> fileds, String entityTableName, String pkName, String pkClumn) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(*) FROM ").append(entityTableName).append(" WHERE 1=1");
		for (int i = 0, size = clumns.size(); i < size; i++) {
			String clumn = clumns.get(i);
			String field = fileds.get(i);
			StringBuilder ifSql = new StringBuilder();
			ifSql.append("\n\t<#if %s?exists && %s!=\"\" >\n");
			if (i < size - 1) {
				ifSql.append("\t\tAND %s=:%s\n");
			} else {
				ifSql.append("\t\tAND %s=:%s\n");
			}
			ifSql.append("\t</#if>");
			sql.append(String.format(ifSql.toString(), field, field, clumn, field));
		}
		return sql.toString();
	}

}