package org.singam.karaf.bundle.dependency.installer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.ServiceReference;

@Command(scope = "karaffeatures", name = "install", description="Features List")
public class FeaturesInstall extends OsgiCommandSupport {

	@Argument(index = 0, name = "arg", description = "The command argument for Features installer", required = false, multiValued = false)
	String arg = null;
	@Override
	protected Object doExecute() throws Exception {
		 ServiceReference<FeaturesService> featuresServiceRef = bundleContext.getServiceReference(FeaturesService.class);
		 FeaturesService featuresService = bundleContext.getService(featuresServiceRef);
		 featuresService.installFeature(arg);
		 return null;
	}
}