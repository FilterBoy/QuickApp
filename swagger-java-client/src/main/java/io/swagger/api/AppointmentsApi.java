/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.16-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Appointment;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-12T13:17:48.921+02:00")

@Api(value = "appointments", description = "the appointments API")
@RequestMapping(value = "")
public interface AppointmentsApi {

    @ApiOperation(value = "getBySalesId", nickname = "getBySalesIdUsingGET", notes = "", response = Appointment.class, responseContainer = "List", tags={ "appointment-controller", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Appointment.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/appointments/{id}",
        produces = { "*/*" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Appointment>> getBySalesIdUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getProposals", nickname = "getProposalsUsingGET", notes = "", response = Appointment.class, responseContainer = "List", tags={ "appointment-controller", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Appointment.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/appointments/propose",
        produces = { "*/*" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Appointment>> getProposalsUsingGET(@NotNull @ApiParam(value = "address", required = true) @Valid @RequestParam(value = "address", required = true) String address,@NotNull @ApiParam(value = "salesIds", required = true) @Valid @RequestParam(value = "salesIds", required = true) List<Long> salesIds);


    @ApiOperation(value = "newAppointment", nickname = "newAppointmentUsingPOST", notes = "", response = Appointment.class, tags={ "appointment-controller", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Appointment.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/appointments",
        produces = { "*/*" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Appointment> newAppointmentUsingPOST(@ApiParam(value = "appointment" ,required=true )  @Valid @RequestBody Appointment appointment);

}
