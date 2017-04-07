<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/pub/constants.inc" %>
<%@ include file="/WEB-INF/jsp/pub/tags.inc" %>
<style>
td{
  padding-left: 10px;
}
</style>
<form id="save-form" method="post" action="${entityLowerCaseSimpleName}/save">
	<div>
		<table style="border: none;width: 100%;">
			<#list fields as item>
			<tr>
				<td>${item}<span style="color: red">*</span></td>
				<td><input type="text" id="${item}" name="${item}"  value="" required="true" class="easyui-validatebox" validType="unnormal" missingMessage="${item}必填" style="width: 90%;"></td>
			</tr>
			</#list>
		</table>
	</div>
</form>