package com.InsRem.service;

import com.InsRem.entity.Agent;
import org.springframework.web.multipart.MultipartFile;

public interface AgentService {

    Agent getLoggedInAgent();

    void uploadProfileImage(
            MultipartFile file)
            throws Exception;

}