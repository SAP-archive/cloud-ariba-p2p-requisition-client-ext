# cloud-ariba-p2p-requisition-client-ext

## Overview

The project represents a requisition client for the Ariba Procure-to-Pay Solution.

You can use the **cloud-ariba-p2p-requisition-client-ext** to submit requisitions to your SAP Ariba instance.

## Import the **cloud-ariba-p2p-requisition-client-ext** in your project.

1. Create a module in your project to place the source code in. (the module contains only **pom.xml** with the configuration described below).

2. Configure the **maven-scm-plugin** plug-in to pull the source code from the GitHub repository.

    ```
	  <scm>
	  	<connection>scm:git:git://github.com/SAP/cloud-ariba-p2p-requisition-client-ext.git</connection>
	  	<developerConnection>scm:git:https://github.com/SAP/cloud-ariba-p2p-requisition-client-ext.git</developerConnection>
	  	<url>https://github.com/SAP/cloud-ariba-p2p-requisition-client-ext</url>
	  </scm>
    ```

3. Add the properties specifying **maven-scm-plugin**, **JDK**, **slf4j**, **maven-compiler-plugin** and their respective versions.

    ```
	  <properties>
		<maven.scm.plugin.version>1.9</maven.scm.plugin.version>
		<cloud.jdk.version>1.7</cloud.jdk.version>
		<slf4j.version>1.7.7</slf4j.version>
		<maven.plugins.version>3.3</maven.plugins.version>
	  </properties>
    ```

4. Add the **slf4j** dependency, the **maven-scm-plugin** and **maven-compiler-plugin** plug-ins and their configurations.

  
	<dependencies>

		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${slf4j.version}</version>
		</dependency>

	</dependencies>

	<build>
		<plugins>

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

		</plugins>
		<pluginManagement>
			<plugins>

				<!--This plugin's configuration is used to store Eclipse m2e settings 
					only. It has no influence on the Maven build itself. -->
				<plugin>
					<groupId>org.eclipse.m2e</groupId>
					<artifactId>lifecycle-mapping</artifactId>
					<version>1.0.0</version>
					<configuration>
						<lifecycleMappingMetadata>
							<pluginExecutions>
								<pluginExecution>
									<pluginExecutionFilter>
										<groupId>org.apache.maven.plugins</groupId>
										<artifactId>maven-scm-plugin</artifactId>
										<versionRange>[1.9,)</versionRange>
										<goals>
											<goal>checkout</goal>
										</goals>
										</pluginExecutionFilter>
									<action>
										<execute />
									</action>
								</pluginExecution>
							</pluginExecutions>
						</lifecycleMappingMetadata>
					</configuration>
				</plugin>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>${maven.plugins.version}</version>
					<configuration>
						<source>${cloud.jdk.version}</source>
						<target>${cloud.jdk.version}</target>
						<showDeprecation>false</showDeprecation>
						<showWarnings>false</showWarnings>
					</configuration>
				</plugin>

			</plugins>
		</pluginManagement>
	</build>
  

4. Right click on your project and then choose Maven > Update project > OK.

5. Run `mvn clean install` on the parent project. If everything is fine, the client should be downloaded from GitHub and imported in the project module you created.

## Create a SOAP request.

In the **RequisitionSOAPClient.java** class, there are two methods for SOAP body creation: 

* **private static SOAPElement addChildElements(SOAPElement currentElement, String... newElementsNames)**: makes a hierarchy with XML element names from the array **newElementsNames** and returns the last added **SOAPElement**;

* **private static SOAPElement addChildElementsWithValue(SOAPElement currentElement, String newElementValue, String... newElementsNames)**: makes a hierarchy with XML elements names from the array **newElementsNames**, sets **newElementValue** value to the last XML element and returns it as a **SOAPElement**.

## Examples for creating a SOAP request.

You can create new SOAP request or edit the current. Here you have some examples that you can use:

* adding an element without a value:

       `addChildElements(soapBody, "ElemName");`

  result:

       <urn:ElemName/>

* adding nested elements:

       addChildElements(soapBody, "ElemName1", "ElemName2", "ElemName3", "ElemName4"); 
	
  result:
  
   
	  <urn:ElemName1>
		<urn:ElemName2>
			<urn:ElemName3>
				<urn:ElemName4/>
			</urn:ElemName3>
		</urn:ElemName2>
	  </urn:ElemName1>
   

* adding an element with a value:

	  addChildElementsWithValue(soapBody, "Value", "ElemName");
	
  result:

	  <urn:ElemName>Value</urn:ElemName>
			
* adding nested elements with a value in the last element:

	  `addChildElementsWithValue(soapBody, "Value4", "ElemName1", "ElemName2", "ElemName3", "ElemName4");`
	
  result:
 
	
	  <urn:ElemName1>
		<urn:ElemName2>
			<urn:ElemName3>
				<urn:ElemName4>Value4</urn:ElemName4>
			</urn:ElemName3>
		</urn:ElemName2>
	  </urn:ElemName1>
	
* adding nested elements with values:

   
	
	  SOAPElement soapElement3 = addChildElements(soapBody, "ElemName1", "ElemName2", "ElemName3");
	  addChildElementsWithValue(soapElement3, "Value4_1", "ElemName4_1");
	  addChildElements(soapElement3, "ElemName4_2_1", "ElemName4_2_2");
	  addChildElementsWithValue(soapElement3, "Value4_3_2", "ElemName4_3_1", "ElemName4_3_2");
	
        
        
  result:
  
	
	  <urn:ElemName1>
		<urn:ElemName2>
			<urn:ElemName3>
				<urn:ElemName4_1>Value4_1</urn:ElemName4_1>
				<urn:ElemName4_2_1>
					<urn:ElemName4_2_2/>
				</urn:ElemName4_2_1>
				<urn:ElemName4_3_1>
					<urn:ElemName4_3_2>Value4_3_2</urn:ElemName4_3_2>
				</urn:ElemName4_3_1>
			</urn:ElemName3>
		</urn:ElemName2>
	  </urn:ElemName1>
	
	
## Example for using the Requisition SOAP client.

To submit a requisition you have to invoke:

	
	  `RequisitionSOAPClient.submit(URL url, PrepareRequisitionParameter prepareRequisitionParameter, String user, String password)`
	
Example for URL. This is an end point for requisitions import:

	
	  https://s1.ariba.com/Buyer/soap/salesdemo/RequisitionImportPull
	

Example for **PrepareRequisitionParameter** object:

	
	
    {  
    
	   "needByDate":"2016-10-24T09:03:44.258Z",
	   "shipTo":"SHARE:KUNY00",
	   "businessUnit":"US001",
	   "deliverTo":"Hana Cloud",
	   "headerComment":"This requisition is imported by HCP on date Mon Oct 24 2016 12:03:44 GMT+0300 (E. Europe Daylight Time)",
	   "headerName":"HCP XML Req Import",
	   "originatingSystem":"Hana Cloud Platform_Ivener",
	   "originatingSystemId":"system_id",
	   "passwordAdapter":"PasswordAdapter1",
	   "preparer":"myuser",
	   "requester":"myuser",
	   "headerUniqueName":"HCPARB_001",
	   "namespaceXMLNSvariant":"vrealm_3",
	   "items":[  
	      {  
	         "needByDate":"2016-10-24T09:03:44.258Z",
	         "shipTo":"SHARE:KUNY00",
	         "deliverTo":"Hana Cloud",
	         "commodityCode":"code",
	         "billingAddress":"SHARE:US001",
	         "manPartNumber":"SHLDMRX",
	         "description":"Protective Goggles Smoke/Yellow/Clear",
	         "currency":"USD",
	         "supplierPartNumber":"partId",
	         "unitOfMeasure":"EA",
	         "itemComment":"Protective Goggles Smoke/Yellow/Clear",
	         "supplierName":"did491",
	         "quantity":1.0,
	         "itemPrice":47.0,
	         "numberInCollection":1,
	         "originatingSystemLineNumber":1,
	         "commonCommodityCodeDomain":"unspsc",
	         "commonCommodityCodeName":"codeName"
	      	}
	   	]
	}
	
Example for another parameters:

	```
		userName: userName123
		password: ******
    ```

Copyright and license
---------------------

Copyright 2016 SAP SE

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
