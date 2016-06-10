
package com.argonnet.Exception;

/**
 * Exception that is launch when a problem (what in GUI) is unkown
 */
public class UnknownHowException extends Exception{

    public UnknownHowException(){
        super("An unkown soltion algorythm as been selected !");
    }
}

