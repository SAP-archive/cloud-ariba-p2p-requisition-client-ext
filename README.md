# cloud-ariba-p2p-requisition-client-ext

## Overview

The project represents a procure to pay requisition client for SAP Ariba.

You can use the `cloud-ariba-p2p-requisition-client-ext` to:

  * submit requisitions to your SAP Ariba instance

## Import the `cloud-ariba-p2p-requisition-client-ext` in your project

1. Create a module in your project to place the source code in. (the module contains only pom.xml with the configuration described below)

2. Configure the 'maven-scm-plugin` plugin to pull the source code from the GitHub repo.

  ```
  <scm>
    <connection>scm:git:git://github.com/SAP/cloud-ariba-p2p-requisition-client-ext.git</connection>
    <developerConnection>scm:git:https://github.com/SAP/cloud-ariba-p2p-requisition-client-ext.git</developerConnection>
    <url>https://github.com/SAP/cloud-ariba-p2p-requisition-client-ext</url>
  </scm>
  ```

2. Add the property specifying the `maven-scm-plugin` version to be used.

  ```
  <properties>
  ...
  	<maven.scm.plugin.version>1.9</maven.scm.plugin.version>
  ...
  </properties>
  ```

3. Add the `maven-scm-plugin` and its configuration.

	```
	<build>
		<plugins>
	  ...
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-scm-plugin</artifactId>
				<version>${maven.scm.plugin.version}</version>
				
				<configuration>
					<goals>checkout</goals>
					<!-- the dir path to pull the changes in -->
					<checkoutDirectory>${project.basedir}/src/main/java</checkoutDirectory> 
					<workingDirectory>${project.basedir}/src/main/java</workingDirectory>
					<!-- include only the source code -->
					<includes>ariba/**</includes> 
				</configuration>
				
				<executions>
					<execution>
						<id>generated-sources</id>
						<phase>generate-sources</phase>
						<goals>
							<goal>checkout</goal>
						</goals>
					</execution>
				</executions>
				
			</plugin>
		...
		</plugins>
	</build>
	```

## Copyright and license

Copyright 2016 SAP AG

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

