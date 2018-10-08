package org.singam.karaf.bundle.dependency.installer;

import java.util.List;

import org.apache.karaf.features.BundleInfo;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;


@Command(scope = "karaffeatures", name = "installdep", description = "Bundle Dependency Installer")
public class KarafFeaturesInstallDepBundle extends OsgiCommandSupport {

	@Argument(index = 0, name = "arg", description = "The command argument for Features installer", required = false, multiValued = false)
	String arg = null;
	@Override
	protected Object doExecute() throws Exception {
		ServiceReference<FeaturesService> featuresServiceRef = bundleContext.getServiceReference(FeaturesService.class);
		 FeaturesService featuresService = bundleContext.getService(featuresServiceRef);
		 List<BundleInfo> bundleInfos=featuresService.getFeature(arg).getBundles();
		 bundleInfos.stream().forEach(bundleInfo->{
			try {
				Bundle bundle=bundleContext.installBundle(bundleInfo.getLocation());
				bundle.start();
				System.out.println(bundle);
			} catch (BundleException e) {
				e.printStackTrace();
			}
		});
		return null;
	}

}
