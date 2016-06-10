package com.argonnet.Exception;

/**
 * Exception that is launch when a problem (what in GUI) is unkown
 */
public class UnknownWhatException extends Exception{

    public UnknownWhatException(){
        super("An unkown problem as been selected !");
    }
}
