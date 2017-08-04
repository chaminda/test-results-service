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

import org.wso2.msf4j.Request;
import org.wso2.sp.example.exception.TestNotFoundException;

import java.util.Collection;
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
                        name = "Chaminda Jayawardena",
                        email = "chaminda@wso2.com",
                        url = "http://wso2.com"
                ))
)

@Path("/testresults")
public class VerifyTest {

    private Map<String, Result> testResults = new HashMap<>();

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
        System.out.println("Getting Test results using PathParam...");
        Result result = testResults.get(testCaseName);
        if (result == null) {
            throw new TestNotFoundException("TestCase " + testCaseName + " Not Found");
        }
        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @ApiOperation(
            value = "Add a test result",
            notes = "Add a valid method name and res")
    public void addResult(@ApiParam(value = "Result object", required = true) Result result) {
        //, @Context Request request
        System.out.println("POST invoked");
        //request.getHeaders().getAll().forEach(entry -> System.out.println(entry.getName() + "=" + entry.getValue()));
        String testCaseName = result.getTestCaseName();
        testResults.put(testCaseName,result);
        System.out.println("result "+ testResults.get(testCaseName));
    }

    /*GET
    @Path("/all")
    @Produces("application/json")
    public Collection<Result> getAll() {
        return testResults.values();
    }*/
}
