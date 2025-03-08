package com.teamproject.clonemoviepop.user.service;

import com.teamproject.clonemoviepop.tag.entity.Tag;
import com.teamproject.clonemoviepop.user.entity.UserTag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserTagService {

    public List<Tag> findTagByUserTag(List<UserTag> userTags) {
        List<Tag> tags = new ArrayList<>();

        for (UserTag findUserTag : userTags) {
            Tag tag = findUserTag.getTag();
            tags.add(tag);
        }

        return tags;
    }

}
