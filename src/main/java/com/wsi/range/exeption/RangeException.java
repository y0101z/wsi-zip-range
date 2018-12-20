package com.wsi.range.exeption;

public class RangeException extends Exception
{
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public RangeException(String errorMessage)
	{
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public RangeException()
	{
		super();
	}
}
