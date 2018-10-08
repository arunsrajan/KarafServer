package org.singam.karaf.bundle.dependency.installer;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

@Command(scope = "bundledep", name = "list", description="Bundle Dependency List")
public class BundleDependencyList extends OsgiCommandSupport{

	
	@Argument(index = 0, name = "arg", 
            description = "The command argument for dependency bundle list", 
            required = false, multiValued = false)
	String arg = null;
	@Override
	protected Object doExecute() throws Exception {
		 System.out.println("Bundle Dependency List"+arg);
		 Bundle bundle=bundleContext.getBundle(new Long(arg));
		 ServiceReference<PackageAdmin> ref = bundleContext.getServiceReference(PackageAdmin.class);
		 PackageAdmin packageAdmin = bundleContext.getService(ref);
		 ExportedPackage[] reqBundles=packageAdmin.getExportedPackages(bundle);
		 for(ExportedPackage reqBundle:reqBundles) {
			 Bundle exportingBundles = reqBundle.getExportingBundle();
			System.out.println(exportingBundles);
		 }
		 
		return null;
	}

}
