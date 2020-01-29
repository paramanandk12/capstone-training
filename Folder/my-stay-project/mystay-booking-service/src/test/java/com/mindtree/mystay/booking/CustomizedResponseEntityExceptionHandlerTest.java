package com.mindtree.mystay.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.ServletWebRequest;

import com.mindtree.mystay.booking.exception.CustomizedResponseEntityExceptionHandler;
import com.mindtree.mystay.booking.exception.ExceptionResponse;
import com.mindtree.mystay.booking.exception.RoomNotAvailableException;

/**
 * @author Paplu Patel(M1048008)
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomizedResponseEntityExceptionHandlerTest {

	@Autowired
	private CustomizedResponseEntityExceptionHandler custException;
	private Exception ex = null;

	@Before
	public void init() {
		ex = new Exception("Test Exception");
	}

	@Test
	public void testHandleAllExceptions() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ResponseEntity<Object> response = custException.handleAllExceptions(ex, new ServletWebRequest(request));
		assertNotNull(response);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		ExceptionResponse respBody = (ExceptionResponse) response.getBody();
		assertEquals("Test Exception", respBody.getMessage());
	}

	@Test
	public void testHandleRoomNotFoundExceptions() throws RoomNotAvailableException {

		MockHttpServletRequest request = new MockHttpServletRequest();
		ResponseEntity<Object> response = custException.handleRoomNotFoundExceptions(ex,
				new ServletWebRequest(request));
		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		ExceptionResponse respBody = (ExceptionResponse) response.getBody();
		assertEquals("Test Exception", respBody.getMessage());
	}
}
