<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>汉诺 | 大连</title>
	<%@ include file="/WEB-INF/pages/common/common.jsp"%>
	<script>
		function query(){
			ajaxSubmit({
				url:"/news/industry/listFrag",
				form:"searchForm",
				updateId:"entities"
			});
		}
		function del(vId) {
			ajaxSubmit({
				url:"/news/industry/delete",
				form:"myForm",
				params:{id:vId},
				maskId:"divResult",
				onSuccess:function(data, textStatus) {
					query();
					if("1" == data) {
						$.dialog.tips('删除成功！', 1, 'succ.png');
					} else {
						$.dialog.tips('删除失败，请稍后再试！', 1, 'fail.png');
					}
				},
				onError:function(req) {
					$.dialog.tips('删除失败，请稍后再试！', 2, 'fail.png');
				}
			});
		}

		function dele(vId) {
			if(confirm('确定删除数据？')) {
				del(vId);
			}
		}
	</script>
</head>
<body>
<div id="breadCrumb" style="display:none">
	<a href="#">HN</a>
	>>
	新闻中心
</div>
<div id="container">
	<div position="center" >
		<div id="tabUnit_Set">
			<div id="divSearch" class="mod-unit">
				<div class="mod-unit-hd">
					<h2>查询条件</h2>
					<i class="btn-toggle"></i>
				</div>
				<div class="mod-unit-ct">
					<form id="searchForm" name="searchForm">
						<div class="pdT10 pdR10 pdB10 pdL10">
							<table width="100%">
								<tbody>
								<tr>
									<td class="w100 tr pdR10">标题:</td>
									<td>
										<input type="text" id="title" name="title" value="" class="valBox" />
									</td>
								</tr>
								</tbody>
							</table>
						</div>
					</form>
					<div class="subBar tc pdB10">
						<ul class="clearfix">
							<li class="fl">
								<a href="###" class="btn-a-1" onclick="query()"><span>搜索</span></a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="mod-unit">
				<div class="mod-unit-hd bdT-blue">
					<h2>查询结果</h2>
				</div>
				<div id="divResult" class="mod-unit-ct">
					<div id="entities" class="pdT5 pdR5 pdB5 pdL5">
						<jsp:include page="newsIndustryListFrag.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>