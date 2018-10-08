package org.singam.karaf.bundle.dependency.installer;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.apache.karaf.system.SystemService;
import org.osgi.framework.ServiceReference;

@Command(scope = "sys", name = "reboot", description="Karaf System Service Extension")
public class KarafSystemServiceExt extends OsgiCommandSupport {

	@Argument(index = 0, name = "arg", 
            description = "The command argument for Karaf System Service Extension", 
            required = false, multiValued = false)
	String arg = null;
	@Override
	protected Object doExecute() throws Exception {
		ServiceReference<SystemService> systemServiceRef=bundleContext.getServiceReference(SystemService.class);
		SystemService systemService = bundleContext.getService(systemServiceRef);
		systemService.reboot();
		return null;
	}

}
