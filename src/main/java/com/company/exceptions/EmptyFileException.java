package com.company.exceptions;

public class EmptyFileException extends Throwable {
    public EmptyFileException() {
        super("File is empty");
    }
}
