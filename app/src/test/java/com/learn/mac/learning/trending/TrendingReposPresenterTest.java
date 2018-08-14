package com.learn.mac.learning.trending;

import com.learn.mac.learning.data.RepoRequester;
import com.learn.mac.learning.data.TrendingReposResponse;
import com.learn.mac.learning.model.Repo;
import com.learn.mac.learning.testUtils.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class TrendingReposPresenterTest {

    @Mock RepoRequester repoRequester;
    @Mock TrendingReposViewModal viewModal;
    @Mock Consumer<Throwable> onErrorConsumer;
    @Mock Consumer<List<Repo>> onSuccesConsumer;
    @Mock Consumer<Boolean> loadingConsumer;

    private TrendingReposPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(viewModal.loadingUpdated()).thenReturn(loadingConsumer);
        when(viewModal.onError()).thenReturn(onErrorConsumer);
        when(viewModal.reposUpdated()).thenReturn(onSuccesConsumer);
    }

    @Test
    public void responseLoaded() throws Exception {
        List<Repo> repos = setUpSuccess();
        initializePresenter();

        verify(repoRequester).getTrendingRepos();
        verify(onSuccesConsumer).accept(repos);
    }

    @Test
    public void reposLoadedError() throws Exception {
        Throwable error = setUpError();
        initializePresenter();

        verify(onErrorConsumer).accept(error);
        verifyZeroInteractions(onSuccesConsumer);
    }

    @Test
    public void loadingSuccess() throws Exception {
        setUpSuccess();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @Test
    public void loadingError() throws Exception {
        //noinspection ThrowableNotThrown
        setUpError();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @Test
    public void onRepoClicked() {
        //TODO
    }

    private List<Repo> setUpSuccess(){
        TrendingReposResponse response = TestUtils.loadJson("mock/get_trending_repos.json",TrendingReposResponse.class);
        List<Repo> repos = response.repos();

        when(repoRequester.getTrendingRepos()).thenReturn(Single.just(repos));

        return repos;
    }

    private Throwable setUpError(){
        Throwable error = new IOException();
        when(repoRequester.getTrendingRepos()).thenReturn(Single.error(error));

        return error;
    }

    private void initializePresenter(){
        presenter = new TrendingReposPresenter(viewModal, repoRequester);
    }
}