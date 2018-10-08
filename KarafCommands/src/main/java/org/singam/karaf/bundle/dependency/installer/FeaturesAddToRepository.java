package org.singam.karaf.bundle.dependency.installer;

import java.net.URI;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.ServiceReference;

@Command(scope = "karaffeatures", name = "addtorepo", description="Features List")
public class FeaturesAddToRepository extends OsgiCommandSupport {

	@Argument(index = 0, name = "arg", description = "The command argument for Features installer", required = false, multiValued = false)
	String arg = null;
	
	@Override
	protected Object doExecute() throws Exception {
		 ServiceReference<FeaturesService> featuresServiceRef = bundleContext.getServiceReference(FeaturesService.class);
		 FeaturesService featuresService = bundleContext.getService(featuresServiceRef);
		 featuresService.addRepository(new URI(arg));
		 return null;
	}

}
