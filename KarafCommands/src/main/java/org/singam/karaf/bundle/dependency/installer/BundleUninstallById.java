package org.singam.karaf.bundle.dependency.installer;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.Bundle;

@Command(scope = "bundleuninstall", name = "id", description="Bundle Dependency List")
public class BundleUninstallById extends OsgiCommandSupport {

	@Argument(index = 0, name = "arg", 
            description = "The command argument for dependency bundle list", 
            required = false, multiValued = false)
	String arg = null;
	@Override
	protected Object doExecute() throws Exception {
		Bundle bundle=bundleContext.getBundle(new Long(arg));
		bundle.uninstall();
		return null;
	}

}
