package com.InsRem.service.impl;

import com.InsRem.dto.PolicyRequest;
import com.InsRem.entity.Policy;
import com.InsRem.entity.PolicyStatus;
import com.InsRem.repository.PolicyRepository;
import com.InsRem.service.AgentService;
import com.InsRem.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final AgentService agentService;

    @Override
    public Policy savePolicy(PolicyRequest request) {
        if(policyRepository.existsByPolicyNumber(
                request.getPolicyNumber())) {

            throw new RuntimeException(
                    "Policy Number Already Exists");
        }
        Policy policy = Policy.builder()
                .agent(agentService.getLoggedInAgent())
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .customerMobile(request.getCustomerMobile())
                .policyType(request.getPolicyType())
                .companyName(request.getCompanyName())
                .policyNumber(request.getPolicyNumber())
                .startDate(request.getStartDate())
                .expiryDate(request.getExpiryDate())
                .premiumAmount(request.getPremiumAmount())
                .notes(request.getNotes())
                .status(PolicyStatus.ACTIVE)
                .build();

        return policyRepository.save(policy);
    }

    @Override
    public List<Policy> getAllPolicies() {

        return policyRepository.findByAgent(
                agentService.getLoggedInAgent());
    }

    @Override
    public void deletePolicy(Long id) {

        Policy policy = findById(id);

        policyRepository.delete(policy);
    }

    @Override
    public void renewPolicy(Long id) {

        Policy policy = policyRepository
                .findById(id)
                .orElseThrow();

        policy.setExpiryDate(
                policy.getExpiryDate().plusYears(1));

        policy.setStatus(
                PolicyStatus.RENEWED);

        policyRepository.save(policy);
    }

    @Override
    public List<Policy> searchPolicy(
            String keyword) {

        List<Policy> customerList =
                policyRepository
                        .findByCustomerNameContainingIgnoreCase(
                                keyword);

        if (!customerList.isEmpty()) {
            return customerList;
        }

        return policyRepository
                .findByPolicyNumberContainingIgnoreCase(
                        keyword);
    }

    @Override
    public void uploadFile(
            Long id,
            MultipartFile file)
            throws Exception {

        Policy policy =
                policyRepository
                        .findById(id)
                        .orElseThrow();

        String uploadDir =
                "uploads/";

        File dir =
                new File(uploadDir);

        if(!dir.exists()) {

            dir.mkdirs();
        }

        String filename =
                System.currentTimeMillis()
                        + "_"
                        + file.getOriginalFilename();

        Path path =
                Paths.get(
                        uploadDir
                                + filename);

        Files.write(
                path,
                file.getBytes());

        policy.setDocumentPath(
                filename);

        policyRepository.save(
                policy);
    }

    @Override
    public Policy findById(Long id) {

        Policy policy = policyRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Policy not found with id: " + id));

        // Security Check: Agent can only access own policy
        if (!policy.getAgent().getId().equals(
                agentService.getLoggedInAgent().getId())) {

            throw new RuntimeException(
                    "Unauthorized access");
        }

        return policy;
    }
}