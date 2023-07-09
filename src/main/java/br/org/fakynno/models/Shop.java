package br.org.fakynno.models;

import br.org.fakynno.dtos.ShopDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identifier;

    private String status;

    @Column(name = "date_shop")
    private LocalDate dateShop;

    @OneToMany(fetch = FetchType.EAGER,
    cascade = CascadeType.ALL,
    mappedBy = "shop")
    private List<ShopItem> shopItems;

    public static Shop convert(ShopDTO shopDTO) {
        Shop shop = new Shop();

        shop.setIdentifier(shopDTO.getIdentifier());
        shop.setStatus(shopDTO.getStatus());
        shop.setDateShop(shopDTO.getDateShop());
        shop.setShopItems(shopDTO
                .getListItems()
                .stream()
                .map(i -> ShopItem.convert(i))
                .collect(Collectors.toList()));

        return shop;
    }


}
