<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/pub/constants.inc" %>
<%@ include file="/WEB-INF/jsp/pub/tags.inc" %>
<style>
td{
  padding-left: 10px;
}
</style>
<input type="hidden" id="${pkName}" name="${pkName}" value="${map[pkName]}" />
<div>
	<table style="border: none;width: 100%;">
		<#list fields as item>
		<tr>
			<td>${item}<span style="color: red">*</span></td>
			<td><input type="text" id="${item}" name="${item}" value="${map[item]}" readonly="readonly" style="width: 90%;"></td>
		</tr>
		</#list>
	</table>
</div>
