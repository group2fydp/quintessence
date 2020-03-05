package com.cove.gateway.feignInterface;

import com.cove.gateway.constants.MicroServiceConstants;
import com.cove.gateway.responseDTO.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
@FeignClient(name = MicroServiceConstants.USER_SERVICE)
@RequestMapping(value = MicroServiceConstants.BASE_API)
public interface UserInterface {
    @RequestMapping(value = MicroServiceConstants.UserMicroServiceConstants.FETCH_USER_BY_USERNAME)
    Optional<UserResponseDTO> fetchUserByUsername(@PathVariable("username") String username);
}
