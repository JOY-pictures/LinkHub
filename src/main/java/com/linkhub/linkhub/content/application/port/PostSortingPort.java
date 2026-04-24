package com.linkhub.linkhub.content.application.port;

import com.linkhub.linkhub.content.application.model.PostSummary;

import java.util.List;

public interface PostSortingPort {
    List<PostSummary> findPostsByModeWithLimit(String modeName, int limit);
}
