package com.rslakra.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 12/10/21 12:22 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class BuildVersion {

    private final String BUILD_VERSION = "build.properties";

    private String majorVersion;
    private String minorVersion;
    private String patchVersion;
    private String buildVersion;

    public void loadBuildProperties() {
//        IOUtils.readStream(IOUtils.newInputStream(Paths.get("build.properties")));
    }

}
