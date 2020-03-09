package com.cove.login.feignInterface;

import com.cove.login.configuration.FeignClientConfiguration;
import com.cove.login.constants.MicroServiceConstants;
import com.cove.login.requestDTO.UserRequestDTO;
import com.cove.login.responseDTO.UserResponseDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = MicroServiceConstants.UserMicroServiceConstants.BASE,configuration = FeignClientConfiguration.class)
@Service
@RequestMapping(value = MicroServiceConstants.BASE_API)
public interface UserInterface {
    @RequestMapping(MicroServiceConstants.UserMicroServiceConstants.SEARCH_USER)
    UserResponseDTO searchUser(@RequestBody UserRequestDTO requestDTO, @RequestHeader("X-TenantID") String tenantId);

    @RequestMapping(MicroServiceConstants.UserMicroServiceConstants.UPDATE_USER)
    void updateUser(@RequestBody UserResponseDTO responseDTO, @RequestHeader("X-TenantID") String tenantId);
}
