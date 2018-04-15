package wang.crick.business.haro.core.base.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 分页标签
 */
public class PageTag extends TagSupport {

    private static final long serialVersionUID = 280053973475466854L;
    private int totalCount = 0;
    private int totalPages = -1;
    private int pageSize;
    private int pageNo = 1;
    private String action;
    private String form;
    private String params;
    private String maskId;
    private String update;
    private String onBefore;
    private String onAfter;
    private String onSuccess;
    private String onError;

    public final void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public final void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    private int getTotalPages() {
        if (this.totalPages == -1) {
            if (this.totalCount <= 0) {
                this.totalPages = 1;
            } else {
                this.totalPages = (this.totalCount / this.pageSize);
                if (this.totalCount % this.pageSize > 0) {
                    this.totalPages += 1;
                }
            }
        }
        return this.totalPages;
    }

    public final void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public final String getAction() {
        return this.action;
    }

    public final void setAction(String action) {
        this.action = action;
    }

    public final String getForm() {
        return this.form;
    }

    public final void setForm(String form) {
        this.form = form;
    }

    public final String getParams() {
        return this.params;
    }

    public final void setParams(String params) {
        this.params = params;
    }

    public final String getMaskId() {
        return this.maskId;
    }

    public final void setMaskId(String maskId) {
        this.maskId = maskId;
    }

    public final String getUpdate() {
        return this.update;
    }

    public final void setUpdate(String update) {
        this.update = update;
    }

    public final String getOnBefore() {
        return this.onBefore;
    }

    public final void setOnBefore(String onBefore) {
        this.onBefore = onBefore;
    }

    public final String getOnAfter() {
        return this.onAfter;
    }

    public final void setOnAfter(String onAfter) {
        this.onAfter = onAfter;
    }

    public final String getOnSuccess() {
        return this.onSuccess;
    }

    public final void setOnSuccess(String onSuccess) {
        this.onSuccess = onSuccess;
    }

    public final String getOnError() {
        return this.onError;
    }

    public final void setOnError(String onError) {
        this.onError = onError;
    }

    private boolean isHasNext() {
        return this.pageNo + 1 <= getTotalPages();
    }

    private int getNextPage() {
        if (isHasNext()) {
            return this.pageNo + 1;
        }
        return this.pageNo;
    }

    private boolean isHasPre() {
        return this.pageNo - 1 >= 1;
    }

    private int getPrePage() {
        if (isHasPre()) {
            return this.pageNo - 1;
        }
        return this.pageNo;
    }

    private StringBuilder compositePaging(int pageNo, int totalCount) {
        StringBuilder content = new StringBuilder();
        content.append("ajaxSubmit({");
        content.append("url:'").append(this.action).append("',");
        content.append("params:{pageNo:").append(pageNo).append(",totalCount:").append(totalCount).append(",turning:1");
        if (this.params != null) {
            content.append(",");
            content.append(this.params);
        }
        content.append("},");
        if (this.form != null) {
            content.append("form:'").append(this.form).append("',");
        }
        if (this.maskId != null) {
            content.append("maskId:'").append(this.maskId).append("',");
        }
        if (this.update != null) {
            content.append("updateId:'").append(this.update).append("',");
        }
        if (this.onBefore != null) {
            content.append("onBefore:").append(this.onBefore).append(",");
        }
        if (this.onAfter != null) {
            content.append("onAfter:").append(this.onAfter).append(",");
        }
        if (this.onSuccess != null) {
            content.append("onSuccess:").append(this.onSuccess).append(",");
        }
        if (this.onError != null) {
            content.append("onError:").append(this.onError).append(",");
        }
        content.append("t:'").append(1).append("'");
        content.append("})");
        return content;
    }

    public final int doStartTag()
            throws JspException {
        this.totalPages = -1;
        if (this.pageSize == 0) {
            this.pageSize = 10;
        }
        StringBuilder inputContent = new StringBuilder();
        if (this.totalCount > 0) {
            inputContent.append("<div class=\"fr pdR20\">共有<font color='red'>").append(this.totalCount).append("</font>条记录 每页<font color='red'>").append(this.pageSize).append("</font>条记录").append(" 当前<font color='red'>").append(this.pageNo).append("</font>页/总<font color='red'>").append(getTotalPages()).append("</font>页").append("</div>");

            inputContent.append("<ul class=\"pagination pdL20 fl\">");
            if ((isHasNext()) && (isHasPre())) {
                inputContent.append("<li><a href=\"javascript:").append(compositePaging(1, this.totalCount)).append("\" title=\"首页\">&lt;&lt;&nbsp;</a></li>");
                inputContent.append("<li><a href=\"javascript:").append(compositePaging(this.pageNo - 1, this.totalCount)).append("\" title=\"上一页\">&lt;&nbsp;</a></li>");
                int begin = this.pageNo - 5;
                if (begin < 1) {
                    begin = 1;
                }
                int end = this.pageNo + 5;
                if (end > getTotalPages()) {
                    end = getTotalPages();
                }
                for (int i = begin; i <= end; i++) {
                    if (i == this.pageNo) {
                        inputContent.append("<li><span class=\"current\">").append(i).append("</span></li>");
                    } else {
                        inputContent.append("<li><a href=\"javascript:").append(compositePaging(i, this.totalCount)).append("\">").append(i).append("</a></li>");
                    }
                }
                inputContent.append("<li>...</li>");
                inputContent.append("<li><a href=\"javascript:").append(compositePaging(this.pageNo + 1, this.totalCount)).append("\" title=\"下一页\">&nbsp;&gt;</a></li>");
            } else if (isHasNext()) {
                inputContent.append("<li><span class=\"disabled\">&lt;&lt;&nbsp;</span></li>");
                inputContent.append("<li><span class=\"disabled\">&lt;&nbsp;</span></li>");
                inputContent.append("<li><span class=\"current\">1</span></li>");
                int end = this.pageNo + 5;
                if (end > getTotalPages()) {
                    end = getTotalPages();
                }
                for (int i = 2; i <= end; i++) {
                    inputContent.append("<li><a href=\"javascript:").append(compositePaging(i, this.totalCount)).append("\">").append(i).append("</a></li>");
                }
                inputContent.append("<li>...</li>");
                inputContent.append("<li><a href=\"javascript:").append(compositePaging(this.pageNo + 1, this.totalCount)).append("\" title=\"下一页\">&nbsp;&gt;</a></li>");
            } else if (isHasPre()) {
                inputContent.append("<li><a href=\"javascript:").append(compositePaging(1, this.totalCount)).append("\" title=\"首页\">&lt;&lt;&nbsp;</a></li>");
                inputContent.append("<li><a href=\"javascript:").append(compositePaging(this.pageNo - 1, this.totalCount)).append("\" title=\"上一页\">&lt;&nbsp;</a></li>");
                int begin = this.pageNo - 5;
                if (begin < 1) {
                    begin = 1;
                }
                for (int i = begin; i < getTotalPages(); i++) {
                    inputContent.append("<li><a href=\"javascript:").append(compositePaging(i, this.totalCount)).append("\">").append(i).append("</a></li>");
                }
                inputContent.append("<li><span class=\"current\">").append(getTotalPages()).append("</span></a></li>");
                inputContent.append("<li><span class=\"disabled\">&nbsp;&gt;</span></li>");
            }
        } else {
            inputContent.append("<div class=\"fr pdR20\"><font color='red'>没有可显示的数据!</font></div>");
        }
        try {
            this.pageContext.getOut().write(inputContent.toString());
        } catch (Exception e) {
            throw new JspException(e);
        }
        return 0;
    }
}
