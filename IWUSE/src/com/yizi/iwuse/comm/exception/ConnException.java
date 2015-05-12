package com.yizi.iwuse.comm.exception;

public class ConnException extends Exception
{

    private static final long serialVersionUID = -277639519461088285L;

    public ConnException()
    {
        super();
    }

    /**
     * @param detailMessage
     * @param throwable
     */
    public ConnException(String detailMessage, Throwable throwable)
    {
        super(detailMessage, throwable);
    }

    /**
     * @param detailMessage
     */
    public ConnException(String detailMessage)
    {
        super(detailMessage);
    }

    /**
     * @param throwable
     */
    public ConnException(Throwable throwable)
    {
        super(throwable);
    }

}
