package org.singam.karaf.bundle.dependency.installer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

@Command(scope = "bundlelist", name = "class", description="Bundle Dependency List")
public class BundleListClass extends OsgiCommandSupport {

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
		Bundle[] importingBundles=exportedPackage.getImportingBundles();
		System.out.println("Bundle Detected");
		System.out.println(exportingBundle.getBundleId()+"-"+exportingBundle.getSymbolicName()+"-"+exportingBundle.getLocation());
		System.out.println("---------------List Of Importing Bundles--------------------");
		List<Bundle> bundles=Arrays.asList(importingBundles);
		bundles.stream().sorted(new Comparator<Bundle>() {

			@Override
			public int compare(Bundle o1, Bundle o2) {
				if(o1.getBundleId()>o2.getBundleId()) {
					return 1;
				}
				else if(o1.getBundleId()<o2.getBundleId()) {
					return -1;
				}
				return 0;
			}
			
		}).forEach(bundle->{
		System.out.println(bundle.getBundleId()+"-"+bundle.getSymbolicName()+"-"+bundle.getLocation());
		});
		return null;
	}

}
