package com.socketchatapp.message;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class HelloMessage {

	private String name;
	private String contents;
	private LocalDateTime sendDate;

	public HelloMessage(String name, String contents, LocalDateTime sendDate) {
		this.name = name;
		this.contents = contents;
		this.sendDate = sendDate;
	}
}

