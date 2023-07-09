package br.org.fakynno.controllers;

import br.org.fakynno.dtos.ShopDTO;
import br.org.fakynno.models.Shop;
import br.org.fakynno.models.ShopItem;
import br.org.fakynno.repositories.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopRepository shopRepository;

    @GetMapping
    public List<ShopDTO> getShop() {
        return shopRepository
                .findAll()
                .stream()
                .map(shop -> ShopDTO.convert(shop))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ShopDTO saveShop(@RequestBody ShopDTO shopDTO) {
        shopDTO.setIdentifier(
                UUID.randomUUID().toString());
        shopDTO.setDateShop(LocalDate.from(LocalTime.now()));
        shopDTO.setStatus("PENDING");

        Shop shop = Shop.convert(shopDTO);
        for (ShopItem shopItem : shop.getShopItems()) {
            shopItem.setShop(shop);
        }

        System.out.println("Saving shop: " + shop);
        return ShopDTO.convert(shopRepository.save(shop));
    }
}
