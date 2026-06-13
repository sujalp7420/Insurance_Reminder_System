package com.InsRem.controller;

import com.InsRem.dto.PolicyRequest;
import com.InsRem.entity.Policy;
import com.InsRem.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
@RequestMapping("/policies")
public class PolicyController {

    private final PolicyService policyService;

    @GetMapping
    public String policies(Model model) {

        model.addAttribute(
                "policies",
                policyService.getAllPolicies());

        return "policy-list";
    }

    @PostMapping("/save")
    public String savePolicy(
            @ModelAttribute PolicyRequest request,
            Model model) {

        try {

            policyService.savePolicy(request);

            return "redirect:/policies";

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage());

            return "add-policy";
        }
    }

    @GetMapping("/add")
    public String addPolicyPage(Model model) {

        model.addAttribute(
                "policyRequest",
                new PolicyRequest());

        return "add-policy";
    }

    @GetMapping("/delete/{id}")
    public String deletePolicy(
            @PathVariable Long id) {

        policyService.deletePolicy(id);

        return "redirect:/policies";
    }

    @GetMapping("/renew/{id}")
    public String renewPolicy(
            @PathVariable Long id) {

        policyService.renewPolicy(id);

        return "redirect:/policies";
    }

    @GetMapping("/search")
    public String searchPolicy(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "policies",
                policyService.searchPolicy(keyword));

        return "policy-list";
    }

    @PostMapping("/upload/{id}")
    public String uploadDocument(

            @PathVariable Long id,

            @RequestParam MultipartFile file)

            throws Exception {

        policyService.uploadFile(
                id,
                file);

        return "redirect:/policies";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource>
    downloadPolicy(

            @PathVariable Long id)

            throws Exception {

        Policy policy =
                policyService.findById(id);

        Path path =
                Paths.get(
                        "uploads/"
                                + policy.getDocumentPath());

        Resource resource =
                new UrlResource(
                        path.toUri());

        return ResponseEntity.ok()

                .header(
                        HttpHeaders.CONTENT_DISPOSITION,

                        "attachment; filename=\""
                                + resource.getFilename()
                                + "\"")

                .body(resource);
    }
}