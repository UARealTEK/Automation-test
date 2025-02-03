package Utils;

import java.nio.file.Paths;

public class Constants {
    public static final String BASE_URL = "https://play1.automationcamp.ir/";
    public static final String BASE_FILES = "src/test/resources/Files";
    public static final String UPLOAD_DIRECTORY = Paths.get(DriverOperations.getWorkingDirectory(),BASE_FILES).toAbsolutePath().toString();
}
