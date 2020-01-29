package com.eatza.restaurantsearch.kafka;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SenderData {
	
	private Long senderId;
	private String senderName;
	private String massege;
	@Override
	public String toString() {
		return "SenderData [senderId=" + senderId + ", senderName=" + senderName + ", massege=" + massege + "]";
	}

	
	
}
