package org.singam.karaf.bundle.dependency.installer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.ServiceReference;

@Command(scope = "karaffeatures", name = "installed", description="Features List")
public class InstalledFeatures extends OsgiCommandSupport {

	@Override
	protected Object doExecute() throws Exception {
		ServiceReference<FeaturesService> featuresServiceRef = bundleContext.getServiceReference(FeaturesService.class);
		FeaturesService featuresService = bundleContext.getService(featuresServiceRef);
		List<Feature> installedFeature=Arrays.asList(featuresService.listInstalledFeatures());
		installedFeature.stream().map(feat->feat.getName()).collect(Collectors.toSet()).stream().sorted().forEach(feature->System.out.println(feature));
		return null;
	}

}
