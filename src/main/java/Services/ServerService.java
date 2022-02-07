package Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
@RequiredArgsConstructor
public class ServerService {
    private final String serverPath = "server/";

    public Boolean init() {
        var server = new File(serverPath);

        if (!server.exists() && !server.isDirectory() && !server.mkdirs()) {
            log.error("Server initialization failed.");
            return false;
        }
        return true;
    }

    public String getServerPath() {
        return serverPath;
    }
}
