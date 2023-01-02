package com.anush.trendingrepositories;

import com.anush.trendingrepositories.data.entities.OwnerEntity;
import com.anush.trendingrepositories.data.entities.RepositoryEntity;
import com.anush.trendingrepositories.models.Owner;
import com.anush.trendingrepositories.models.Repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class RepositoryMapper {

    @Inject
    public RepositoryMapper() {
    }

    public List<Repository> mapToRepositoryList(List<RepositoryEntity> entityList) {
        return entityList.stream().map(this::mapToRepository).collect(Collectors.toList());
    }
    public Repository mapToRepository(RepositoryEntity entity) {
        return new Repository(entity.getName(), entity.getDescription(), mapToOwner(entity.getOwner()), entity.getStargazersCount());
    }

    public Owner mapToOwner(OwnerEntity entity) {
        return new Owner(entity.getLogin(), entity.getAvatarUrl());
    }
}
