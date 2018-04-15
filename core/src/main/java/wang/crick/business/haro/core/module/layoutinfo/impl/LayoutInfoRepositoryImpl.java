package wang.crick.business.haro.core.module.layoutinfo.impl;

import org.springframework.stereotype.Repository;
import wang.crick.business.haro.core.base.dao.BaseDao;
import wang.crick.business.haro.core.domain.LayoutInfo;
import wang.crick.business.haro.core.module.layoutinfo.LayoutInfoRepository;
import wang.crick.business.haro.core.module.layoutinfo.orm.LayoutInfoRowMapper;

@Repository
public class LayoutInfoRepositoryImpl extends BaseDao implements LayoutInfoRepository {

    public LayoutInfo findUnique() {
        return queryOne("select id, hotline, address, zip_code, telephone, " +
                "copyright, icp_no, publish_time, publish_user " +
                "from ha_r_layout_info limit 1 ", new LayoutInfoRowMapper());
    }

    public boolean update(LayoutInfo layoutInfo) {
        return update("update ha_r_layout_info set hotline=?, address=?, zip_code=?, telephone=?, copyright=?, " +
                "icp_no=?, publish_time=?, publish_user=? where id=?",
                layoutInfo.getHotline(), layoutInfo.getAddress(), layoutInfo.getZipCode(), layoutInfo.getTelephone(), layoutInfo.getCopyright(),
                layoutInfo.getIcpNo(), layoutInfo.getPublishTime(), layoutInfo.getPublishUser(), layoutInfo.getId());
    }

    public boolean create(LayoutInfo layoutInfo) {
        return insert("insert into ha_r_layout_info" +
                "(id, hotline, address, zip_code, telephone, " +
                "copyright, icp_no, publish_time, publish_user)values" +
                "(?,?,?,?,?," +
                "?,?,?,?)",
                layoutInfo.getId(), layoutInfo.getHotline(), layoutInfo.getAddress(), layoutInfo.getZipCode(), layoutInfo.getTelephone(),
                layoutInfo.getCopyright(), layoutInfo.getIcpNo(), layoutInfo.getPublishTime(), layoutInfo.getPublishUser());
    }
}
