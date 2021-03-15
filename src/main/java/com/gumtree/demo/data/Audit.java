package com.gumtree.demo.data;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Audit<T> {

	private T previous;
	private T recent;
	private LocalDateTime time;

}
