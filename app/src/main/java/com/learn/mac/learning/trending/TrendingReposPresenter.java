package com.learn.mac.learning.trending;

import com.learn.mac.learning.data.RepoRequester;
import com.learn.mac.learning.di.ScreenScope;
import com.learn.mac.learning.model.Repo;

import javax.inject.Inject;

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener{

    private final TrendingReposViewModal viewModel;
    private final RepoRequester repoRequester;

    @Inject
    TrendingReposPresenter(TrendingReposViewModal viewModel, RepoRequester repoRequester) {

        this.viewModel = viewModel;
        this.repoRequester = repoRequester;
        loadRepos();
    }

    private void loadRepos() {
        repoRequester.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError());
    }

    @Override
    public void onRepoClicked(Repo repo) {

    }
}