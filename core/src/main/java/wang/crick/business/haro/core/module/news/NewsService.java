package wang.crick.business.haro.core.module.news;

import wang.crick.business.haro.core.domain.News;
import wang.crick.business.haro.core.module.news.dto.NewsDto;

import java.util.List;

/**
 * 新闻管理模块
 */
public interface NewsService {

    boolean create(News entity);

    List<News> findPaged(NewsDto dto);

    List<News> findPagedSimple(NewsDto dto);

    News findUnique(String id);

    List<News> findIndex(int type);

    boolean delete(String id);
}
