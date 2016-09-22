package com.shedhack.filter.requestid;

import com.shedhack.exception.controller.spring.ExceptionController;
import com.shedhack.exception.core.ExceptionModel;
import com.shedhack.trace.request.api.constant.HttpHeaderKeysEnum;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTest {

    @Autowired
	private TestRestTemplate template;

	@Test
	public void getProblem() throws Exception {

		ResponseEntity<ExceptionModel> response = template.getForEntity(makeURL("problem").toString(), ExceptionModel.class);

		// Check that the response is the exception model
		assertThat(response.getStatusCode().value(), equalTo(400));

		// body
		assertThat(response.getBody().getApplicationName(), equalTo("demo"));
		assertThat(response.getBody().getSpanId(), notNullValue());
        assertThat(response.getBody().getTraceId(), notNullValue());
		assertThat(response.getBody().getExceptionId(), notNullValue());
		assertThat(response.getBody().getHttpStatusDescription(), equalTo("Bad Request"));
		assertThat(response.getBody().getPath(), equalTo("/problem"));
		assertThat(response.getBody().getSessionId(), notNullValue());
		assertThat(response.getBody().getHelpLink(), equalTo("http://link"));
		assertThat(response.getBody().getMessage(), equalTo("Something horrible happened"));
		assertThat(response.getBody().getExceptionClass(), equalTo("com.shedhack.exception.core.BusinessException"));
		assertThat(response.getBody().getMetadata(), equalTo("exception-core-model"));
		assertThat(response.getBody().getHttpStatusCode(), equalTo(400));
		assertThat(response.getBody().getParams(), hasKey("user"));
		assertThat(response.getBody().getParams().get("user"), Matchers.<Object>equalTo("imam"));
		assertThat(response.getBody().getBusinessCodes(), hasKey("E100"));
		assertThat(response.getBody().getContext(), hasKey("threadName"));
		assertThat(response.getBody().getDateTime(), notNullValue());
		assertThat(response.getBody().getExceptionChain(), notNullValue());

		// header
		assertThat(response.getHeaders().get("exceptionId"), notNullValue());
		assertThat(response.getHeaders().get("exceptionId").toString(), equalTo("["+response.getBody().getExceptionId()+"]"));
		assertThat(response.getHeaders().get("exceptionType"), notNullValue());

		// check the exception count
		assertThat(ExceptionController.getExceptionCount(), equalTo(1));

        // trace + span
        assertThat(response.getHeaders().get(HttpHeaderKeysEnum.TRACE_ID.key()), notNullValue());
        assertThat(response.getHeaders().get(HttpHeaderKeysEnum.SPAN_ID.key()), notNullValue());
    }

	private String makeURL(String path) throws Exception {
		return "/" + path;
	}
}
