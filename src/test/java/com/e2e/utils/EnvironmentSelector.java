package com.e2e.utils;

import com.e2e.config.Configuration;

public class EnvironmentSelector {

    public static String envSelectorForBaseApplication(String tenant) {
        String url = null;
        String env = System.getProperty("envName", Configuration.ENVIRONMENT);

        // NESTED SWITCH
        switch (tenant) {
            case "LH": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.LH_DESKTOP_PREPROD;
                        break;
                    }
                    case "INT": {
                        url = Configuration.LH_DESKTOP_INT;
                        break;
                    }
                    case "AGATE": {
                        url = Configuration.LH_PORTAL_AGATE;
                        break;
                    }
                }
                break;
            }
            case "SN": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.SN_DESKTOP_PREPROD;
                        break;
                    }
                    case "INT": {
                        url = Configuration.SN_DESKTOP_INT;
                        break;
                    }
                    case "AGATE": {
                        url = Configuration.SN_PORTAL_AGATE;
                        break;
                    }
                }
                break;
            }

            case "LX": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.LX_DESKTOP_PREPROD;
                        break;
                    }
                    case "INT": {
                        url = Configuration.LX_DESKTOP_INT;
                        break;
                    }
                    case "AGATE": {
                        url = Configuration.LX_PORTAL_AGATE;
                        break;
                    }
                }
                break;
            }

            case "OS": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.OS_DESKTOP_PREPROD;
                        break;
                    }
                    case "INT": {
                        url = Configuration.OS_DESKTOP_INT;
                        break;
                    }
                    case "AGATE": {
                        url = Configuration.OS_PORTAL_AGATE;
                        break;
                    }
                }
                break;
            }
            default: {
                url = null;
            }

        }
        return url;
    }

    public static String getEnvironment() {
        return System.getProperty("envName", Configuration.ENVIRONMENT);
    }

    public static String envSelectorforOnceApplication(String tenant) {
        String url = null;
        String env = System.getProperty("envName", Configuration.ENVIRONMENT);

        // NESTED SWITCH
        switch (tenant) {
            case "LH": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.LH_ONCE_INT;
                        break;
                    }
                    case "INT": {
                        url = Configuration.LH_ONCE_INT;
                        break;
                    }
                }
                break;
            }
            case "SN": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.SN_ONCE_INT;
                        break;
                    }
                    case "INT": {
                        url = Configuration.SN_ONCE_INT;
                        break;
                    }
                }
                break;
            }

            case "LX": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.LX_ONCE_INT;
                        break;
                    }
                    case "INT": {
                        url = Configuration.LX_ONCE_INT;
                        break;
                    }
                }
                break;
            }

            case "OS": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.OS_ONCE_INT;
                        break;
                    }
                    case "INT": {
                        url = Configuration.OS_ONCE_INT;
                        break;
                    }
                }
                break;
            }
            default: {
                url = null;
            }

        }
        return url;
    }

    public static String envSelectorforMMBApplication(String tenant) {
        String url = null;
        String env = System.getProperty("envName", Configuration.ENVIRONMENT);

        // NESTED SWITCH
        switch (tenant) {
            case "LH": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.LH_MMB_PREPROD;
                        break;
                    }
                    case "INT": {
                        url = Configuration.LH_MMB_PREPROD;
                        break;
                    }
                }
                break;
            }
            case "SN": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.SN_MMB_PREPROD;
                        break;
                    }
                    case "INT": {
                        url = Configuration.SN_MMB_PREPROD;
                        break;
                    }
                }
                break;
            }

            case "LX": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.LX_MMB_PREPROD;
                        break;
                    }
                    case "INT": {
                        url = Configuration.LX_MMB_PREPROD;
                        break;
                    }
                }
                break;
            }

            case "OS": {
                switch (env) {
                    case "PREPROD": {
                        url = Configuration.OS_MMB_PREPROD;
                        break;
                    }
                    case "INT": {
                        url = Configuration.OS_MMB_PREPROD;
                        break;
                    }
                }
                break;
            }
            default: {
                url = null;
            }

        }
        return url;
    }

}
