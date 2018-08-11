package com.learn.mac.learning.data;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();
}
