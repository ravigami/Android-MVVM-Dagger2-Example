package com.uday.androidsample.viewmodel;

import com.uday.androidsample.util.TestResourceReaderUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by uday on 14/07/18.
 */
public class FactsViewModelTest {

    private ClassLoader mClassLoader;


    @Before
    public void setup(){

        mClassLoader = this.getClass().getClassLoader();
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testJsonFileLoading() {
        // Given


        // When
        String loadedJsonString = TestResourceReaderUtil.readFile(mClassLoader,
                "get_facts_sample_client_response.json");

        // Then
        assertThat(loadedJsonString, instanceOf(String.class));
    }
    @Test
    public void getFacts() throws Exception {

    }

    @Test
    public void isDataAvailableViewModel() throws Exception {
    }

    @Test
    public void getData() throws Exception {
    }

}