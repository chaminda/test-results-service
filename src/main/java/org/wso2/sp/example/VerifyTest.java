/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.sp.example;

import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.msf4j.Request;
import org.wso2.sp.example.exception.TestNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the Microservice resource class.
 * See <a href="https://github.com/wso2/msf4j#getting-started">https://github.com/wso2/msf4j#getting-started</a>
 * for the usage of annotations.
 *
 * @since 1.0.0-SNAPSHOT
 */

@Api(value = "testresults")
@SwaggerDefinition(
        info = @Info(
                title = "SP Test Results Swagger Definition", version = "1.0",
                description = "Stream Processor Test Results in-memory persisting service",
                license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0"),
                contact = @Contact(
                        name = "WSO2 Pvt Ltd",
                        email = "analytics-team@wso2.com",
                        url = "http://wso2.com"
                ))
)

@Path("/testresults")
public class VerifyTest {

    private static final Logger log = LoggerFactory.getLogger(VerifyTest.class);
    private Map<String, Event> testResults = new HashMap<>();

    public VerifyTest(){

    }

    @GET
    @Path("/{testCaseName}")
    @Produces({"application/json", "text/xml"})
    @ApiOperation(
            value = "Return event details corresponding to the testcase name",
            notes = "Returns HTTP 404 if the testcase is not found")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Valid stock item found"),
            @ApiResponse(code = 404, message = "Stock item not found")})
    public Response getTestResults(@ApiParam(value = "testCaseName", required = true)
                                   @PathParam("testCaseName") String testCaseName) throws TestNotFoundException {

        //@CookieParam("testCaseName") String testCaseName
        log.info("Getting Test results using PathParam...");
        Event result = testResults.get(testCaseName);
        if (result == null) {
            //throw new TestNotFoundException("TestCase " + testCaseName + " Not Found");
            log.warn("TestCase " + testCaseName + " Not Found");
            return Response.status(404).build();
        }
        return Response.ok().entity(result).build();
    }
//Good Read: https://stackoverflow.com/questions/28229017/jackson-not-consuming-the-json-root-element

    @POST
    @Path("/")
    @Consumes("application/json")
    @ApiOperation(
            value = "Add a test result",
            notes = "Add a valid method name and res")
    public void addResult(@ApiParam(value = "Result object", required = true) EventWrapper event, @ApiParam(value = "className string", required = true)
                          @HeaderParam("className") String className, @Context Request request) {
        //, @Context Request request
        log.info("POST invoked");
        request.getHeaders().getAll().forEach(entry -> System.out.println(entry.getName() + "=" + entry.getValue()));
        System.out.println("body==="+request.isEmpty());
        String testCaseName = className;
       /* if (testResults.containsKey(testCaseName)) {
            log.info("events exist for the test, not adding new.");
           //TODO: if events are already exist for the test
        }*/
        testResults.put(testCaseName,event.event);
        log.info("ClassName: "+ testCaseName);
        log.info("event: "+testResults.get(testCaseName));

    }

    @POST
    @Path("/clear")
    public void clearMap(){
        log.info("event map clearing...");
        testResults.clear();
    }

    /*GET
    @Path("/all")
    @Produces("application/json")
    public Collection<Result> getAll() {
        return testResults.values();
    }*/
}
