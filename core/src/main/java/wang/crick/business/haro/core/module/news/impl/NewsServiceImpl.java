package wang.crick.business.haro.core.module.news.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wang.crick.business.haro.core.base.helper.guid.HelperGuid;
import wang.crick.business.haro.core.domain.News;
import wang.crick.business.haro.core.module.news.NewsRepository;
import wang.crick.business.haro.core.module.news.NewsService;
import wang.crick.business.haro.core.module.news.dto.NewsDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;


    public boolean create(News entity) {
        entity.setId(HelperGuid.getRandomStr());
        return newsRepository.create(entity) && newsRepository.createContent(entity.getId(), entity.getContent());
    }

    public List<News> findPaged(NewsDto dto) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(dto.getTitle())) {
            params.put("title", "%" + dto.getTitle() + "%");
        }
        if (!StringUtils.isEmpty(dto.getAuthor())) {
            params.put("author", "%" + dto.getAuthor() + "%");
        }
        if (dto.getType() > 0) {
            params.put("type", dto.getType());
        }
        return newsRepository.findPaged(dto, params);
    }

    @Override
    public List<News> findPagedSimple(NewsDto dto) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (dto.getType() > 0) {
            params.put("type", dto.getType());
        }
        if (!StringUtils.isEmpty(dto.getTitle())) {
            params.put("title", "%"+dto.getTitle()+"%");
        }
        return newsRepository.findPagedSimple(dto, params);
    }

    @Override
    public News findUnique(String id) {
        News result = null;
        if (HelperGuid.isUUID(id)) {
            result = newsRepository.findUnique(id);
            result.setContent(newsRepository.getContent(id));
        }
        return result;
    }

    @Override
    public List<News> findIndex(int type) {
        return newsRepository.findIndex(type);
    }

    public boolean delete(String id) {
        return newsRepository.delete(id);
    }

}
