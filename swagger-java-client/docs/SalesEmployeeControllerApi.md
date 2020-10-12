# SalesEmployeeControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**allUsingGET**](SalesEmployeeControllerApi.md#allUsingGET) | **GET** /salesEmployees | all


<a name="allUsingGET"></a>
# **allUsingGET**
> List&lt;SalesEmployee&gt; allUsingGET()

all

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.SalesEmployeeControllerApi;


SalesEmployeeControllerApi apiInstance = new SalesEmployeeControllerApi();
try {
    List<SalesEmployee> result = apiInstance.allUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SalesEmployeeControllerApi#allUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;SalesEmployee&gt;**](SalesEmployee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

