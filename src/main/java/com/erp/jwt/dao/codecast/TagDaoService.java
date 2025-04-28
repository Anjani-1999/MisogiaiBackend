package com.erp.jwt.dao.codecast;

import com.erp.jwt.entity.codecast.TagEntity;
import com.erp.jwt.repo.codecast.TagRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagDaoService {

    private final TagRepo tagRepo;

    public TagDaoService(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    public TagEntity createTag(TagEntity tag) {
        return tagRepo.save(tag);
    }


    public List<String> getUniqueTags() {
        return tagRepo.findDistinctTags();
    }
}
