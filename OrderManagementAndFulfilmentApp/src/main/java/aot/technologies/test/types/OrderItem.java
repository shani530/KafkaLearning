
package aot.technologies.test.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ProductCode",
    "ProductType",
    "ProductDescripton",
    "ProductQuantity",
    "ProductOrderStatus"
})
public class OrderItem {

    @JsonProperty("ProductCode")
    private Integer productCode;
    @JsonProperty("ProductType")
    private Object productType;
    @JsonProperty("ProductDescripton")
    private Object productDescripton;
    @JsonProperty("ProductQuantity")
    private Integer productQuantity;
    @JsonProperty("ProductOrderStatus")
    private Object productOrderStatus;

    @JsonProperty("ProductCode")
    public Integer getProductCode() {
        return productCode;
    }

    @JsonProperty("ProductCode")
    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    @JsonProperty("ProductType")
    public Object getProductType() {
        return productType;
    }

    @JsonProperty("ProductType")
    public void setProductType(Object productType) {
        this.productType = productType;
    }

    @JsonProperty("ProductDescripton")
    public Object getProductDescripton() {
        return productDescripton;
    }

    @JsonProperty("ProductDescripton")
    public void setProductDescripton(Object productDescripton) {
        this.productDescripton = productDescripton;
    }

    @JsonProperty("ProductQuantity")
    public Integer getProductQuantity() {
        return productQuantity;
    }

    @JsonProperty("ProductQuantity")
    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @JsonProperty("ProductOrderStatus")
    public Object getProductOrderStatus() {
        return productOrderStatus;
    }

    @JsonProperty("ProductOrderStatus")
    public void setProductOrderStatus(Object productOrderStatus) {
        this.productOrderStatus = productOrderStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("productCode", productCode).append("productType", productType).append("productDescripton", productDescripton).append("productQuantity", productQuantity).append("productOrderStatus", productOrderStatus).toString();
    }

}
