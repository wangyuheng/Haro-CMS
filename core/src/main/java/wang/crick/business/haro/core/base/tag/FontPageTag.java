package wang.crick.business.haro.core.base.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 分页标签
 */
public class FontPageTag extends TagSupport {

    private static final long serialVersionUID = 280053973475466854L;
    private int totalCount = 0;
    private int totalPages = -1;
    private int pageSize;
    private int pageNo = 1;
    private String action;
    private String update;
    private String form;

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

    public final String getUpdate() {
        return this.update;
    }

    public final void setUpdate(String update) {
        this.update = update;
    }

    public final String getForm() {
        return this.form;
    }

    public final void setForm(String form) {
        this.form = form;
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
        content.append("},");
        if (this.form != null) {
            content.append("form:'").append(this.form).append("',");
        }
        if (this.update != null) {
            content.append("updateId:'").append(this.update).append("',");
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
            inputContent.append("<div class=\"news-page\">");

            if ((isHasNext()) && (isHasPre())) {
                inputContent.append("<a href=\"javascript:").append(compositePaging(this.pageNo - 1, this.totalCount)).append("\" class=\"prev\" title=\"上一页\">上一页</a>");
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
                        inputContent.append("<span>").append(i).append("</span>");
                    } else {
                        inputContent.append("<a href=\"javascript:").append(compositePaging(i, this.totalCount)).append("\">").append(i).append("</a>");
                    }
                }
                inputContent.append("<a href=\"javascript:").append(compositePaging(this.pageNo + 1, this.totalCount)).append("\" class=\"next\" title=\"下一页\">下一页</a>");
            } else if (isHasNext()) {
                inputContent.append("<a href=\"javascript:").append(compositePaging(this.pageNo - 1, this.totalCount)).append("\" class=\"prev hid\" title=\"上一页\">上一页</a>");
                inputContent.append("<span>1</span>");
                int end = this.pageNo + 5;
                if (end > getTotalPages()) {
                    end = getTotalPages();
                }
                for (int i = 2; i <= end; i++) {
                    inputContent.append("<a href=\"javascript:").append(compositePaging(i, this.totalCount)).append("\">").append(i).append("</a>");
                }
                inputContent.append("<a href=\"javascript:").append(compositePaging(this.pageNo + 1, this.totalCount)).append("\" class=\"next\" title=\"下一页\">下一页</a>");
            } else if (isHasPre()) {
                inputContent.append("<a href=\"javascript:").append(compositePaging(1, this.totalCount)).append("\" class=\"prev\" title=\"首页\">首页</a>");
                inputContent.append("<a href=\"javascript:").append(compositePaging(this.pageNo - 1, this.totalCount)).append("\" class=\"prev\" title=\"上一页\">上一页</a>");
                int begin = this.pageNo - 5;
                if (begin < 1) {
                    begin = 1;
                }
                for (int i = begin; i < getTotalPages(); i++) {
                    inputContent.append("<a href=\"javascript:").append(compositePaging(i, this.totalCount)).append("\">").append(i).append("</a>");
                }
                inputContent.append("<span>").append(getTotalPages()).append("</span>");

            } else {
                inputContent.append("<span>").append(this.pageNo).append("</span>");
            }
            inputContent.append("<em>(第").append(this.pageNo).append("页，共").append(this.totalCount).append("条)</em><br class=\"clr\" />");
            inputContent.append("</div>");
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
