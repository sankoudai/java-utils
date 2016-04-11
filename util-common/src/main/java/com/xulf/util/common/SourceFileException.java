package com.xulf.util.common;

import java.io.IOException;

public class SourceFileException extends IOException {

	public SourceFileException() {
	}

	public SourceFileException(String message) {
		super(message);
	}

	public SourceFileException(Throwable cause) {
		super(cause);
	}

	public SourceFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
