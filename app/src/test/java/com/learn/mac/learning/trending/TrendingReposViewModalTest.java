package com.learn.mac.learning.trending;

import com.learn.mac.learning.R;
import com.learn.mac.learning.data.TrendingReposResponse;
import com.learn.mac.learning.testUtils.TestUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

public class TrendingReposViewModalTest {

    private TrendingReposViewModal viewModal;

    @Before
    public void setUp() throws Exception {
        viewModal = new TrendingReposViewModal();
    }

    @Test
    public void loading() throws Exception {
        TestObserver<Boolean> loadingObserver = viewModal.loading().test();
        viewModal.loadingUpdated().accept(true);
        viewModal.loadingUpdated().accept(false);

        loadingObserver.assertValues(true, false);
    }

    @Test
    public void repos() throws Exception {
        TrendingReposResponse response = TestUtils.loadJson("mock/get_trending_repos.json",TrendingReposResponse.class);
        viewModal.reposUpdated().accept(response.repos());

        viewModal.repos().test().assertValue(response.repos());
    }

    @Test
    public void error() throws Exception {
        TestObserver<Integer> errorObserver = viewModal.error().test();
        viewModal.onError().accept(new IOException());
        viewModal.reposUpdated().accept(Collections.emptyList());

        errorObserver.assertValues(R.string.api_error_repos, -1);
    }
}