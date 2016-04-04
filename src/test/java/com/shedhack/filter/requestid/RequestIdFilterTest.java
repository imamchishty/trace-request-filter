package com.shedhack.filter.requestid;

import com.shedhack.filter.requestid.filter.RequestIdFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Request Filter test.
 *
 * @author imamchishty
 */
@RunWith(MockitoJUnitRunner.class)
public class RequestIdFilterTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    FilterChain chain;


    @Test
    public void should_add_requestId_to_headers() throws IOException, ServletException {

        // Arrange
        RequestIdFilter filter = new RequestIdFilter();

        // Act
        filter.doFilter(request, response, chain);

        // Assert

    }

}
