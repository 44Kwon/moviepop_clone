package com.teamproject.clonemoviepop.tag.mapper;

import com.teamproject.clonemoviepop.tag.dto.TagDto;
import com.teamproject.clonemoviepop.tag.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto.Response tagToResponse(Tag tag);
    List<TagDto.Response> tagsToResponses(List<Tag> tag);
}
