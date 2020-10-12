# AppointmentControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getBySalesIdUsingGET**](AppointmentControllerApi.md#getBySalesIdUsingGET) | **GET** /appointments/{id} | getBySalesId
[**getProposalsUsingGET**](AppointmentControllerApi.md#getProposalsUsingGET) | **GET** /appointments/propose | getProposals
[**newAppointmentUsingPOST**](AppointmentControllerApi.md#newAppointmentUsingPOST) | **POST** /appointments | newAppointment


<a name="getBySalesIdUsingGET"></a>
# **getBySalesIdUsingGET**
> List&lt;Appointment&gt; getBySalesIdUsingGET(id)

getBySalesId

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AppointmentControllerApi;


AppointmentControllerApi apiInstance = new AppointmentControllerApi();
Long id = 789L; // Long | id
try {
    List<Appointment> result = apiInstance.getBySalesIdUsingGET(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AppointmentControllerApi#getBySalesIdUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| id |

### Return type

[**List&lt;Appointment&gt;**](Appointment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getProposalsUsingGET"></a>
# **getProposalsUsingGET**
> List&lt;Appointment&gt; getProposalsUsingGET(address, salesIds)

getProposals

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AppointmentControllerApi;


AppointmentControllerApi apiInstance = new AppointmentControllerApi();
String address = "address_example"; // String | address
List<Long> salesIds = Arrays.asList(56L); // List<Long> | salesIds
try {
    List<Appointment> result = apiInstance.getProposalsUsingGET(address, salesIds);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AppointmentControllerApi#getProposalsUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **address** | **String**| address |
 **salesIds** | [**List&lt;Long&gt;**](Long.md)| salesIds |

### Return type

[**List&lt;Appointment&gt;**](Appointment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="newAppointmentUsingPOST"></a>
# **newAppointmentUsingPOST**
> Appointment newAppointmentUsingPOST(appointment)

newAppointment

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AppointmentControllerApi;


AppointmentControllerApi apiInstance = new AppointmentControllerApi();
Appointment appointment = new Appointment(); // Appointment | appointment
try {
    Appointment result = apiInstance.newAppointmentUsingPOST(appointment);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AppointmentControllerApi#newAppointmentUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **appointment** | [**Appointment**](Appointment.md)| appointment |

### Return type

[**Appointment**](Appointment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

