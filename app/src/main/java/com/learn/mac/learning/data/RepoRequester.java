package com.learn.mac.learning.data;

import com.learn.mac.learning.model.Repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RepoRequester {
    private final RepoService repoService;

    @Inject
    public RepoRequester(RepoService repoService) {
        this.repoService = repoService;
    }

    public Single<List<Repo>> getTrendingRepos(){
        return repoService.getTrendingRepos()
                .map(new Function<TrendingReposResponse, List<Repo>>() {
                    @Override
                    public List<Repo> apply(TrendingReposResponse trendingReposResponse) throws Exception {
                        return trendingReposResponse.repos();
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
