<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>汉诺 | 大连</title>
	<%@ include file="/WEB-INF/pages/common/common.jsp"%>
	<script>
		function search(){
			ajaxSubmit({
				url:_basePath + "partner/project/listFrag",
				form:"searchForm",
				updateId:"entities",
				maskId:"divResult"
			});
		}

		function del(vId) {
			ajaxSubmit({
				url:_basePath + "partner/project/delete",
				params:{id:vId},
				maskId:"divResult",
				onSuccess:function(data, textStatus) {
					search();
					if ("1" == data) {
						$.dialog.tips('删除成功！', 1, 'succ.png');
					} else {
						$.dialog.tips("删除失败，请稍后再试！", 2, 'fail.png');
					}
				},
				onError:function(req) {
					$.dialog.tips(req.responseText, 2, 'fail.png');
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
	合作伙伴
	>>
	示范工程
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

								</tbody>
							</table>
						</div>
					</form>
					<div class="subBar tc pdB10">
						<ul class="clearfix">
							<li class="fl">
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
						<jsp:include page="partnerProjectListFrag.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>