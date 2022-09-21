package com.camping.packages;

/**
 * @author Thuduhenage Laleesha Wijetunga
 *
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.camping.common.DataCache;
import com.camping.packages.CampingPackage;
import com.camping.utility.Utilitys;

@ManagedBean(name = "addPackageBean")
@SessionScoped
public class AddPackageBean {

	/**
	 * 
	 */
	ArrayList<CampingPackage> packages;
	private String packageType;
	private List<String> locations;
	private String selectedLocation = new String();
	private int paxAdults;
	private int paxKids;
	private String price;
	private boolean validPrice;
	private UploadedFile packageImage;
	private boolean validPackageImage;
	private String description;
	private String destination;
	private String imgPath;

	public AddPackageBean() {
		this.packages = new ArrayList<>();
		initializeInputs();

	}

	public void setPackageType(String pkgType) {
		this.packageType = pkgType;
	}

	public String getPackageType() {
		return this.packageType;
	}

	public void setSelectedLocation(String selected) {
		this.selectedLocation = selected;
	}

	public String getSelectedLocation() {
		return this.selectedLocation;
	}

	public void setPaxAdults(int adults) {
		this.paxAdults = adults;
	}

	public int getPaxAdults() {
		return this.paxAdults;
	}

	public void setPaxKids(int kids) {
		this.paxKids = kids;
	}

	public int getPaxKids() {
		return this.paxKids;
	}

	public void setLocations(List<String> loc) {
		this.locations = loc;
	}

	public List<String> getLocations() {
		return this.locations;
	}

	public UploadedFile getPackageImage() {
		return this.packageImage;
	}

	public void setPackageImage(UploadedFile pkgImage) {
		this.packageImage = pkgImage;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return this.description;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice() {
		return this.price;
	}

	public boolean addPackage() throws IOException, InterruptedException {
		// verification package name
		System.out.println("[INFO]: Started adding a new package to the system.");
		
		if (getPaxAdults() <= 0 ) {
			System.out.println("[INFO]: Validating capacity");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"At least 1 adult should be included in a package.");
			FacesContext.getCurrentInstance().addMessage("message", message);
			return false;
		}		
		try {
			if (!handleFileUpload()) {
				return false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!this.validPrice) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Price should be a numeric value.");
			FacesContext.getCurrentInstance().addMessage("message", message);

			return false;
		}
		UUID randomUUID = UUID.randomUUID();
		CampingPackage newPackage = new CampingPackage();
		newPackage.setId(randomUUID.toString().replaceAll("_", ""));
		newPackage.setName(this.packageType);
		newPackage.setLocation(this.selectedLocation);
		newPackage.setLocation(this.selectedLocation);
		newPackage.setAdultsPax(this.paxAdults);
		newPackage.setKidsPax(this.paxKids);
		newPackage.setPrice(Double.parseDouble(this.price));
		newPackage.setPriceWithDecimal(new DecimalFormat("#.00").format(newPackage.getPrice()));
		newPackage.setImgPath(this.imgPath);
		newPackage.setDescription(this.description);
		packages.add(newPackage);
		System.out.println("[INFO]: New package created "+newPackage);
		
		DataCache.getSingleton().setCampingPackages(packages);
		
		System.out.println("[INFO]: Camping packages list updated");
		for (CampingPackage pkg:DataCache.getSingleton().getCampingPackages()) {
			System.out.println("[INFO]: Package Details --> "+pkg);
		}
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful",
				"Package is added successfully.");
		FacesContext.getCurrentInstance().addMessage("message", message);
		return true;
	}

	private boolean handleFileUpload() throws IOException {
		System.out.println("[INFO]: Handler for image upload is called.");
		if (this.validPackageImage) {
			if (packageImage != null) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful",
						this.packageImage.getFileName() + " is uploaded.");
				FacesContext.getCurrentInstance().addMessage("message", message);
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						"Image upload failed. Please check the file");
				FacesContext.getCurrentInstance().addMessage("message", message);
				return false;
			}
			try {
				this.imgPath=copyFile(this.packageImage.getFileName(), this.packageImage.getInputstream());
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

	public void validatePrice(FacesContext context, UIComponent comp, Object value) {
		System.out.println("[INFO]: Handler for validating price is called.");
		final Pattern pattern = Pattern.compile("^[+-]?([0-9]+\\.?[0-9]*|\\.[0-9]+)$");
		if (!pattern.matcher((String) value).matches()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Price should be a numeric value.");
			context.addMessage("message", message);
			this.validPrice = false;
		}
		else {
			this.validPrice = true;
		}

	}

	public void validateImage(FacesContext context, UIComponent comp, Object value) {
		System.out.println("[INFO]: Handler for validating image is called.");
		UploadedFile img = (UploadedFile) value;
		final Pattern pattern = Pattern.compile("^(.)+(\\.)(jpe?g|png)$");
		if (!pattern.matcher(img.getFileName()).matches()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Image file should be in jpg or png format.");
			context.addMessage("message", message);
			this.validPackageImage = false;
		} else {
			this.validPackageImage = true;
		}
	}
	
	private String copyFile(String fileName, InputStream in) {
		this.destination = new Utilitys().getFileUploadLocationOnDisk();
		try {
			// write the inputStream to a FileOutputStream
			File imgFile = new File(this.destination + File.separator + fileName);
			imgFile.createNewFile(); // if file already exists will do nothing
			OutputStream out = new FileOutputStream(imgFile, false);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			in.close();
			out.flush();
			out.close();
			System.out.println("[INFO]: New file uploaded: " + (this.destination + File.separator + fileName));
			return (new Utilitys().getFileUploadLocationOnServer() + fileName);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void reload() throws IOException {
		initializeInputs();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}

	private void initializeInputs() {
		this.packageType = "";

		locations = new ArrayList<String>();
		locations.add("Forest View");
		locations.add("Lake Side View");
		locations.add("Center of Park");
		locations.add("Mountain View");
		locations.add("Glamping Village");
		this.selectedLocation = "Select One";

		this.paxAdults = 0;
		this.paxKids = 0;
		this.price = "";
		this.description = "";
		this.destination = "";
		this.validPackageImage = false;
		this.validPrice = false;
	}
}
