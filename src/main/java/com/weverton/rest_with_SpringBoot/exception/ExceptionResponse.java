package com.weverton.rest_with_SpringBoot.exception;

import java.util.Date;

public record ExceptionResponse (Date timestamp, String message, String details) {}