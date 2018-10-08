package org.singam.karaf.bundle.dependency.installer;

import java.util.List;

import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;

public class BundleDependencyInstallerCompleter implements Completer {
	@Override
	public int complete(String buffer, int cursor, List<String> candidates) {
		 	StringsCompleter delegate = new StringsCompleter();
		  return delegate.complete(buffer, cursor, candidates);
	}

}
