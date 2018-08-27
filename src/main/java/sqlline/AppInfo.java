/*
// Licensed to Julian Hyde under one or more contributor license
// agreements. See the NOTICE file distributed with this work for
// additional information regarding copyright ownership.
//
// Julian Hyde licenses this file to you under the Modified BSD License
// (the "License"); you may not use this file except in compliance with
// the License. You may obtain a copy of the License at:
//
// http://opensource.org/licenses/BSD-3-Clause
*/
package sqlline;

import java.io.InputStream;
import java.util.Properties;

/**
 * Interface responsible for returning information message
 * about application message. Should be implemented in case
 * if custom info message should be displayed.
 */
public interface AppInfo {

  String DEFAULT_MESSAGE = "sqlline version ???";

  /**
   * @return application info message
   * @throws Exception in case of any errors
   */
  String getInfoMessage() throws Exception;

  /**
   * SqlLine info message implementation.
   * Returns current sqlline app name and version.
   */
  class SqlLineAppInfo implements AppInfo {
    @Override
    public String getInfoMessage() throws Exception {
      InputStream inputStream =
          getClass().getResourceAsStream(
              "/META-INF/maven/sqlline/sqlline/pom.properties");
      Properties properties = new Properties();
      properties.put("artifactId", "sqlline");
      properties.put("version", "???");
      if (inputStream != null) {
        // If not running from a .jar, pom.properties will not exist, and
        // inputStream is null.
        properties.load(inputStream);
      }
      return String.format("%s version %s",
          properties.getProperty("artifactId"),
          properties.getProperty("version"));
    }
  }
}
