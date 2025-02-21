package com.teamproject.clonemoviepop.tag.service;

import com.teamproject.clonemoviepop.tag.dto.TagDto;
import com.teamproject.clonemoviepop.tag.entity.Tag;
import com.teamproject.clonemoviepop.tag.mapper.TagMapper;
import com.teamproject.clonemoviepop.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Transactional
    public void makeInitData(Set<Tag> tagSet) {
        tagRepository.saveAll(tagSet);
    }

    public Set<Tag> findTags() {
        return tagRepository.findAll().stream().collect(Collectors.toSet());
    }

    public List<TagDto.Response> getTags() {
        return tagMapper.tagsToResponses(tagRepository.findAll());
    }

    public Tag findTagById(Long tagId) {
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        Tag findTag = optionalTag.orElseThrow(() -> new IllegalArgumentException("나중에"));

        return findTag;
    }
}
