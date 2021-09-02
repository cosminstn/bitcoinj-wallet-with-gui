package wallettemplate.utils;

import org.bitcoinj.core.Utils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class AppDataDirectory {

	/**
	 * Get and create if necessary the Path to the application data directory.
	 *
	 * @param appName The name of the current application
	 * @return Path to the application data directory
	 */
	public static Path get(String appName) {
		Path applicationDataDirectory = getPath(appName);
		try {
			Files.createDirectories(applicationDataDirectory);
		} catch (IOException ioe) {
			throw new RuntimeException("Couldn't find/create AppDataDirectory", ioe);
		}
		return applicationDataDirectory;
	}

	/**
	 * Return the Path to the application data directory without making
	 * sure it exists or creating it. (No disk I/O)
	 *
	 * @param appName The name of the current application
	 * @return Path to the application data directory
	 */
	private static Path getPath(String appName) {
		Path applicationDataDirectory = null;
		if (Utils.isWindows()) {
			applicationDataDirectory = pathOf(System.getenv("APPDATA"), appName.toLowerCase(Locale.getDefault()));
		} else if (Utils.isMac()) {
			applicationDataDirectory = pathOf(System.getProperty("user.home"), "Library/Application Support", appName);
		} else if (Utils.isLinux()) {
			applicationDataDirectory = pathOf(System.getProperty("user.home"), "." + appName.toLowerCase(Locale.getDefault()));
		} else {
			// Unknown, assume unix-like
			applicationDataDirectory = pathOf(System.getProperty("user.home"), "." + appName.toLowerCase(Locale.getDefault()));
		}
		return applicationDataDirectory;
	}

	/**
	 * Create a `Path` by joining path strings. Same functionality as Path.of() in JDK 11+
	 *
	 * @param first      A base path string
	 * @param additional additional components to add
	 * @return the joined `Path`
	 */
	private static Path pathOf(String first, String... additional) {
		return FileSystems.getDefault().getPath(first, additional);
	}

}
