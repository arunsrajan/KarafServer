package org.singam.karaf.bundle.dependency.installer;

import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;
import org.apache.karaf.system.SystemService;
import org.osgi.framework.ServiceReference;
import org.osgi.service.blueprint.container.BlueprintContainer;
@Command(scope = "blueprint", name = "components", description="Karaf Blueprint Components")
public class GetBlueprintComponents extends OsgiCommandSupport {

	
	@Override
	protected Object doExecute() throws Exception {
		ServiceReference<BlueprintContainer> blueprintServiceRef=bundleContext.getServiceReference(BlueprintContainer.class);
		BlueprintContainer blueprintContainer = bundleContext.getService(blueprintServiceRef);
		blueprintContainer.getComponentIds().stream().forEach(component->{
			System.out.println(component);
			System.out.println(blueprintContainer.getComponentInstance(component));
		});
		
		return null;
	}

}
