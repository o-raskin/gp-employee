package ru.olegraskin.suskills.client.fallback;

import ru.olegraskin.suskills.client.UsersClient;
import ru.olegraskin.suskills.client.dto.GatewayUserDto;

public class UsersClientFallback implements UsersClient {

    @Override
    public GatewayUserDto getGatewayUser(Long id) {
        return new GatewayUserDto(id);
    }
}
