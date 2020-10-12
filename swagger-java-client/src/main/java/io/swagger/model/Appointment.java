package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Appointment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-10-12T13:17:48.921+02:00")




public class Appointment   {
  @JsonProperty("address")
  private String address = null;

  @JsonProperty("begin")
  private OffsetDateTime begin = null;

  @JsonProperty("end")
  private OffsetDateTime end = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("note")
  private String note = null;

  @JsonProperty("salesEmployeeId")
  private Long salesEmployeeId = null;

  public Appointment address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Appointment begin(OffsetDateTime begin) {
    this.begin = begin;
    return this;
  }

  /**
   * Get begin
   * @return begin
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getBegin() {
    return begin;
  }

  public void setBegin(OffsetDateTime begin) {
    this.begin = begin;
  }

  public Appointment end(OffsetDateTime end) {
    this.end = end;
    return this;
  }

  /**
   * Get end
   * @return end
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getEnd() {
    return end;
  }

  public void setEnd(OffsetDateTime end) {
    this.end = end;
  }

  public Appointment id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Appointment note(String note) {
    this.note = note;
    return this;
  }

  /**
   * Get note
   * @return note
  **/
  @ApiModelProperty(value = "")


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Appointment salesEmployeeId(Long salesEmployeeId) {
    this.salesEmployeeId = salesEmployeeId;
    return this;
  }

  /**
   * Get salesEmployeeId
   * @return salesEmployeeId
  **/
  @ApiModelProperty(value = "")


  public Long getSalesEmployeeId() {
    return salesEmployeeId;
  }

  public void setSalesEmployeeId(Long salesEmployeeId) {
    this.salesEmployeeId = salesEmployeeId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Appointment appointment = (Appointment) o;
    return Objects.equals(this.address, appointment.address) &&
        Objects.equals(this.begin, appointment.begin) &&
        Objects.equals(this.end, appointment.end) &&
        Objects.equals(this.id, appointment.id) &&
        Objects.equals(this.note, appointment.note) &&
        Objects.equals(this.salesEmployeeId, appointment.salesEmployeeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, begin, end, id, note, salesEmployeeId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Appointment {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    begin: ").append(toIndentedString(begin)).append("\n");
    sb.append("    end: ").append(toIndentedString(end)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    salesEmployeeId: ").append(toIndentedString(salesEmployeeId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

