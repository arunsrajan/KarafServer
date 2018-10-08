package org.singam.karaf.bundle.dependency.installer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.ServiceReference;

@Command(scope = "karaffeatures", name = "list", description="Features List")
public class FeaturesList extends OsgiCommandSupport {

	@Override
	protected Object doExecute() throws Exception {
		 ServiceReference<FeaturesService> featuresServiceRef = bundleContext.getServiceReference(FeaturesService.class);
		 FeaturesService featuresService = bundleContext.getService(featuresServiceRef);
		 List<Feature> features = Arrays.asList(featuresService.listFeatures());
		 features.stream().map(feat->feat.getName()).collect(Collectors.toSet()).stream().sorted().forEach(feature->System.out.println(feature));
		 return null;
	}
}
