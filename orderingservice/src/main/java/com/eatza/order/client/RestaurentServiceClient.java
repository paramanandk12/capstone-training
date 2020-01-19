package com.eatza.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eatza.order.dto.ItemFetchDto;

@FeignClient("restaurentsearchservice")
@RequestMapping("/item")
public interface RestaurentServiceClient {

	@GetMapping(value = "/id/{itemId}")
	ItemFetchDto getMenuItemById(@PathVariable("itemId") long itemId);

}
