package com.xulf.util.common;

import java.io.IOException;

public class AimFileException extends IOException {

	public AimFileException() {
	}

	public AimFileException(String message) {
		super(message);
	}

	public AimFileException(Throwable cause) {
		super(cause);
	}

	public AimFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
