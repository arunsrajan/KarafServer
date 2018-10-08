package org.singam.karaf.bundle.dependency.installer;

import java.util.Arrays;
import java.util.List;

import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.osgi.framework.Bundle;
@Command(scope = "bundles", name = "listall", description = "Bundle Dependency Installer")
public class ListAllBundles extends OsgiCommandSupport {

	@Override
	protected Object doExecute() throws Exception {
		List<Bundle> bundles=Arrays.asList(bundleContext.getBundles());
		bundles.stream().forEach(bundle->System.out.println(bundle.getBundleId()+" "+bundle.getLocation()+" "+(bundle.getState()==Bundle.ACTIVE?"ACTIVE":bundle.getState()==Bundle.INSTALLED?"INSTALLED":bundle.getState()==Bundle.RESOLVED?"RESOLVED":bundle.getState()==Bundle.STARTING?"STARTING":"")));
		return null;
	}

}
