package com.petshop.in.exceptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.petshop.in.enums.TransactionStatus;

public class ValidationClass {
	
	public static boolean validationInt(String str)
	{
		try
		{
			Integer.parseInt(str);
			return false;
		}
		catch(NumberFormatException e)
		{
			return true;
		}
	}
	
	public static boolean validationDouble(String str)
	{
		try
		{
			Double.parseDouble(str);
			return false;
		}
		catch(NumberFormatException e)
		{
			return true;
		}
	}
	
	public static boolean isValidDateFormat(Date date)
	{
		if(date == null)
		{
			return false;
		}
		try
		{
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = dateFormatter.format(date);
			dateFormatter.parse(dateString);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean validateTransactionStatus(TransactionStatus status)
	{
		if(status == null || (status != TransactionStatus.Success) || (status != TransactionStatus.Failed))
		{
			return false;
		}
		return true;
	}
	
	public static boolean validationInt1(int id)
	{
		try
		{
			Integer.parseInt(String.valueOf(id));
			return false;
		}
		catch(NumberFormatException e)
		{
			return true;
		}
	}
	
	public static boolean validationString(int id)
	{
		try
		{
			Integer.parseInt(String.valueOf(id));
			return false;
		}
		catch(NumberFormatException e)
		{
			return true;
		}
	}
	
	public static boolean validationDate(String str)
	{
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		try
		{
			Date date = formatter.parse(str);
			return false;
		}
		catch(ParseException e)
		{
			return true;
		}
	}

}
