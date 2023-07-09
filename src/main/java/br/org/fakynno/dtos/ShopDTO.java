package br.org.fakynno.dtos;

import br.org.fakynno.models.Shop;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShopDTO {

    private String identifier;
    private LocalDate dateShop;
    private String status;
    private List<ShopItemDTO> listItems = new ArrayList<>();

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();

        shopDTO.setIdentifier(shop.getIdentifier());
        shopDTO.setDateShop(shop.getDateShop());
        shopDTO.setStatus(shop.getStatus());
        shopDTO.setListItems(shop
                .getShopItems()
                .stream()
                .map(i -> ShopItemDTO.convert(i))
                .collect(Collectors.toList()));

        return shopDTO;
    }
}
