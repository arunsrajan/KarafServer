package org.singam.karaf.bundle.dependency.installer;

import java.util.List;

import org.apache.karaf.features.BundleInfo;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.ServiceReference;

@Command(scope = "karaffeatures", name = "listbundles", description="Features List")
public class KarafFeaturesListBundle extends OsgiCommandSupport {

	@Argument(index = 0, name = "arg", description = "The command argument for Features installer", required = false, multiValued = false)
	String arg = null;
	@Override
	protected Object doExecute() throws Exception {
		ServiceReference<FeaturesService> featuresServiceRef = bundleContext.getServiceReference(FeaturesService.class);
		FeaturesService featuresService = bundleContext.getService(featuresServiceRef);
		List<BundleInfo> bundleInfos=featuresService.getFeature(arg).getBundles();
		bundleInfos.stream().forEach(bundleInfo->System.out.println(bundleInfo.getLocation()+" "+bundleInfo.getOriginalLocation()+" "+bundleInfo.getStartLevel()));
		return null;
	}

}
