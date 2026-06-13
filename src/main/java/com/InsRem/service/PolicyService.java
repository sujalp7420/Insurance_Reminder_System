package com.InsRem.service;

import com.InsRem.dto.PolicyRequest;
import com.InsRem.entity.Policy;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PolicyService {

    List<Policy> searchPolicy(String keyword);

    Policy savePolicy(PolicyRequest request);

    List<Policy> getAllPolicies();

    void deletePolicy(Long id);

    void renewPolicy(Long id);

    void uploadFile(
            Long id,
            MultipartFile file)
            throws Exception;

    Policy findById(Long id);
}