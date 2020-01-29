package com.mindtree.mystay.zuul.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.netflix.zuul.exception.ZuulException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreFilterTest {

	@Autowired
	PreFilter preFilter;
	
	@Test
	public void shouldFilterTest() {
		assertEquals(true,preFilter.shouldFilter());
	}
	
	@Test
	public void runTest() {
		try {
			assertEquals(null, preFilter.run());
		} catch (ZuulException e) {
		}
	}
	
	@Test
	public void filterType() {
		assertEquals("pre",preFilter.filterType());
	}
	
	@Test
	public void filterOrder() {
		assertEquals(1,preFilter.filterOrder());
	}
}
