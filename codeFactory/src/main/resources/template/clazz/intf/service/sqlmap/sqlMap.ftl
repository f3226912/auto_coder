<?xml version="1.0" encoding="UTF-8" ?>
<sqlMap namespace="${entityName}">
<sql id="insert">
	<![CDATA[
	${insertSql}
	]]>
</sql>

<sql id="update">
	<![CDATA[
	${updateSql}
	]]>
</sql>

<sql id="getById">
	<![CDATA[
	${getByIdSql}
	]]>
</sql>

<sql id="getList">
	<![CDATA[
	${getListSql}
	]]>
</sql>

<sql id="getListPage">
	<![CDATA[
	${getListSql}
	LIMIT :startRow, :endRow
	]]>
</sql>

<sql id="getTotal">
	<![CDATA[
	${getTotalSql}
	]]>
</sql>

<sql id="deleteById">
	<![CDATA[
	DELETE ${entityTableName} WHERE ${pkClumn}=:${pkName}
	]]>
</sql>
</sqlMap>
