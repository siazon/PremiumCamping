package com.camping.utility;

import java.io.File;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 1 Aug 2022 20:18:20
 * @version 1.0
 */
public class Utilitys {

	public static boolean checkEmailFormat(String content) {

		String REGEX = "^\\w+((-\\w+)|(\\.\\w+))*@\\w+(\\.\\w{2,3}){1,3}$";
		Pattern p = Pattern.compile(REGEX);
		Matcher matcher = p.matcher(content);

		return matcher.matches();
	}
	//only runs in a webserver
	public String getFileUploadLocationOnDisk() {
		ServletContext servletContext = (ServletContext) FacesContext
    		    .getCurrentInstance().getExternalContext().getContext();
		String destination = servletContext.getRealPath("/")+File.separator+"resources"+File.separator+"uploaded";;
		return destination;
	}
	
	public String getFileUploadLocationOnServer() {
		return "./resources/uploaded/";
	}

}
