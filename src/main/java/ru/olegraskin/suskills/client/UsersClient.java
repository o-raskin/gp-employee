package ru.olegraskin.suskills.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.olegraskin.suskills.client.dto.GatewayUserDto;
import ru.olegraskin.suskills.client.fallback.UsersClientFallback;

@FeignClient(value = "gateway", fallback = UsersClientFallback.class)
public interface UsersClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}",
            produces =  MediaType.APPLICATION_JSON_VALUE
    )
    GatewayUserDto getGatewayUser(@PathVariable("id") Long id);
}
