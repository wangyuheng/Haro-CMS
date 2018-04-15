package wang.crick.business.haro.core.base.mvc.entity;

public abstract class BaseTenantDomain extends BaseDomain {
    private String tenantId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
