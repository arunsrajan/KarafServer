package org.singam.karaf.bundle.dependency.installer;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

@Command(scope = "bundleuninstall", name = "class", description="Bundle Dependency List")
public class BundleUninstallByPackage extends OsgiCommandSupport {

	@Argument(index = 0, name = "arg", 
            description = "The command argument for dependency bundle list", 
            required = false, multiValued = false)
	String arg = null;
	@Override
	protected Object doExecute() throws Exception {
		ServiceReference<PackageAdmin> packageAdminService= bundleContext.getServiceReference(PackageAdmin.class);
		PackageAdmin packageAdmin=bundleContext.getService(packageAdminService);
		ExportedPackage exportedPackage=packageAdmin.getExportedPackage(arg.substring(0,arg.lastIndexOf('.')));
		Bundle exportingBundle=exportedPackage.getExportingBundle();
		System.out.println("Uninstalling the bundle with Id and Name "+exportingBundle.getBundleId()+" "+exportingBundle.getSymbolicName());
		exportingBundle.uninstall();
		System.out.println("Bundle "+exportingBundle.getBundleId() +" Successfully Uninstalled");
		return null;
	}

}
