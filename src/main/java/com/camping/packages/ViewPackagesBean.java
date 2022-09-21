/*
 *
 *  Author: Thuduhenage Laleesha Wijetunga
 *  
 */
package com.camping.packages;

/**
 * @author Thuduhenage Laleesha Wijetunga
 *
 */

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.camping.common.DataCache;

@ManagedBean(name = "viewPackagesBean")
@SessionScoped
public class ViewPackagesBean {

	private ArrayList<CampingPackage> campingPackages;

	public ViewPackagesBean() {
	}

	@PostConstruct
	public void init() {
		campingPackages = DataCache.getSingleton().getCampingPackages();
		System.out.println("[INFO]: " + campingPackages);
	}

	public ArrayList<CampingPackage> getCampingPackages() {
		return this.campingPackages;
	}

	public boolean deletePackage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String pkgId = getPackageID(fc);
		System.out.println("[INFO]: Delete package is requested for "+pkgId);
		for (CampingPackage pkgToDelete:campingPackages) {
			if(pkgToDelete.getId().equals(pkgId)) {
				campingPackages.remove(campingPackages.indexOf(pkgToDelete));
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful",
						"Package deleted successfully.");
				FacesContext.getCurrentInstance().addMessage("message", message);
				System.out.println("[INFO]: "+pkgId+" is deleted successfully");
				return true;
			}
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
				"Package deletion failed.");
		FacesContext.getCurrentInstance().addMessage("message", message);
		System.out.println("[INFO]: "+pkgId+" is not found");
		return false;
	}
	
	public String getPackageID(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("pkgId");

	}

}
