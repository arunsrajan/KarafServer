package org.singam.karaf.bundle.dependency.installer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.service.packageadmin.PackageAdmin;

@Command(scope = "services", name = "list", description="Bundle Dependency List")
public class ListAllServices extends OsgiCommandSupport {

	@Argument(index = 0, name = "arg", 
            description = "The command argument for Bundle List Services", 
            required = false, multiValued = false)
	String arg = null;
	@Override
	protected Object doExecute() throws Exception {
		List<Bundle> allBundles=Arrays.asList(bundleContext.getBundles());
		 allBundles.stream().map(bundle->{
			 System.out.println(bundle.getBundleId()+" "+bundle.getSymbolicName());
			if(bundle.getServicesInUse()!=null) {
				return Arrays.asList(bundle.getServicesInUse());
			}
			return null;
		}).forEach(serviceReferences->{
			if(serviceReferences!=null)
			serviceReferences.stream().forEach(serviceReference->{
				System.out.println("\t\t\t\t\t"+bundleContext.getService(serviceReference));
			});
		});
		return null;
	}

}
