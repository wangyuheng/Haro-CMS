package wang.crick.business.haro.core.module.news;

import wang.crick.business.haro.core.base.mvc.entity.BasePageDto;
import wang.crick.business.haro.core.domain.News;

import java.util.List;
import java.util.Map;

public interface NewsRepository {
    boolean create(News entity);

    //TODO mongoDB
    boolean createContent(String newsId, String content);

    List<News> findPaged(BasePageDto dto, Map<String, Object> params);

    List<News> findPagedSimple(BasePageDto page, Map<String, Object> params);

    News findUnique(String id);

    String getContent(String newsId);

    List<News> findIndex(int type);

    boolean delete(String id);
}
