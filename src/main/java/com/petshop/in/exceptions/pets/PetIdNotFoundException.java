package com.petshop.in.exceptions.pets;

public class PetIdNotFoundException extends Throwable {

	public PetIdNotFoundException(String message)
	{
		super(message);
	}
	
//	public PetIdNotFoundException(String message, Throwable e)
//	{
//		super(message, e);
//	}
//	
//	public PetIdNotFoundException(String message, Throwable e, boolean enableSuppression, boolean enableStacktrace)
//	{
//		super(message, e, enableSuppression, enableStacktrace);
//	}
}
