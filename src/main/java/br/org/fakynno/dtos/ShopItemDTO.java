package br.org.fakynno.dtos;

import br.org.fakynno.models.ShopItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ShopItemDTO {

    @JsonProperty("productIdentifier")
    private String productIdentifier;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("price")
    private BigDecimal price;

    public static ShopItemDTO convert(ShopItem shopItem) {

        ShopItemDTO shopItemDTO = new ShopItemDTO();
        shopItemDTO.setProductIdentifier(shopItem.getProductIdentifier());
        shopItemDTO.setAmount(shopItem.getAmount());
        shopItemDTO.setPrice(shopItem.getPrice());
        return shopItemDTO;
    }
}
