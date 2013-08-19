package com.totsp.tom.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: keber_000
 * Date: 8/12/13
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class EvaluationException extends RuntimeException {

    public EvaluationException(String message, Throwable cause){
        super(message, cause);
    }
}
